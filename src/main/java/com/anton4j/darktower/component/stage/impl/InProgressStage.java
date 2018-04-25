package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.character.RoundOutcome;
import com.anton4j.darktower.component.option.impl.*;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.component.stage.Stage;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class InProgressStage extends Stage<RoundOutcome> {

    // todo create chain of locations and put it into context then here put true only if tower is reached and man in black is defeated!

    InProgressStage() {
        super(new EndGameStage(),
              new OptionsScene(asList(
                    new ExploreOption(),
                    new TakeRestOption(),
                    new MoveOption(),
                    new CharInfoOption(),
                    new PrintMapOption(),
                    new SaveExitOption()
              )));
    }

    @Override
    public boolean getCompletionStatus(RoundOutcome stageResult) {
        return false;
    }

}