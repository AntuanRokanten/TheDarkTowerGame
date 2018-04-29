package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.character.CharRace;
import com.anton4j.darktower.game.character.Gender;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.component.scene.InputScene;
import com.anton4j.darktower.game.component.scene.OptionsScene;
import com.anton4j.darktower.game.component.util.OptionsFromEnum;

/**
 * Option that creates a new game.
 *
 * @author ant
 */
public class NewGameOption extends Option<GameContext> {

    public NewGameOption(GameContext gameContext) {
        super(gameContext, "New game");
    }

    @Override
    protected OptionResult<GameContext> processOptionForResult() {
        new ConsoleLine("In order to join Ka-Tet and start journey to the Dark end-banner first create a character", FontColor.BLUE).println();

        CharRace charRace = new OptionsScene<>(new OptionsFromEnum<>(CharRace.values()).options(gameContext), new ConsoleLine("Choose race:"))
              .processScene();

        Gender gender = new OptionsScene<>(new OptionsFromEnum<>(Gender.values()).options(gameContext), new ConsoleLine("Choose gender:"))
              .processScene();

        String name = new InputScene(new ConsoleLine("Enter name:"))
              .processScene();

        new ConsoleLine("Great! Are you ready to defeat all the enemies and reach the Dark Tower? Let's start.", FontColor.BLUE).println();

        Char character = new Char.CharBuilder()
              .withGender(gender)
              .withName(name)
              .withRace(charRace)
              .build();

        gameContext.setMainCharacter(character);
        return OptionResult.success();
    }

}
