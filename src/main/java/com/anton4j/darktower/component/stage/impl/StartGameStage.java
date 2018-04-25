package com.anton4j.darktower.component.stage.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.component.option.impl.LoadGameOption;
import com.anton4j.darktower.component.option.impl.NewGameOption;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.component.stage.Stage;

import static java.util.Arrays.asList;

/**
 * @author anton
 */
public class StartGameStage extends Stage<Char> {

    public StartGameStage(GameContext gameContext) {
        super(new InProgressStage(gameContext),
              new OptionsScene<>(asList(
                    new NewGameOption(gameContext),
                    new LoadGameOption(gameContext)
              )),
              gameContext
        );
    }

    @Override
    public boolean getCompletionStatus(Char character) {
        gameContext.setMainCharacter(character);
        return true;
    }

}