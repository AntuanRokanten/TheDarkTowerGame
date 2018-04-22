package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class LoadGameOption extends Option<Char> {

    public LoadGameOption(int index) {
        super("Load game", index);
    }

    @Override
    public OptionResult<Char> processOption() {
        return null;
    }
}
