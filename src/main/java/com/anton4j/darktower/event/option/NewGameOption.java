package com.anton4j.darktower.event.option;

/**
 * @author anton
 */
public class NewGameOption extends Option {

    private static final String value = "New game"; // todo to properties

    protected NewGameOption(int index) {
        super(index);
    }

    @Override
    public String optionValue() {
        return value;
    }

    @Override
    public void processOption() {

    }
}
