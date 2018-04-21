package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionAvailability;

/**
 * @author anton
 */
public class ExploreOption extends Option {

    public ExploreOption(int index) {
        super("Explore the location", index);
    }

    @Override
    public void processOption() {

    }

    @Override
    public OptionAvailability canBeChosen() {
        return super.canBeChosen();
    }

}
