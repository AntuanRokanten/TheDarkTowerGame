package com.anton4j.darktower.component.option;

import com.anton4j.darktower.component.event.Event;
import com.anton4j.darktower.component.event.EventResult;

/**
 * @author anton
 */
public abstract class EventOption extends Option {

    protected EventOption(String displayText, int index) {
        super(displayText, index);
    }

    @Override
    public OptionResult processOption() {
        EventResult process = optionEvent().process();

        return new OptionResult<>(process.status(), process.resultObj());
    }

    public abstract Event optionEvent();

}
