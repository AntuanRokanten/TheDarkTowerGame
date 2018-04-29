package com.anton4j.darktower.console;

import java.io.Serializable;
import java.util.Objects;

/**
 * Line that can be printed in console.
 *
 * @author ant
 */
public class ConsoleLine implements Serializable {

    /**
     * Reset char.
     */
    static final String RESET = "\u001B[0m";

    /**
     * Text value of the line.
     */
    private final String value;

    /**
     * Line font color.
     */
    private final FontColor fontColor;

    /**
     * Line background color.
     */
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

    /**
     * Print lines starting with new line.
     */
    public void println() {
        System.out.println(composeSting());
    }

    /**
     * Print lines starting on current line.
     */
    public void print() {
        System.out.print(composeSting());
    }

    /**
     * @return string to be printed.
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleLine that = (ConsoleLine) o;
        return Objects.equals(value, that.value) &&
              fontColor == that.fontColor &&
              backgroundColor == that.backgroundColor;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, fontColor, backgroundColor);
    }
}
