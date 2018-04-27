package com.anton4j.darktower.game.component.scene;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.game.component.util.OptionsFromEnum;

/**
 * Scene for selecting YES/NO options.
 *
 * @author ant
 */
public class YesNoScene extends OptionsScene<YesNoScene.Response> {

    public YesNoScene(ConsoleLine sceneTitle) {
        super(new OptionsFromEnum<>(Response.values()).options(null), sceneTitle);
    }

    public enum Response {
        YES, NO;

        public boolean isPositive() {
            return this == YES;
        }
    }

}
