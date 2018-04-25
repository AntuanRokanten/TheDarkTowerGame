package com.anton4j.darktower.component.stage;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.console.ConsoleLine;

/**
 * @author anton
 */
public abstract class Stage<T> {

    private final Stage next;
    private final OptionsScene<T> optionsScene;
    private final ConsoleLine stageIntro;
    private boolean stageCompleted = false;
    protected final GameContext gameContext;

    public Stage(Stage next, OptionsScene<T> optionsScene, ConsoleLine stageIntro, GameContext gameContext) {
        this.next = next;
        this.optionsScene = optionsScene;
        this.stageIntro = stageIntro;
        this.gameContext = gameContext;
    }

    public Stage(Stage next, OptionsScene<T> optionsScene, GameContext gameContext) {
        this(next, optionsScene, null, gameContext);
    }

    public void processScene() {
        if (stageIntro != null) {
            stageIntro.println();
        }

        T o = optionsScene.processScene();
        stageCompleted = getCompletionStatus(o);
    }

    public Stage nextStage() {
        return next;
    }

    public boolean stageCompleted() {
        return stageCompleted;
    }

    public abstract boolean getCompletionStatus(T stageResult);

}