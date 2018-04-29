package com.anton4j.darktower.game;

import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.location.GameMap;

import java.io.Serializable;
import java.util.Objects;

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

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Char getMainCharacter() {
        return mainCharacter;
    }

    public void setMainCharacter(Char mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public void setGameStats(GameStats gameStats) {
        this.gameStats = gameStats;
    }

    /**
     * Copies context from specified.
     *
     * @param gameContext context from which to copy.
     */
    public void copyFrom(GameContext gameContext) {
        this.mainCharacter = gameContext.getMainCharacter();
        this.gameMap = gameContext.getGameMap();
        this.gameStats = gameContext.getGameStats();
    }

    /**
     * @return true if context was fully initialized; false otherwise.
     */
    public boolean isInitialized() {
        return gameMap != null && mainCharacter != null && gameStats != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameContext that = (GameContext) o;
        return Objects.equals(gameMap, that.gameMap) &&
              Objects.equals(gameStats, that.gameStats) &&
              Objects.equals(mainCharacter, that.mainCharacter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameMap, gameStats, mainCharacter);
    }
}
