package com.anton4j.darktower.character;

/**
 * @author ant
 */
public enum CharEvent {

   FIGHT_VICTORY(50f), FIGHT_DEFEAT(30f), EXPLORATION(20f);

   private final float experienceFactor;

   CharEvent(float experienceFactor) {
      this.experienceFactor = experienceFactor;
   }

   public float experienceFactor() {
      return experienceFactor;
   }

}
