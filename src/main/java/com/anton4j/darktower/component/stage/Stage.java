package com.anton4j.darktower.component.stage;

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

    public Stage(Stage next, OptionsScene<T> optionsScene, ConsoleLine stageIntro) {
        this.next = next;
        this.optionsScene = optionsScene;
        this.stageIntro = stageIntro;
    }

    public Stage(Stage next, OptionsScene<T> optionsScene) {
        this(next, optionsScene, null);
    }

    // todo add process scene method and based on result set stage completed
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