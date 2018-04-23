package com.anton4j.darktower;

/**
 * @author ant
 */
public class GameContext {

    private static final GameContext INSTANCE = new GameContext();

    public static GameContext getInstance() {
        return INSTANCE;
    }

    private ConsoleLines gameMap;

    public ConsoleLines getGameMap() {
        return gameMap;
    }

    public void setGameMap(ConsoleLines gameMap) {
        this.gameMap = gameMap;
    }

}
