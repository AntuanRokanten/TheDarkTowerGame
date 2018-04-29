package com.anton4j.darktower.game;

import java.io.Serializable;
import java.util.Objects;

/**
 * Game statistics.
 *
 * @author ant
 */
public class GameStats implements Serializable {

    /**
     * Number of areas explored.
     */
    private int areasExplored = 0;

    /**
     * Number of fight wins.
     */
    private int fightWins = 0;

    /**
     * Number of fight loses.
     */
    private int fightLoses = 0;

    /**
     * Increments number of areas explored.
     */
    public void areaExplored() {
        areasExplored++;
    }

    /**
     * Increments number of fight wins.
     */
    public void fightWin() {
        fightWins++;
    }

    /**
     * Increments number of fight loses.
     */
    public void fightLost() {
        fightLoses++;
    }

    @Override
    public String toString() {
        return "Areas explored = " + areasExplored +
              ", Fight wins = " + fightWins +
              ", Fight loses = " + fightLoses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameStats gameStats = (GameStats) o;
        return areasExplored == gameStats.areasExplored &&
              fightWins == gameStats.fightWins &&
              fightLoses == gameStats.fightLoses;
    }

    @Override
    public int hashCode() {

        return Objects.hash(areasExplored, fightWins, fightLoses);
    }
}
