package com.anton4j.darktower.util;

/**
 * Utils for performing calculations.
 *
 * @author ant
 */
public class CalculateUtils {

   /**
    * Calculates feature as specified value plus factor as a percentage value from value.
    *
    * @param value feature value.
    * @param factor percentage value to be added.
    * @return calculated feature value.
    */
   public static int calculateFeature(int value, float factor) {
      return value + calculatePercentage(value, factor);
   }

   /**
    * Calculate percentage from the specified value.
    *
    * @param value value for which percentage will be calculated.
    * @param percent percentage to be calculated.
    * @return percentage of the specified value.
    */
   public static int calculatePercentage(int value, float percent) {
      return (int) (value * (percent / 100.0f));
   }

   /**
    * Calculate how many percent is percentage param of the specified value.
    *
    * @param value value as 100%
    * @param percentage percentage value as X%
    * @return X%
    */
   public static float calculatePercentValue(int value, int percentage) {
      return percentage * 100f / value;
   }

}