package com.anton4j.darktower;

import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.console.ProgressBar;

/**
 * @author ant
 */
public class GameContext {

    private static final GameContext INSTANCE = new GameContext();

    public static GameContext getInstance() {
        return INSTANCE;
    }

    private ConsoleLines gameMap;
    private Char mainCharacter;

    public ConsoleLines getGameMap() {
        return gameMap;
    }

    public void setGameMap(ConsoleLines gameMap) {
        this.gameMap = gameMap;
    }

    public void setMainCharacter(Char character) {
        this.mainCharacter = character;
    }

    public Char getMainCharacter() {
        return mainCharacter;
    }

}
