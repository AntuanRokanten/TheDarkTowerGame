package com.anton4j.darktower.game.component.util;

import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.ValueOption;
import com.anton4j.darktower.game.GameContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Class converter from enum values to options.
 *
 * @author ant
 */
public class OptionsFromEnum<T extends Enum>  {

    /**
     * Enum values to be comverted.
     */
    private final T[] enumValues;

    public OptionsFromEnum(T[] enumValues) {
        this.enumValues = enumValues;
    }

    /**
     * @param gameContext context of the game.
     * @return converted options.
     */
    public List<Option<T>> options(GameContext gameContext) {
        List<Option<T>> raceOptions = new ArrayList<>();
        for (T value : enumValues) {
            String raceName = value.name();
            String displayText = raceName.substring(0, 1).toUpperCase() + raceName.substring(1).toLowerCase().replaceAll("_", " ");

            ValueOption<T> option = new ValueOption<>(value, displayText, gameContext);
            raceOptions.add(option);
        }
        return raceOptions;
    }

}
