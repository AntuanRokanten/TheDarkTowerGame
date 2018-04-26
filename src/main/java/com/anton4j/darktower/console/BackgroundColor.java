package com.anton4j.darktower.console;

/**
 * @author anton
 */
public enum BackgroundColor implements ConsoleColor {

    BLACK("\u001B[40m"),
    RED("\u001B[41m"),
    GREEN("\u001B[42m"),
    YELLOW("\u001B[43m"),
    BLUE("\u001B[44m"),
    PURPLE("\u001B[45m"),
    CYAN("\u001B[46m"),
    GRAY("\u001B[47m");

    private final String value;

    BackgroundColor(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

}
