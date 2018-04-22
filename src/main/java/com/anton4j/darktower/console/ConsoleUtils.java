package com.anton4j.darktower.console;

import java.util.Scanner;

/**
 * @author anton
 */
public class ConsoleUtils {

    public static String readLine() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public static void emptyLine() {
        System.out.println();
    }
}
