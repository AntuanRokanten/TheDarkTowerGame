package com.anton4j.darktower.event.option;

/**
 * @author anton
 */
public class CreateCharacterOption extends Option {

    private static final String value = "Create a character"; // todo to properties

    protected CreateCharacterOption(int index) {
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
