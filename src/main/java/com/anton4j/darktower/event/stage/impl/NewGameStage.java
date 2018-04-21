package com.anton4j.darktower.event.stage.impl;

import com.anton4j.darktower.event.option.impl.LoadGameOption;
import com.anton4j.darktower.event.option.impl.NewGameOption;
import com.anton4j.darktower.event.stage.Stage;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class NewGameStage extends Stage {

    public NewGameStage() {
        super(asList(
              new NewGameOption(0),
              new LoadGameOption(1)
        ));
    }

}
