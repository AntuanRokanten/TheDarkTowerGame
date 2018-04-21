package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.event.Event;
import com.anton4j.darktower.component.event.impl.CreateCharacterEvent;
import com.anton4j.darktower.component.option.EventOption;

/**
 * @author anton
 */
public class NewGameOption extends EventOption {

    public NewGameOption(int index) {
        super("New game", index);
    }

    @Override
    public Event optionEvent() {
        return new CreateCharacterEvent();
    }

}
