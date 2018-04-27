package com.anton4j.darktower.game.component.option;

import com.anton4j.darktower.game.GameContext;

/**
 * @author ant
 */
public class ValueOption<T> extends Option<T> {

    private final T value;

    public ValueOption(T value, String displayText, GameContext gameContext) {
        super(gameContext, displayText);
        this.value = value;
    }

    @Override
    public OptionResult<T> processOption() {
        return OptionResult.success(value);
    }

}