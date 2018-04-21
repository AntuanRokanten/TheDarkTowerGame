package com.anton4j.darktower.event.option;

/**
 * @author anton
 */
public class LoadGameOption extends Option {

    private static final String value = "Load game"; // todo to properties

    protected LoadGameOption(int index) {
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
