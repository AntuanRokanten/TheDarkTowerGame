package com.anton4j.darktower.component.stage;

import com.anton4j.darktower.component.scene.OptionsScene;

/**
 * @author anton
 */
public abstract class Stage {

    private final Stage next;
    private final OptionsScene optionsScene;

    public Stage(Stage next, OptionsScene optionsScene) {
        this.next = next;
        this.optionsScene = optionsScene;
    }

    // todo add process scene method and based on result set stage completed
    public void processScene() {
        Object o = optionsScene.processScene();
        System.err.println();
    }

    public OptionsScene getOptionsScene() {
        return optionsScene;
    }

    public Stage nextStage() {
        return next;
    }

    public abstract boolean stageCompleted();

}