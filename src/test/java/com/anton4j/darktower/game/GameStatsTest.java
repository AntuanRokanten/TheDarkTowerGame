package com.anton4j.darktower.game;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ant
 */
public class GameStatsTest {

    private final GameStats gameStats = new GameStats();

    @Test
    public void areaExplored() throws Exception {
        int valueBefore = (int) FieldUtils.readDeclaredField(gameStats, "areasExplored", true);

        // ACT
        gameStats.areaExplored();

        // ASSERT
        int valueAfter = (int) FieldUtils.readDeclaredField(gameStats, "areasExplored", true);
        assertEquals(1, valueAfter - valueBefore);
    }

    @Test
    public void fightWin() throws Exception {
        int valueBefore = (int) FieldUtils.readDeclaredField(gameStats, "fightWins", true);

        // ACT
        gameStats.fightWin();

        // ASSERT
        int valueAfter = (int) FieldUtils.readDeclaredField(gameStats, "fightWins", true);
        assertEquals(1, valueAfter - valueBefore);
    }

    @Test
    public void fightLost() throws Exception {
        int valueBefore = (int) FieldUtils.readDeclaredField(gameStats, "fightLoses", true);

        // ACT
        gameStats.fightLost();

        // ASSERT
        int valueAfter = (int) FieldUtils.readDeclaredField(gameStats, "fightLoses", true);
        assertEquals(1, valueAfter - valueBefore);
    }

    @Test
    public void toStringTest() {
        // ARRANGE
        gameStats.fightLost();
        gameStats.fightWin();
        gameStats.areaExplored();

        // ACT
        String stringValue = gameStats.toString();

        // ASSERT
        assertEquals("Areas explored = 1, Fight wins = 1, Fight loses = 1", stringValue);
    }
}