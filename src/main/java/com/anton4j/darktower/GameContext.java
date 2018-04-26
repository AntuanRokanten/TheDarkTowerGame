package com.anton4j.darktower;

import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.console.location.GameMap;

import java.io.Serializable;

/**
 * Context of the whole game.
 *
 * @author ant
 */
public class GameContext implements Serializable {

    /**
     * Map of the game.
     */
    private GameMap gameMap;

    /**
     * Statistics of the game.
     */
    private GameStats gameStats;

    /**
     * Game main character.
     */
    private Char mainCharacter;

    public GameMap getGameMap() {
        return gameMap;
    }

    public Char getMainCharacter() {
        return mainCharacter;
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setGameStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }

    public void setMainCharacter(Char mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public void copyFrom(GameContext gameContext) {
        this.mainCharacter = gameContext.getMainCharacter();
        this.gameMap = gameContext.getGameMap();
        this.gameStats = gameContext.getGameStats();
    }

    public boolean isInitialized() {
        return gameMap != null && mainCharacter != null && gameStats != null;
    }

}
