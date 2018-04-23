package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class SaveExitOption extends Option {

    public SaveExitOption() {
        super("Save and exit");
    }

    @Override
    public OptionResult processOption() {
        return null;
    }
}
