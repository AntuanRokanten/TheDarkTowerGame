package com.anton4j.darktower.util;

import java.io.ByteArrayInputStream;

/**
 * @author ant
 */
public class TestConsoleUtils {

    public static void enterValue(String value) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(value.getBytes());
            System.setIn(in);
        } finally {
            System.setIn(System.in);
        }
    }

}
