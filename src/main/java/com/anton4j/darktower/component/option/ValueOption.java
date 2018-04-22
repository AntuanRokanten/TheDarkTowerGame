package com.anton4j.darktower.component.option;

import com.anton4j.darktower.component.event.EventResult;

/**
 * @author anton
 */
public class ValueOption<T> extends Option {

    private final T value;

    public ValueOption(T value, String displayText, int index) {
        super(displayText, index);
        this.value = value;
    }

    @Override
    public OptionResult<T> processOption() {
        return new OptionResult<>(EventResult.Status.SUCCESS, value);
    }

}