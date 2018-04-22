//package com.anton4j.darktower.component.event.impl;
//
//import com.anton4j.darktower.character.Char;
//import com.anton4j.darktower.character.Gender;
//import com.anton4j.darktower.character.Race;
//import com.anton4j.darktower.component.event.Event;
//import com.anton4j.darktower.component.event.EventResult;
//import com.anton4j.darktower.component.option.Option;
//import com.anton4j.darktower.component.option.ValueOption;
//import com.anton4j.darktower.component.scene.InputScene;
//import com.anton4j.darktower.component.scene.OptionsScene;
//import com.anton4j.darktower.console.ConsoleLine;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author anton
// */
//public class CreateCharacterEvent implements Event<Char> {
//
//    @Override
//    public EventResult<Char> process() {
//        List<Option<Race>> options = optionsFromEnumValues(Race.values());
//
//        Race race = new OptionsScene<>(options, new ConsoleLine("Choose race:"))
//              .processScene();
//
//        Gender gender = new OptionsScene<Gender>(optionsFromEnumValues(Gender.values()), new ConsoleLine("Choose gender:"))
//              .processScene();
//
//        String name = new InputScene(new ConsoleLine("Enter name:"))
//              .processScene();
//
//        return EventResult.success(new Char(gender, name, race));
//    }
//
//    private <T extends Enum> List<Option<T>> optionsFromEnumValues(T[] enumValues) {
//        List<Option<T>> raceOptions = new ArrayList<>();
//        for (int i = 0; i < enumValues.length; i++) {
//            T value = enumValues[i];
//
//            String raceName = value.name();
//            String displayText = raceName.substring(0, 1).toUpperCase() + raceName.substring(1).toLowerCase().replaceAll("_", " ");
//
//            ValueOption<T> option = new ValueOption<>(value, displayText, i + 1);
//            raceOptions.add(option);
//        }
//        return raceOptions;
//    }
//
//}
