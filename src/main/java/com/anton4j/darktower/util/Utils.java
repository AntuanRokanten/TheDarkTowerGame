package com.anton4j.darktower.util;

import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.ValueOption;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ant
 */
public class Utils {

    public static <T extends Enum> List<Option<T>> optionsFromEnumValues(T[] enumValues) {
        List<Option<T>> raceOptions = new ArrayList<>();
        for (int i = 0; i < enumValues.length; i++) {
            T value = enumValues[i];

            String raceName = value.name();
            String displayText = raceName.substring(0, 1).toUpperCase() + raceName.substring(1).toLowerCase().replaceAll("_", " ");

            ValueOption<T> option = new ValueOption<>(value, displayText, i + 1);
            raceOptions.add(option);
        }
        return raceOptions;
    }

}
