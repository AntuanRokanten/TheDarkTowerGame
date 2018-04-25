package com.anton4j.darktower;

/**
 * @author ant
 */
public class GameStats {

    private int locationsExplored = 0;
    private int fightWins = 0;
    private int fightLosses = 0;

    public void locationExplored() {
        locationsExplored++;
    }

    public void fightWin() {
        fightWins++;
    }

    public void fighLost() {
        fightLosses++;
    }

    @Override
    public String toString() {
        return "Locations explored = " + locationsExplored +
              ", Fight wins = " + fightWins +
              ", Fight losses = " + fightLosses;
    }

}
