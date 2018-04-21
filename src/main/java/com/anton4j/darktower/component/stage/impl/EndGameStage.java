package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.component.stage.Stage;

/**
 * @author anton
 */
public class EndGameStage extends Stage {

    public EndGameStage() {
        super(null, null);
    }

    @Override
    public boolean stageCompleted() {
        return true;
    }

}
