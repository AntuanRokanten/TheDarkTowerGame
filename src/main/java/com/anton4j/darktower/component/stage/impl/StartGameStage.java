package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.component.option.impl.LoadGameOption;
import com.anton4j.darktower.component.option.impl.NewGameOption;
import com.anton4j.darktower.component.scene.Scene;
import com.anton4j.darktower.component.stage.Stage;
import com.anton4j.darktower.console.ConsoleLine;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class StartGameStage extends Stage {

    public StartGameStage() {
        super(new InProgressStage(), new Scene(asList(
              new NewGameOption(1),
              new LoadGameOption(2)
        ), new ConsoleLine("TEST")));
    }

    @Override
    public boolean stageCompleted() {
        return false; // char created or game loaded
    }

}