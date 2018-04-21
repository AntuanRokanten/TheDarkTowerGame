package com.anton4j.darktower.component.option;

import com.anton4j.darktower.console.ConsoleLine;

/**
 * @author anton
 */
public abstract class Option {

    private final String displayText;
    private final int index;

    protected Option(String displayText, int index) {
        this.displayText = displayText;
        this.index = index;
    }

    public ConsoleLine consoleLine() {
        return new ConsoleLine(index + " - " + displayText);
    }

    public int index() {
        return index;
    }

    public OptionAvailability canBeChosen() {
        return OptionAvailability.available();
    }

//    public final void processOption() {
//        EventResult process = optionEvent().process();
//    }

}
