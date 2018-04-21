package com.anton4j.darktower.component.option;

/**
 * @author anton
 */
public class OptionAvailability {

    private final boolean available;
    private final String message;

    private OptionAvailability(boolean available, String message) {
        this.available = available;
        this.message = message;
    }

    private OptionAvailability(boolean available) {
        this(available, null);
    }

    public static OptionAvailability available() {
        return new OptionAvailability(true);
    }

    public static OptionAvailability notAvailable(String message) {
        return new OptionAvailability(true, message);
    }

    public boolean isAvailable() {
        return available;
    }

    public String message() {
        return message;
    }
}
