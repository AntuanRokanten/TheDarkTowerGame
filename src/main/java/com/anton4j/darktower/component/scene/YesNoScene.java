package com.anton4j.darktower.component.scene;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.util.Utils;

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
        super(Utils.optionsFromEnumValues(Response.values(), null), sceneTitle);
    }

}
