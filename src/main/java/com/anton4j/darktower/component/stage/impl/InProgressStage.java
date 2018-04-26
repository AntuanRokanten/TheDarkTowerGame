package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.RoundOutcome;
import com.anton4j.darktower.component.option.impl.*;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.component.scene.Scene;
import com.anton4j.darktower.component.stage.Stage;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class InProgressStage extends Stage<RoundOutcome> {

    InProgressStage() {
        super(new EndGameStage());
    }

    @Override
    public boolean getCompletionStatus(RoundOutcome stageResult, GameContext gameContext) {
        return gameContext.getGameMap().isFinalDestination();
    }

    @Override
    public Scene<RoundOutcome> stageScene(GameContext gameContext) {
        return new OptionsScene(asList(
              new ExploreOption(gameContext),
              new TakeRestOption(gameContext),
              new MoveOption(gameContext),
              new CharInfoOption(gameContext),
              new PrintMapOption(gameContext),
              new SaveExitOption(gameContext)
        ));
    }
}