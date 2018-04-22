package com.anton4j.darktower.component.scene;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;

/**
 * @author ant
 */
public class InputScene extends Scene<String> {

    public InputScene(ConsoleLine sceneTitle) {
        super(sceneTitle);
    }

    @Override
    public String processScene() {
        sceneTitle.println();
        return ConsoleUtils.readLine();
    }
}
