package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class LoadGameOption extends Option<Char> {

    public LoadGameOption(GameContext gameContext) {
        super(gameContext, "Load game");
    }

    @Override
    public OptionResult<Char> processOption() {
        return null;
    }
}
