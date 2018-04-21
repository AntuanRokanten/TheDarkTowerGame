package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.event.Event;
import com.anton4j.darktower.component.option.EventOption;

/**
 * @author anton
 */
public class SaveExitOption extends EventOption {

    public SaveExitOption(int index) {
        super("Save and exit", index);
    }

    @Override
    public Event optionEvent() {
        return null;
    }

}
