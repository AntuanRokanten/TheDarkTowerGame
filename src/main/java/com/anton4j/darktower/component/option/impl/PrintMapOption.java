package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class PrintMapOption extends Option {

    public PrintMapOption(int index) {
        super("Print map", index);
    }

    @Override
    public OptionResult processOption() {
        return null;
    }

}
