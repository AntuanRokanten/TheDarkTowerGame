package com.anton4j.darktower.event.option.impl;

import com.anton4j.darktower.event.option.Option;

/**
 * @author anton
 */
public class MoveOption extends Option {

    public MoveOption(int index) {
        super("Move to the next location", index);
    }

    @Override
    public void processOption() {

    }
}
