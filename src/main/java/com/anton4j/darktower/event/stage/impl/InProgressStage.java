package com.anton4j.darktower.event.stage.impl;

import com.anton4j.darktower.event.option.impl.*;
import com.anton4j.darktower.event.stage.Stage;

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

}
