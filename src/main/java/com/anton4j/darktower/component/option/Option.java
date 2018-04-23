package com.anton4j.darktower.component.option;

/**
 * @author anton
 */
public abstract class Option<T> {

    private final String displayText;

    protected Option(String displayText) {
        this.displayText = displayText;
    }

    public String displayText() {
        return displayText;
    }

    public abstract OptionResult<T> processOption();

}
