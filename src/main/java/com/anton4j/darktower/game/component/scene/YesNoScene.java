package com.anton4j.darktower.game.component.scene;

import com.anton4j.darktower.game.component.util.OptionsFromEnum;
import com.anton4j.darktower.console.ConsoleLine;

/**
 * @author ant
 */
public class YesNoScene extends OptionsScene<YesNoScene.Response> {

    public enum Response {
        YES, NO;

        public boolean isPositive() {
            return this == YES;
        }
    }

    public YesNoScene(ConsoleLine sceneTitle) {
        super(new OptionsFromEnum<>(Response.values()).options(null), sceneTitle);
    }

}
