package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.component.option.impl.LoadGameOption;
import com.anton4j.darktower.component.option.impl.NewGameOption;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.component.scene.Scene;
import com.anton4j.darktower.component.stage.Stage;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class StartGameStage extends Stage<Char> {

    public StartGameStage() {
        super(new InProgressStage());
    }

    @Override
    public boolean getCompletionStatus(Char character, GameContext gameContext) {
        gameContext.setMainCharacter(character);
        return true; // todo
    }

    @Override
    public Scene<Char> stageScene(GameContext gameContext) {
        return new OptionsScene<>(asList(
              new NewGameOption(gameContext),
              new LoadGameOption(gameContext)
        ));
    }
}