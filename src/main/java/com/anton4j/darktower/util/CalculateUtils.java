package com.anton4j.darktower.util;

/**
 * @author ant
 */
public class CalculateUtils {

   public static int calculateFeature(int value, float factor) {
      return value + calculatePercentage(value, factor);
   }

   public static int calculatePercentage(int value, float percent) {
      return (int) (value * (percent / 100.0f));
   }

   public static float calculatePercentValue(int value, int percentage) {
      return percentage * 100f / value;
   }

}