package com.anton4j.darktower.component.stage;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.scene.Scene;
import com.anton4j.darktower.console.ConsoleLine;

/**
 * @author anton
 */
public abstract class GameStage<T> {

    private final GameStage next;
    private final ConsoleLine stageIntro;

    private boolean stageCompleted;

    public GameStage(GameStage next, ConsoleLine stageIntro) {
        this.next = next;
        this.stageIntro = stageIntro;
    }

    public GameStage(GameStage next) {
        this(next, null);
    }

    public void processScene(GameContext gameContext) {
        if (stageIntro != null) {
            stageIntro.println();
        }

        T sceneResult = stageScene(gameContext).processScene(); // todo result is not used
        stageCompleted = getCompletionStatus(gameContext);
    }

    public GameStage nextStage() {
        return next;
    }

    public abstract Scene<T> stageScene(GameContext gameContext);

    public boolean stageCompleted() {
        return stageCompleted;
    }

    public abstract boolean getCompletionStatus(GameContext gameContext);

}