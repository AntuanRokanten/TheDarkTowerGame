package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.event.Event;
import com.anton4j.darktower.component.option.EventOption;

/**
 * @author anton
 */
public class TakeRestOption extends EventOption {

    public TakeRestOption(int index) {
        super("Take a rest", index);
    }

    @Override
    public Event optionEvent() {
        return null;
    }
}
