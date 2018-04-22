package com.anton4j.darktower.component.scene;

import com.anton4j.darktower.console.ConsoleLine;

/**
 * @author ant
 */
public abstract class Scene<T> {

    final ConsoleLine sceneTitle;

    protected Scene(ConsoleLine sceneTitle) {
        this.sceneTitle = sceneTitle;
    }

    public abstract T processScene();

}
