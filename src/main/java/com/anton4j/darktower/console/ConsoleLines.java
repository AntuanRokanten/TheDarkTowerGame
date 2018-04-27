package com.anton4j.darktower.console;

import java.io.Serializable;
import java.util.List;

/**
 * Wrapper for collection of console lines.
 *
 * @author ant
 */
public class ConsoleLines implements Serializable {

    /**
     * Lines that constitute this obj.
     */
    private final List<ConsoleLine> consoleLines;

    public ConsoleLines(List<ConsoleLine> consoleLines) {
        this.consoleLines = consoleLines;
    }

    /**
     * Prints lines in sequence.
     */
    public void printSequentially() {
        for (ConsoleLine line : consoleLines) {
            line.println();
            ConsoleUtils.sleep(300);
        }
    }

    /**
     * Prints lines at once.
     */
    public void print() {
        consoleLines.forEach(ConsoleLine::println);
    }

}
