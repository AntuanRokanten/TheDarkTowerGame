package com.anton4j.darktower.console;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author ant
 */
public class ProgressBar {

    private final long total;

    public ProgressBar(long total) {
        this.total = total;
    }

    public void start() {
        for (int i = 1; i <= total; i = i + 3) {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException ignored) {
            }
            printProgress(total, i);
        }
    }

    private static void printProgress(long total, long current) {
        int percent = (int) (current * 100 / total);

        String bar = "\r" +
              String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")) +
              String.format(" %d%% [", percent) +
              String.join("", Collections.nCopies(percent, "=")) +
              '>' +
              String.join("", Collections.nCopies(100 - percent, " ")) +
              ']' +
              String.join("", Collections.nCopies((int) (Math.log10(total)) - (int) (Math.log10(current)), " "));

        new ConsoleLine(bar, FontColor.BLACK).print();
    }

}
