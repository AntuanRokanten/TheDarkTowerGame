package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.GameStats;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.location.GameMap;
import com.anton4j.darktower.game.location.Location;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
public class MoveOptionTest extends OutStreamsInterceprtorTest {

   @Test
   public void processOptionCanMove() throws Exception {

      GameContext context = Mockito.mock(GameContext.class);
      Char character = Mockito.mock(Char.class);
      GameMap gameMap = Mockito.mock(GameMap.class);
      GameStats gameStats = Mockito.mock(GameStats.class);

      Mockito.when(context.getMainCharacter()).thenReturn(character);
      Mockito.when(context.getGameMap()).thenReturn(gameMap);
      Mockito.when(context.getGameStats()).thenReturn(gameStats);

      Mockito.when(gameMap.canMoveToNextLocation(character)).thenReturn(true);

      MoveOption infoOption = new MoveOption(context);

      // ACT
      OptionResult<Void> result = infoOption.processOption();

      // ASSERT
      assertTrue(result.isSuccess());

      Mockito.verify(context).getMainCharacter();
      Mockito.verify(context).getGameMap();
      Mockito.verify(context).getGameStats();
      Mockito.verify(gameStats).areaExplored();
      Mockito.verify(gameMap).canMoveToNextLocation(character);
      Mockito.verify(gameMap).moveToNextLocation(character);
      Mockito.verifyNoMoreInteractions(context, character, gameMap, gameStats);
   }

   @Test
   public void processOptionCanNotMove() throws Exception {
      int accessLevel = new Random().nextInt();

      GameContext context = Mockito.mock(GameContext.class);
      Char character = Mockito.mock(Char.class);
      GameMap gameMap = Mockito.mock(GameMap.class);
      GameStats gameStats = Mockito.mock(GameStats.class);
      Location currentLocation = Mockito.mock(Location.class);
      Location nextLocation = Mockito.mock(Location.class);

      Mockito.when(context.getMainCharacter()).thenReturn(character);
      Mockito.when(context.getGameMap()).thenReturn(gameMap);
      Mockito.when(context.getGameStats()).thenReturn(gameStats);

      Mockito.when(gameMap.canMoveToNextLocation(character)).thenReturn(false);
      Mockito.when(gameMap.currentLocation()).thenReturn(currentLocation);
      Mockito.when(currentLocation.next()).thenReturn(nextLocation);
      Mockito.when(nextLocation.accessLevel()).thenReturn(accessLevel);

      MoveOption infoOption = new MoveOption(context);

      // ACT
      OptionResult<Void> result = infoOption.processOption();

      // ASSERT
      assertTrue(result.isSuccess());

      Mockito.verify(context).getMainCharacter();
      Mockito.verify(context).getGameMap();
      Mockito.verify(gameMap).canMoveToNextLocation(character);
      Mockito.verify(gameMap).currentLocation();
      Mockito.verify(currentLocation).next();
      Mockito.verify(nextLocation).accessLevel();
      Mockito.verifyNoMoreInteractions(context, character, gameMap, gameStats, currentLocation, nextLocation);

      String consoleOutput = outContent.toString();
      assertTrue(consoleOutput.contains("You cannot move to the next location. Needed level is " + accessLevel));
   }

}