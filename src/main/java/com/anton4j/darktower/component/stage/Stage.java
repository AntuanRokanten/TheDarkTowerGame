package com.anton4j.darktower.component.stage;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.scene.Scene;
import com.anton4j.darktower.console.ConsoleLine;

/**
 * @author anton
 */
public abstract class Stage<T> {

    private final Stage next;
    private final ConsoleLine stageIntro;
    private boolean stageCompleted = false;

    public Stage(Stage next, ConsoleLine stageIntro) {
        this.next = next;
        this.stageIntro = stageIntro;
    }

    public Stage(Stage next) {
        this(next, null);
    }

    public GameContext processScene(GameContext gameContext) {
        if (stageIntro != null) {
            stageIntro.println();
        }

        T sceneResult = stageScene(gameContext).processScene();
        stageCompleted = getCompletionStatus(sceneResult, gameContext);

        return gameContext;
    }

    public Stage nextStage() {
        return next;
    }

    public abstract Scene<T> stageScene(GameContext gameContext);

    public boolean stageCompleted() {
        return stageCompleted;
    }

    public abstract boolean getCompletionStatus(T stageResult, GameContext gameContext);

}