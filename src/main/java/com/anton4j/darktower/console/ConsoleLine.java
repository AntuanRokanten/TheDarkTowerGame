package com.anton4j.darktower.console;

/**
 * @author anton
 */
public class ConsoleLine {

    private static final String RESET = "\u001B[0m";

    private final String value;
    private final FontColor fontColor;
    private final BackgroundColor backgroundColor;

    public ConsoleLine(String value, FontColor fontColor, BackgroundColor backgroundColor) {
        this.value = value;
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;
    }

    public ConsoleLine(String value, BackgroundColor backgroundColor) {
        this(value, null, backgroundColor);
    }

    public ConsoleLine(String value, FontColor fontColor) {
        this(value, fontColor, null);
    }

    public ConsoleLine(String value) {
        this(value, null, null);
    }

    public void println() {
        System.out.println(composeSting());
    }

    public void print() {
        System.out.print(composeSting());
    }

    private String composeSting() {
        StringBuilder printValue = new StringBuilder();

        if (backgroundColor != null) {
            printValue.append(backgroundColor.value());
        }

        if (fontColor != null) {
            printValue.append(fontColor.value());
        }

        printValue.append(value).append(RESET);
        return printValue.toString();
    }

}
