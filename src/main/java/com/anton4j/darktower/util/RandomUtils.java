package com.anton4j.darktower.util;

import java.util.Random;

/**
 * Utils for generating random data.
 *
 * @author ant
 */
public class RandomUtils {

    private final static Random RANDOM = new Random();

    /**
     * @return random boolean value.
     */
    public static boolean randomBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * @param bound bound on the random number to be returned. Must be positive.
     * @return bounded positive integer value.
     */
    public static int integer(int bound) {
        return RANDOM.nextInt(bound);
    }

    /**
     * @param min minimum random integer value.
     * @param max maximum random integer value.
     * @return random integer in range.
     */
    public static int integerInRange(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

}
