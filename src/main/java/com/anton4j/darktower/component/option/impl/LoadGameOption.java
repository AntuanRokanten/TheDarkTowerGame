package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.event.Event;
import com.anton4j.darktower.component.option.EventOption;

/**
 * @author anton
 */
public class LoadGameOption extends EventOption {

    public LoadGameOption(int index) {
        super("Load game", index);
    }

    @Override
    public Event optionEvent() {
        return null;
    }

}
