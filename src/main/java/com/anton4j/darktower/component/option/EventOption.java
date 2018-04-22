package com.anton4j.darktower.component.option;

import com.anton4j.darktower.component.event.Event;

/**
 * @author anton
 */
public abstract class EventOption extends Option {

    protected EventOption(String displayText, int index) {
        super(displayText, index);
    }

    @Override
    public Object processOption() {
        return optionEvent().process();
    }

    public abstract Event optionEvent();

}
