package com.anton4j.darktower.component.option;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.event.EventResult;

/**
 * @author anton
 */
public class ValueOption<T> extends Option<T> {

    private final T value;

    public ValueOption(T value, String displayText, GameContext gameContext) {
        super(gameContext, displayText);
        this.value = value;
    }

    @Override
    public OptionResult<T> processOption() {
        return new OptionResult<>(EventResult.Status.SUCCESS, value);
    }

}