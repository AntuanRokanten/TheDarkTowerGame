package com.anton4j.darktower.character;

import com.anton4j.darktower.GameContext;

/**
 * @author ant
 */
public enum CharEvent {

    FIGHT_VICTORY(50f) {
        public void logEvent() {
            GameContext.getInstance().getGameStats().fightWin();
        }
    }, FIGHT_DEFEAT(30f) {
        public void logEvent() {
            GameContext.getInstance().getGameStats().fighLost();
        }
    }, EXPLORATION(20f) {
        public void logEvent() {
            GameContext.getInstance().getGameStats().locationExplored();
        }
    }, RUN_SUCCESS(15f) {
        public void logEvent() {
            // no need to log
        }
    };

    private final float experienceFactor;

    CharEvent(float experienceFactor) {
        this.experienceFactor = experienceFactor;
    }

    public float experienceFactor() {
        return experienceFactor;
    }

    public abstract void logEvent();

}
