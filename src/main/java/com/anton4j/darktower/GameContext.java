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
    private final GameMap gameMap;

    /**
     * Statistics of the game.
     */
    private final GameStats gameStats;

    /**
     * Game main character.
     */
    private Char mainCharacter;

    public GameContext(GameMap gameMap) {
        this.gameMap = gameMap;
        this.gameStats = new GameStats();
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setMainCharacter(Char character) {
        this.mainCharacter = character;
    }

    public Char getMainCharacter() {
        return mainCharacter;
    }

    public GameStats getGameStats() {
        return gameStats;
    }
}
