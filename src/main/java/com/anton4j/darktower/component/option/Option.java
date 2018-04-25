package com.anton4j.darktower.component.option;

import com.anton4j.darktower.GameContext;

/**
 * @author anton
 */
public abstract class Option<T> {

    protected final GameContext gameContext;
    private final String displayText;

    protected Option(GameContext gameContext, String displayText) {
        this.gameContext = gameContext;
        this.displayText = displayText;
    }

    public String displayText() {
        return displayText;
    }

    public abstract OptionResult<T> processOption();

}
