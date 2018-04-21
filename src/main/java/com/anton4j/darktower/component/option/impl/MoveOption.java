package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionAvailability;

/**
 * @author anton
 */
public class MoveOption extends Option {

    public MoveOption(int index) {
        super("Move to the next location", index);
    }

    @Override
    public void processOption() {

    }

    @Override
    public OptionAvailability canBeChosen() {
        return OptionAvailability.notAvailable("You need to gain more experience and strength in order to mode to the next location");
    }

}
