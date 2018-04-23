package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class MoveOption extends Option {

    public MoveOption() {
        super("Move to the next location");
    }

//    @Override
//    public OptionAvailability canBeChosen() {
//        return OptionAvailability.notAvailable("You need to gain more experience and strength in order to mode to the next location");
//    }

    @Override
    public OptionResult processOption() {
        return null;
    }

}
