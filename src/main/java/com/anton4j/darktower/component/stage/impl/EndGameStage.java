package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.component.stage.Stage;

/**
 * @author anton
 */
public class EndGameStage extends Stage<Void> {

    EndGameStage() {
        super(null, null, null);
    }

    @Override
    public boolean getCompletionStatus(Void stageResult) {
        return false;
    }

}
