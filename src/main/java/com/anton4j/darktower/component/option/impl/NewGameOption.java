package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.character.Gender;
import com.anton4j.darktower.character.Race;
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
public class NewGameOption extends Option<Char> {

    public NewGameOption(int index) {
        super("New game", index);
    }

    @Override
    public OptionResult<Char> processOption() {
        new ConsoleLine("In order to join Ka-Tet and start journey to the Dark tower first create a character", FontColor.BLUE).println();

        Race race = new OptionsScene<>(optionsFromEnumValues(Race.values()), new ConsoleLine("Choose race:"))
              .processScene();

        Gender gender = new OptionsScene<>(optionsFromEnumValues(Gender.values()), new ConsoleLine("Choose gender:"))
              .processScene();

        String name = new InputScene(new ConsoleLine("Enter name:"))
              .processScene();

        new ConsoleLine("Great! Are you ready to defeat all the enemies and reach the Dark Tower? Let's start.", FontColor.BLUE).println();

        return new OptionResult<>(EventResult.Status.SUCCESS, new Char(gender, name, race));
    }


}
