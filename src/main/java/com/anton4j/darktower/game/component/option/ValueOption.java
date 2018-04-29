package com.anton4j.darktower.game.component.option;

import com.anton4j.darktower.game.GameContext;

/**
 * Option that contains a value and returns it if chosen,
 *
 * @author ant
 */
public class ValueOption<T> extends Option<T> {

    /**
     * Value of this option. It will be returned if option is chosen.
     */
    private final T value;

    public ValueOption(T value, String displayText, GameContext gameContext) {
        super(gameContext, displayText);
        this.value = value;
    }

    @Override
    public OptionResult<T> processOptionForResult() {
        return OptionResult.success(value);
    }

}