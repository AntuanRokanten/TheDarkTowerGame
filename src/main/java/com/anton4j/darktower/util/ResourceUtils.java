package com.anton4j.darktower.util;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utils for working with resources.
 *
 * @author ant
 */
public class ResourceUtils {

    /**
     * @param fileName name of the resource text file.
     * @return list of lines of the file; empty list if file is not found.
     */
    public static List<String> getResourceLines(String fileName) {
        InputStream is = ResourceUtils.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            new ConsoleLine("Cannot find resource text file with name " + fileName, FontColor.RED).println();
            return Collections.emptyList();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        List<String> lines = new ArrayList<>();
        try {
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            new ConsoleLine("Cannot load resource file text", FontColor.RED).println();
        }

        return lines;
    }

}
