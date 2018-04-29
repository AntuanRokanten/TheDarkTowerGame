package com.anton4j.darktower.console;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleLines that = (ConsoleLines) o;
        return Objects.equals(consoleLines, that.consoleLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consoleLines);
    }
}
