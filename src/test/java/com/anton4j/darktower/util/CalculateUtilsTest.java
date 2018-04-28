package com.anton4j.darktower.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author ant
 */
public class CalculateUtilsTest {

    @Test
    public void calculateFeature() {
        // ACT
        int actualValue = CalculateUtils.calculateFeature(100, 10f);

        // ASSERT
        assertEquals(110, actualValue);

        // ACT
        actualValue = CalculateUtils.calculateFeature(100, 50f);

        // ASSERT
        assertEquals(150, actualValue);

        // ACT
        actualValue = CalculateUtils.calculateFeature(100, 100f);

        // ASSERT
        assertEquals(200, actualValue);
    }

    @Test
    public void calculatePercentage() {
        // ACT
        int actualValue = CalculateUtils.calculatePercentage(100, 10f);

        // ASSERT
        assertEquals(10, actualValue);

        // ACT
        actualValue = CalculateUtils.calculatePercentage(100, 50f);

        // ASSERT
        assertEquals(50, actualValue);

        // ACT
        actualValue = CalculateUtils.calculatePercentage(100, 100f);

        // ASSERT
        assertEquals(100, actualValue);
    }

    @Test
    public void calculatePercentValue() {
        // ACT
        float actualValue = CalculateUtils.calculatePercentValue(100, 10);

        // ASSERT
        assertEquals(10f, actualValue, 0);

        // ACT
        actualValue = CalculateUtils.calculatePercentValue(100, 50);

        // ASSERT
        assertEquals(50f, actualValue, 0);

        // ACT
        actualValue = CalculateUtils.calculatePercentValue(100, 100);

        // ASSERT
        assertEquals(100f, actualValue, 0);
    }
}