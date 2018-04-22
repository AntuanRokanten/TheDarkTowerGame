package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class SaveExitOption extends Option {

    public SaveExitOption(int index) {
        super("Save and exit", index);
    }

    @Override
    public OptionResult processOption() {
        return null;
    }
}
