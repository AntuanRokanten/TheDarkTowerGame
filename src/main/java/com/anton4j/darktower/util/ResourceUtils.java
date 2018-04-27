package com.anton4j.darktower.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            return Collections.emptyList();
        }

        File file = new File(resource.getFile());

        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

}
