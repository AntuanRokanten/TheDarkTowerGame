package com.anton4j.darktower;

import com.anton4j.darktower.console.ConsoleLine;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author ant
 */
public class ConsoleLines {

    private final List<ConsoleLine> consoleLines;

    public ConsoleLines(List<ConsoleLine> consoleLines) {
        this.consoleLines = consoleLines;
    }

    public void printSequentially() {
        for (ConsoleLine line : consoleLines) {
            line.println();
            try {
                MILLISECONDS.sleep(300);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void print() {
        consoleLines.forEach(ConsoleLine::println);
    }

}
