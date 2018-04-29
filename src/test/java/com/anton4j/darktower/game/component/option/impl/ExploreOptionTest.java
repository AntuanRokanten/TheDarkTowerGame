package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.GameStats;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.location.GameMap;
import com.anton4j.darktower.util.TestConsoleUtils;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author ant
 */
public class ExploreOptionTest {

    @Test
    public void processOption() {
        // ARRANGE
        GameContext gameContext = Mockito.mock(GameContext.class);

        Char character = Mockito.mock(Char.class);
        GameMap gameMap = Mockito.mock(GameMap.class);
        GameStats gameStats = Mockito.mock(GameStats.class);

        when(gameContext.getMainCharacter()).thenReturn(character);
        when(gameContext.getGameMap()).thenReturn(gameMap);
        when(gameContext.getGameStats()).thenReturn(gameStats);

        when(gameMap.canMoveToNextLocation(character)).thenReturn(true);

        ExploreOption exploreOption = new ExploreOption(gameContext);

        // ACT
        TestConsoleUtils.enterValue(1 + "");
        OptionResult<Void> result = exploreOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());
        // todo complete // todo inject random
//        verify(character).takeRest();
//        verifyNoMoreInteractions(character);
    }

}