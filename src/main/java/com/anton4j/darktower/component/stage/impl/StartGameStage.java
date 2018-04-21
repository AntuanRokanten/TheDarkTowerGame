package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.component.option.impl.LoadGameOption;
import com.anton4j.darktower.component.option.impl.NewGameOption;
import com.anton4j.darktower.component.stage.Stage;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class StartGameStage extends Stage {

    public StartGameStage() {
        super(asList(
              new NewGameOption(0),
              new LoadGameOption(1)
        ));
    }

    @Override
    public boolean stageCompleted() {
        return false; // char created or game loaded
    }
}
