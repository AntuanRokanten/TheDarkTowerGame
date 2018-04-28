package com.anton4j.darktower.console;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author ant
 */
public class ConsoleLineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void println() {
        // ARRANGE
        String value = UUID.randomUUID().toString();

        FontColor fontColor = FontColor.WHITE;
        BackgroundColor backgroundColor = BackgroundColor.GRAY;

        // ACT
        new ConsoleLine(value, fontColor, backgroundColor)
              .println();

        // ASSERT
        String expected = backgroundColor.value() + fontColor.value() + value + ConsoleLine.RESET + "\n";
        String actual = outContent.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void print() {
        // ARRANGE
        String value = UUID.randomUUID().toString();

        FontColor fontColor = FontColor.WHITE;
        BackgroundColor backgroundColor = BackgroundColor.GRAY;

        // ACT
        new ConsoleLine(value, fontColor, backgroundColor)
              .print();

        // ASSERT
        String expected = backgroundColor.value() + fontColor.value() + value + ConsoleLine.RESET;
        String actual = outContent.toString();

        assertEquals(expected, actual);
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

}