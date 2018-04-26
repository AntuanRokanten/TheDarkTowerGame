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

    protected Scene() {
        this(null);
    }

    public abstract T processScene(); // todo return is not used

}
