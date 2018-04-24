package com.anton4j.darktower.console;

import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author anton
 */
public class ConsoleUtils {

    public static String readLine() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine().trim();
    }

    public static void emptyLine() {
        System.out.println();
    }

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
