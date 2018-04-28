package com.anton4j.darktower.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author ant
 */
public class TestConsoleUtils {

    public static void enterValue(String value) {
        try {
            InputStream in = new ByteArrayInputStream(value.getBytes());
            System.setIn(in);
        } finally {
            System.setIn(System.in);
        }
    }

}
