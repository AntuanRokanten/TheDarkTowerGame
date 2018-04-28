package com.anton4j.darktower.game.character;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.GameStats;
import org.junit.Test;
import org.mockito.Mockito;

import static com.anton4j.darktower.game.character.CharEvent.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * @author ant
 */
public class CharEventTest {

    @Test
    public void experienceFactor() {
        assertEquals(40f, FIGHT_VICTORY.experienceFactor(), 0);
        assertEquals(30f, FIGHT_DEFEAT.experienceFactor(), 0);
        assertEquals(20f, EXPLORATION.experienceFactor(), 0);
        assertEquals(15f, RUN_SUCCESS.experienceFactor(), 0);
    }

    @Test
    public void logEventFightVictory() {
        // ARRANGE
        GameContext gameContext = mock(GameContext.class);
        GameStats gameStats = mock(GameStats.class);

        Mockito.when(gameContext.getGameStats()).thenReturn(gameStats);

        // ACT
        FIGHT_VICTORY.logEvent(gameContext);

        // ASSERT
        verify(gameStats).fightWin();
        verify(gameContext, times(1)).getGameStats();
        verifyNoMoreInteractions(gameContext);
    }

    @Test
    public void logEventFightDefeat() {
        // ARRANGE
        GameContext gameContext = mock(GameContext.class);
        GameStats gameStats = mock(GameStats.class);

        Mockito.when(gameContext.getGameStats()).thenReturn(gameStats);

        // ACT
        FIGHT_DEFEAT.logEvent(gameContext);

        // ASSERT
        verify(gameStats).fightLost();
        verify(gameContext, times(1)).getGameStats();
        verifyNoMoreInteractions(gameContext);
    }

    @Test
    public void logEventAreaExplored() {
        // ARRANGE
        GameContext gameContext = mock(GameContext.class);
        GameStats gameStats = mock(GameStats.class);

        Mockito.when(gameContext.getGameStats()).thenReturn(gameStats);

        // ACT
        EXPLORATION.logEvent(gameContext);

        // ASSERT
        verify(gameStats).areaExplored();
        verify(gameContext, times(1)).getGameStats();
        verifyNoMoreInteractions(gameContext);
    }

    @Test
    public void logEventRunAway() {
        // ARRANGE
        GameContext gameContext = mock(GameContext.class);
        GameStats gameStats = mock(GameStats.class);

        Mockito.when(gameContext.getGameStats()).thenReturn(gameStats);

        // ACT
        RUN_SUCCESS.logEvent(gameContext);

        // ASSERT
        // no logging
        verifyNoMoreInteractions(gameContext);
    }

}