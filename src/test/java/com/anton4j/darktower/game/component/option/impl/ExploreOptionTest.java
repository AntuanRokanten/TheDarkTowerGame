package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.GameStats;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.character.CharEvent;
import com.anton4j.darktower.game.character.encounter.EncounterOutcome;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.location.GameMap;
import com.anton4j.darktower.util.RandomUtils;
import com.anton4j.darktower.util.TestConsoleUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

/**
 * @author ant
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({RandomUtils.class})
public class ExploreOptionTest extends OutStreamsInterceprtorTest {

    @Test
    public void processOptionExploration() {
        // ARRANGE
        GameContext gameContext = Mockito.mock(GameContext.class);

        Char character = Mockito.mock(Char.class);
        GameMap gameMap = Mockito.mock(GameMap.class);
        GameStats gameStats = Mockito.mock(GameStats.class);

        when(gameContext.getMainCharacter()).thenReturn(character);
        when(gameContext.getGameMap()).thenReturn(gameMap);
        when(gameContext.getGameStats()).thenReturn(gameStats);

        when(gameMap.canMoveToNextLocation(character)).thenReturn(true);

        mockStatic(RandomUtils.class);
        PowerMockito.when(RandomUtils.randomBoolean()).thenReturn(true, true);

        ExploreOption exploreOption = new ExploreOption(gameContext);

        // ACT
        OptionResult<Void> result = exploreOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());
        verify(character).increaseExperience(CharEvent.EXPLORATION);
        verify(gameMap).canMoveToNextLocation(character);
        verify(gameStats).areaExplored();
        verify(gameContext).getMainCharacter();
        verify(gameContext).getGameMap();
        verify(gameContext).getGameStats();

        verifyNoMoreInteractions(character, gameMap, gameStats, gameContext);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are exploring a current location..."));
        assertTrue(consoleOutput.contains("The character is ready to move closer to the destination! Choose 'Move to the next location' option"));
        assertTrue(consoleOutput.contains("You have explored new territories in the current location"));
    }

    @Test
    public void processOptionFightSuccess() {
        // ARRANGE
        GameContext gameContext = Mockito.mock(GameContext.class);

        Char character = Mockito.mock(Char.class);
        GameMap gameMap = Mockito.mock(GameMap.class);
        GameStats gameStats = Mockito.mock(GameStats.class);

        when(gameContext.getMainCharacter()).thenReturn(character);
        when(gameContext.getGameMap()).thenReturn(gameMap);
        when(gameContext.getGameStats()).thenReturn(gameStats);

        when(character.fight(any())).thenReturn(EncounterOutcome.SUCCESS);

        when(gameMap.canMoveToNextLocation(character)).thenReturn(true);

        mockStatic(RandomUtils.class);
        PowerMockito.when(RandomUtils.randomBoolean()).thenReturn(false);

        ExploreOption exploreOption = new ExploreOption(gameContext);

        // ACT
        TestConsoleUtils.enterValue(1 + "");
        OptionResult<Void> result = exploreOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());

        verify(character).increaseExperience(CharEvent.FIGHT_VICTORY);
        verify(character).fight(any());
        verify(character).defence();
        verify(character).vitality();
        verify(character).strength();
        verify(character).speed();

        verify(gameMap).canMoveToNextLocation(character);
        verify(gameStats).fightWin();
        verify(gameContext).getMainCharacter();
        verify(gameContext).getGameMap();
        verify(gameContext).getGameStats();

        verifyNoMoreInteractions(character, gameMap, gameStats, gameContext);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are exploring a current location..."));
        assertTrue(consoleOutput.contains("The character is ready to move closer to the destination! Choose 'Move to the next location' option"));
        assertTrue(consoleOutput.contains("You encountered a beast"));
        assertTrue(consoleOutput.contains("Beast stats: race = "));
        assertTrue(consoleOutput.contains("Your stats:"));
        assertTrue(consoleOutput.contains("1 - Fight"));
        assertTrue(consoleOutput.contains("2 - Run away"));
        assertTrue(consoleOutput.contains("Character won the fight"));
    }

    @Test
    public void processOptionFightDefeat() {
        // ARRANGE
        GameContext gameContext = Mockito.mock(GameContext.class);

        Char character = Mockito.mock(Char.class);
        GameMap gameMap = Mockito.mock(GameMap.class);
        GameStats gameStats = Mockito.mock(GameStats.class);

        when(gameContext.getMainCharacter()).thenReturn(character);
        when(gameContext.getGameMap()).thenReturn(gameMap);
        when(gameContext.getGameStats()).thenReturn(gameStats);

        when(character.fight(any())).thenReturn(EncounterOutcome.FAILURE);

        when(gameMap.canMoveToNextLocation(character)).thenReturn(true);

        mockStatic(RandomUtils.class);
        PowerMockito.when(RandomUtils.randomBoolean()).thenReturn(false);

        ExploreOption exploreOption = new ExploreOption(gameContext);

        // ACT
        TestConsoleUtils.enterValue(1 + "");
        OptionResult<Void> result = exploreOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());

        verify(character).increaseExperience(CharEvent.FIGHT_DEFEAT);
        verify(character).fight(any());
        verify(character).defence();
        verify(character).vitality();
        verify(character).strength();
        verify(character).speed();

        verify(gameMap).canMoveToNextLocation(character);
        verify(gameStats).fightLost();
        verify(gameContext).getMainCharacter();
        verify(gameContext).getGameMap();
        verify(gameContext).getGameStats();

        verifyNoMoreInteractions(character, gameMap, gameStats, gameContext);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are exploring a current location..."));
        assertTrue(consoleOutput.contains("The character is ready to move closer to the destination! Choose 'Move to the next location' option"));
        assertTrue(consoleOutput.contains("You encountered a beast"));
        assertTrue(consoleOutput.contains("Beast stats: race = "));
        assertTrue(consoleOutput.contains("Your stats:"));
        assertTrue(consoleOutput.contains("1 - Fight"));
        assertTrue(consoleOutput.contains("2 - Run away"));
        assertTrue(consoleOutput.contains("Character lost the encounter. Take a rest in order to heal"));
    }

    @Test
    public void processOptionRunFailure() {
        // ARRANGE
        GameContext gameContext = Mockito.mock(GameContext.class);

        Char character = Mockito.mock(Char.class);
        GameMap gameMap = Mockito.mock(GameMap.class);
        GameStats gameStats = Mockito.mock(GameStats.class);

        when(gameContext.getMainCharacter()).thenReturn(character);
        when(gameContext.getGameMap()).thenReturn(gameMap);
        when(gameContext.getGameStats()).thenReturn(gameStats);

        when(character.runAway(any())).thenReturn(EncounterOutcome.FAILURE);
        when(character.fight(any())).thenReturn(EncounterOutcome.SUCCESS);

        when(gameMap.canMoveToNextLocation(character)).thenReturn(true);

        mockStatic(RandomUtils.class);
        PowerMockito.when(RandomUtils.randomBoolean()).thenReturn(false);

        ExploreOption exploreOption = new ExploreOption(gameContext);

        // ACT
        TestConsoleUtils.enterValue(2 + "");
        OptionResult<Void> result = exploreOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());

        verify(character).increaseExperience(CharEvent.FIGHT_VICTORY);
        verify(character).fight(any());
        verify(character).runAway(any());
        verify(character).defence();
        verify(character).vitality();
        verify(character).strength();
        verify(character).speed();

        verify(gameMap).canMoveToNextLocation(character);
        verify(gameStats).fightWin();
        verify(gameContext).getMainCharacter();
        verify(gameContext).getGameMap();
        verify(gameContext).getGameStats();

        verifyNoMoreInteractions(character, gameMap, gameStats, gameContext);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are exploring a current location..."));
        assertTrue(consoleOutput.contains("The character is ready to move closer to the destination! Choose 'Move to the next location' option"));
        assertTrue(consoleOutput.contains("You encountered a beast"));
        assertTrue(consoleOutput.contains("Beast stats: race = "));
        assertTrue(consoleOutput.contains("Your stats:"));
        assertTrue(consoleOutput.contains("1 - Fight"));
        assertTrue(consoleOutput.contains("2 - Run away"));
        assertTrue(consoleOutput.contains("Character won the fight"));
    }

    @Test
    public void processOptionRunSuccess() {
        // ARRANGE
        GameContext gameContext = Mockito.mock(GameContext.class);

        Char character = Mockito.mock(Char.class);
        GameMap gameMap = Mockito.mock(GameMap.class);
        GameStats gameStats = Mockito.mock(GameStats.class);

        when(gameContext.getMainCharacter()).thenReturn(character);
        when(gameContext.getGameMap()).thenReturn(gameMap);
        when(gameContext.getGameStats()).thenReturn(gameStats);

        when(character.runAway(any())).thenReturn(EncounterOutcome.SUCCESS);

        when(gameMap.canMoveToNextLocation(character)).thenReturn(true);

        mockStatic(RandomUtils.class);
        PowerMockito.when(RandomUtils.randomBoolean()).thenReturn(false);

        ExploreOption exploreOption = new ExploreOption(gameContext);

        // ACT
        TestConsoleUtils.enterValue(2 + "");
        OptionResult<Void> result = exploreOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());

        verify(character).increaseExperience(CharEvent.RUN_SUCCESS);
        verify(character).runAway(any());
        verify(character).defence();
        verify(character).vitality();
        verify(character).strength();
        verify(character).speed();

        verify(gameMap).canMoveToNextLocation(character);
        verify(gameContext).getMainCharacter();
        verify(gameContext).getGameMap();

        verifyNoMoreInteractions(character, gameMap, gameStats, gameContext);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are exploring a current location..."));
        assertTrue(consoleOutput.contains("The character is ready to move closer to the destination! Choose 'Move to the next location' option"));
        assertTrue(consoleOutput.contains("You encountered a beast"));
        assertTrue(consoleOutput.contains("Beast stats: race = "));
        assertTrue(consoleOutput.contains("Your stats:"));
        assertTrue(consoleOutput.contains("1 - Fight"));
        assertTrue(consoleOutput.contains("2 - Run away"));
    }

}