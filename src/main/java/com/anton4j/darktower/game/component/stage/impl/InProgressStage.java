package com.anton4j.darktower.game.component.stage.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.option.impl.*;
import com.anton4j.darktower.game.component.scene.OptionsScene;
import com.anton4j.darktower.game.component.scene.Scene;
import com.anton4j.darktower.game.component.stage.GameStage;

import static java.util.Arrays.asList;

/**
 * Stage for game process.
 *
 * @author ant
 */
public class InProgressStage extends GameStage<Void> {

    InProgressStage() {
        super(null);
    }

    @Override
    public boolean getCompletionStatus(GameContext gameContext) {
        return gameContext.getGameMap().isFinalDestination();
    }

    @Override
    public Scene<Void> stageScene(GameContext gameContext) {
        return new OptionsScene<>(
              asList(
                    new ExploreOption(gameContext),
                    new TakeRestOption(gameContext),
                    new MoveOption(gameContext),
                    new CharInfoOption(gameContext),
                    new PrintMapOption(gameContext),
                    new SaveOption(gameContext),
                    new ExitOption(gameContext)
              )
        );
    }
}