package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.stage.Stage;

import java.util.List;

/**
 * @author anton
 */
public class EndGameStage extends Stage {

    public EndGameStage(List<Option> options) {
        super(options);
    }

    @Override
    public boolean stageCompleted() {
        return true;
    }

}
