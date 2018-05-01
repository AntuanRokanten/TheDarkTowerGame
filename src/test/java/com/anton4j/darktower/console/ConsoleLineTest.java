package com.anton4j.darktower.console;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author ant
 */
public class ConsoleLineTest extends OutStreamsInterceprtorTest {

    @Ignore("todo fix this test")
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

}