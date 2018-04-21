package com.anton4j.darktower.event.option;

import com.anton4j.darktower.console.ConsoleLine;

/**
 * @author anton
 */
public abstract class Option {

    private final int index;

    protected Option(int index) {
        this.index = index;
    }

    public ConsoleLine consoleLine() {
        return new ConsoleLine(index + " - " + optionValue());
    }

    public abstract void processOption();
    public abstract String optionValue();

}
