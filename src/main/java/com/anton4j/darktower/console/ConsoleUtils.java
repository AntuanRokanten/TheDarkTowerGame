package com.anton4j.darktower.console;

import java.awt.*;
import java.util.Scanner;

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

}
