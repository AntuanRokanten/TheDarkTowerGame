package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.character.CharRace;
import com.anton4j.darktower.character.Gender;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.component.scene.InputScene;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

import static com.anton4j.darktower.util.Utils.optionsFromEnumValues;

/**
 * @author anton
 */
public class NewGameOption extends Option<GameContext> {

    public NewGameOption(GameContext gameContext) {
        super(gameContext, "New game");
    }

    @Override
    public OptionResult<GameContext> processOption() {
        new ConsoleLine("In order to join Ka-Tet and start journey to the Dark end-banner first create a character", FontColor.BLUE).println();

        CharRace charRace = new OptionsScene<>(optionsFromEnumValues(CharRace.values(), gameContext), new ConsoleLine("Choose race:"))
              .processScene();

        Gender gender = new OptionsScene<>(optionsFromEnumValues(Gender.values(), gameContext), new ConsoleLine("Choose gender:"))
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
        return new OptionResult<>(EventResult.Status.SUCCESS, gameContext);
    }

}
