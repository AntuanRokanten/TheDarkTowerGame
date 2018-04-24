package com.anton4j.darktower;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;

import java.util.List;

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
            ConsoleUtils.sleep(300);
        }
    }

    public void print() {
        consoleLines.forEach(ConsoleLine::println);
    }

}
