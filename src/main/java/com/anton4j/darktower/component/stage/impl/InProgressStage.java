package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.RoundOutcome;
import com.anton4j.darktower.component.option.impl.*;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.component.stage.Stage;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class InProgressStage extends Stage<RoundOutcome> {

    InProgressStage(GameContext gameContext) {
        super(new EndGameStage(),
              new OptionsScene(asList(
                    new ExploreOption(gameContext),
                    new TakeRestOption(gameContext),
                    new MoveOption(gameContext),
                    new CharInfoOption(gameContext),
                    new PrintMapOption(gameContext),
                    new SaveExitOption(gameContext)
              )),
              gameContext);
    }

    @Override
    public boolean getCompletionStatus(RoundOutcome stageResult) {
        return gameContext.getGameMap().isFinalDestination();
    }

}