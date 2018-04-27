package com.anton4j.darktower.game;

import java.io.Serializable;

/**
 * @author ant
 */
public class GameStats implements Serializable {

    private int locationsExplored = 0;
    private int fightWins = 0;
    private int fightLosses = 0;

    public void locationExplored() {
        locationsExplored++;
    }

    public void fightWin() {
        fightWins++;
    }

    public void fightLost() {
        fightLosses++;
    }

    @Override
    public String toString() {
        return "Locations explored = " + locationsExplored +
              ", Fight wins = " + fightWins +
              ", Fight losses = " + fightLosses;
    }

}
