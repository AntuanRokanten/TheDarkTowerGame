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

    public StartGameStage() {
        super(new InProgressStage(),
              new OptionsScene<>(asList(
                    new NewGameOption(),
                    new LoadGameOption()
              ))
        );
    }

    @Override
    public boolean getCompletionStatus(Char character) {
        GameContext.getInstance().setMainCharacter(character);
        return true;
    }

}