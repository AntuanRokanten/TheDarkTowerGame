package com.anton4j.darktower;

import com.anton4j.darktower.console.ConsoleLine;

import java.util.List;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Game's banner.
 *
 * @author anton
 */
public class Banner {

    /**
     * Lines that compose a banner.
     */
    private final List<ConsoleLine> lines;

    public Banner(List<ConsoleLine> lines) {
        this.lines = lines;
    }

    public void displaySequentially() {
        for (ConsoleLine line : lines) {
            line.println();
            try {
                MILLISECONDS.sleep(300);
            } catch (InterruptedException ignored) {
            }
        }
    }

}
