package com.anton4j.darktower.game.component.scene;

import com.anton4j.darktower.console.ConsoleLine;

/**
 * Scene that is displayed to the player.
 *
 * @author ant
 */
public abstract class Scene<T> {

    /**
     * Title of the scene.
     */
    final ConsoleLine sceneTitle;

    Scene(ConsoleLine sceneTitle) {
        this.sceneTitle = sceneTitle;
    }

    Scene() {
        this(null);
    }

    public abstract T processScene();

}
