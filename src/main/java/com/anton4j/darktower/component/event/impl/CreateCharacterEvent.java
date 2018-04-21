package com.anton4j.darktower.component.event.impl;

import com.anton4j.darktower.character.Race;
import com.anton4j.darktower.component.event.Event;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.ValueOption;
import com.anton4j.darktower.component.scene.Scene;
import com.anton4j.darktower.console.ConsoleLine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author anton
 */
public class CreateCharacterEvent implements Event {

    @Override
    public EventResult process() {
        Race[] races = Race.values();

        List<Option> raceOptions = new ArrayList<>();
        for (int i = 0; i < races.length; i++) {
            Race race = races[i];

            String raceName = race.name();
            String displayText = raceName.substring(0, 1).toUpperCase() + raceName.substring(1).toLowerCase().replaceAll("_", " ");

            ValueOption<Race> option = new ValueOption<>(race, displayText, i + 1);
            raceOptions.add(option);
        }

        new Scene(raceOptions, new ConsoleLine("Choose race:")).processScene();
        return null;
    }

}
