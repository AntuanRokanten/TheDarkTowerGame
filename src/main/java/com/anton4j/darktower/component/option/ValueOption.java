package com.anton4j.darktower.component.option;

/**
 * @author anton
 */
public class ValueOption<T> extends Option {

    private final T value;

    public ValueOption(T value, String displayText, int index) {
        super(displayText, index);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}
