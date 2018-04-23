package com.anton4j.darktower.util;

import java.util.Random;

/**
 * @author ant
 */
public class RandomUtils {

    private final static Random RANDOM = new Random();

    public static boolean randomBoolean() {
        return RANDOM.nextBoolean();
    }

    public static int integer() {
        return RANDOM.nextInt();
    }

    /**
     * @param bound bound on the random number to be returned. Must be positive.
     * @return bounded positive integer value.
     */
    public static int integer(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static int integerInRange(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

}
