package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.scene.Scene;
import com.anton4j.darktower.component.stage.Stage;

/**
 * @author anton
 */
public class EndGameStage extends Stage<Void> {

    public EndGameStage() {
        super(null);
    }

    @Override
    public Scene<Void> stageScene(GameContext gameContext) {
        return null;
    }

    @Override
    public boolean getCompletionStatus(Void stageResult, GameContext gameContext) {
        return false;
    }
}
