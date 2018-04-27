package com.anton4j.darktower.game.component.scene;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;

/**
 * Scene for retrieving value typed to the console.
 *
 * @author ant
 */
public class InputScene extends Scene<String> {

    public InputScene(ConsoleLine sceneTitle) {
        super(sceneTitle);
    }

    @Override
    public String processScene() {
        sceneTitle.println();
        String line = ConsoleUtils.readLine().trim();

        if (line.isEmpty()) {
            new ConsoleLine("Input value cannot be empty", FontColor.YELLOW).println();
            return processScene();
        }

        return line;
    }
}
