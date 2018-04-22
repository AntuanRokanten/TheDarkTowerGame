package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class CharInfoOption extends Option {

    public CharInfoOption(int index) {
        super("Print character info", index);
    }

    @Override
    public OptionResult processOption() {
        return null;
    }

//    @Override
//    public Event optionEvent() {
//        return null;
//    }

}
