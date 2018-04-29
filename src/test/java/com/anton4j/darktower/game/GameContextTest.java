package com.anton4j.darktower.game;

import com.anton4j.darktower.game.location.GameMap;
import com.anton4j.darktower.util.TestCharUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ant
 */
public class GameContextTest {

    @Test
    public void copyFrom() {
        // ARRANGE
        GameContext initialContext = new GameContext();

        GameContext copyContext = new GameContext();
        copyContext.setGameMap(new GameMap(null, null));
        copyContext.setGameStats(new GameStats());
        copyContext.setMainCharacter(TestCharUtils.randomChar());

        // ACT
        initialContext.copyFrom(copyContext);

        // ASSERT
        assertEquals(initialContext, copyContext);
    }

    @Test
    public void isInitialized() {
        // ACT
        GameContext gameContext = new GameContext();

        // ASSERT
        assertFalse(gameContext.isInitialized());

        // ACT
        gameContext.setMainCharacter(TestCharUtils.randomChar());

        // ASSERT
        assertFalse(gameContext.isInitialized());

        // ACT
        gameContext.setGameStats(new GameStats());

        // ASSERT
        assertFalse(gameContext.isInitialized());

        // ACT
        gameContext.setGameMap(new GameMap(null, null));

        // ASSERT
        assertTrue(gameContext.isInitialized());
    }
}