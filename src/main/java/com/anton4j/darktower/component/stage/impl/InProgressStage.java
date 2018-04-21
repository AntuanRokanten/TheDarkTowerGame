package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.component.option.impl.*;
import com.anton4j.darktower.component.stage.Stage;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class InProgressStage extends Stage {

    public InProgressStage() {
        super(asList(
              new ExploreOption(0),
              new TakeRestOption(1),
              new MoveOption(2),
              new CharInfoOption(3),
              new PrintMapOption(4),
              new SaveExitOption(5)
        ));
    }

    @Override
    public boolean stageCompleted() {
        return false; // boss defeated
    }
}
