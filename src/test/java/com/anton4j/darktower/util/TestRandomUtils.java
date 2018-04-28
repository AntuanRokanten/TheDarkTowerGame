package com.anton4j.darktower.util;

import java.util.Random;

/**
 * @author ant
 */
public class TestRandomUtils {

    private static final Random RANDOM = new Random();

    public static <T extends Enum> T randomEnum(T[] enumValues) {
        int genderPick = RANDOM.nextInt(enumValues.length);
        return enumValues[genderPick];
    }

}
