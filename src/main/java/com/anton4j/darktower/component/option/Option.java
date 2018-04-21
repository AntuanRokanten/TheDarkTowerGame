package com.anton4j.darktower.component.option;

import com.anton4j.darktower.console.ConsoleLine;

/**
 * @author anton
 */
public abstract class Option {

    private final String value;
    private final int index;

    protected Option(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public ConsoleLine consoleLine() {
        return new ConsoleLine(index + " - " + value);
    }

    public int index() {
        return index;
    }

    public OptionAvailability canBeChosen() {
        return OptionAvailability.available();
    }

    public abstract void processOption();

}
