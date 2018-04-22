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

    InProgressStage() {
        super(new EndGameStage(),
              new OptionsScene(asList(
                    new ExploreOption(1),
                    new TakeRestOption(2),
                    new MoveOption(3),
                    new CharInfoOption(4),
                    new PrintMapOption(5),
                    new SaveExitOption(6)
              )));
    }

    @Override
    public boolean getCompletionStatus(RoundOutcome stageResult) {
        return false;
    }

}
