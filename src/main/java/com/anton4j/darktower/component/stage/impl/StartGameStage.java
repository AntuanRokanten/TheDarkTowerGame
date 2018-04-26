package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.option.impl.LoadGameOption;
import com.anton4j.darktower.component.option.impl.NewGameOption;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.component.scene.Scene;
import com.anton4j.darktower.component.stage.GameStage;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class StartGameStage extends GameStage<GameContext> {

    public StartGameStage() {
        super(new InProgressStage());
    }

    @Override
    public boolean getCompletionStatus(GameContext gameContext) {
        return gameContext.isInitialized();
    }

    @Override
    public Scene<GameContext> stageScene(GameContext gameContext) {
        return new OptionsScene<>(asList(
              new NewGameOption(gameContext),
              new LoadGameOption(gameContext)
        ));
    }

}