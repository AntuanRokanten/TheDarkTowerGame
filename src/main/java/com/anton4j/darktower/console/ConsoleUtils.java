package com.anton4j.darktower.console;

import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Utils methods for working with console.
 *
 * @author ant
 */
public class ConsoleUtils {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * @return value entered in console.
     */
    public static String readLine() {
        return scanner.nextLine().trim();
    }

    /**
     * Prints empty line to the console.
     */
    public static void emptyLine() {
        System.out.println();
    }

    /**
     * Performs default OS beep sound.
     */
    public static void beep() {
        Toolkit.getDefaultToolkit().beep();
    }

    /**
     * Puts current thread in sleep mode.
     *
     * @param millis milliseconds to sleep.
     */
    public static void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
}
