package com.anton4j.darktower;

import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.console.location.GameMap;

/**
 * @author ant
 */
public class GameContext {

    private static final GameContext INSTANCE = new GameContext();

    public static GameContext getInstance() {
        return INSTANCE;
    }

    private GameMap gameMap;
    private Char mainCharacter;
    private GameStats gameStats;

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setMainCharacter(Char character) {
        this.mainCharacter = character;
    }

    public Char getMainCharacter() {
        return mainCharacter;
    }

    public GameStats getGameStats() {
        if (gameStats == null) {
            gameStats = new GameStats();
        }

        return gameStats;
    }
}
