package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.event.Event;
import com.anton4j.darktower.component.option.EventOption;
import com.anton4j.darktower.component.option.OptionAvailability;

/**
 * @author anton
 */
public class ExploreOption extends EventOption {

    public ExploreOption(int index) {
        super("Explore the location", index);
    }

    @Override
    public Event optionEvent() {
        return null;
    }

    @Override
    public OptionAvailability canBeChosen() {
        return super.canBeChosen();
    }

}
