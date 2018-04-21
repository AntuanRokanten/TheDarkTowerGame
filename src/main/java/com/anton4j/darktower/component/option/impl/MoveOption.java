package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.event.Event;
import com.anton4j.darktower.component.option.EventOption;
import com.anton4j.darktower.component.option.OptionAvailability;

/**
 * @author anton
 */
public class MoveOption extends EventOption {

    public MoveOption(int index) {
        super("Move to the next location", index);
    }

    @Override
    public Event optionEvent() {
        return null;
    }

    @Override
    public OptionAvailability canBeChosen() {
        return OptionAvailability.notAvailable("You need to gain more experience and strength in order to mode to the next location");
    }

}
