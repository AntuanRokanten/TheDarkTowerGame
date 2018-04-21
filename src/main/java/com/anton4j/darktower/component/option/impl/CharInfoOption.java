package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.event.Event;
import com.anton4j.darktower.component.option.EventOption;

/**
 * @author anton
 */
public class CharInfoOption extends EventOption {

    public CharInfoOption(int index) {
        super("Print character info", index);
    }

    @Override
    public Event optionEvent() {
        return null;
    }

}
