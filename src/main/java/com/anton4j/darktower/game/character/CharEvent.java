package com.anton4j.darktower.game.character;

import com.anton4j.darktower.game.GameContext;

/**
 * @author ant
 */
public enum CharEvent {

    FIGHT_VICTORY(50f) {
        public void logEvent(GameContext gameContext) {
            gameContext.getGameStats().fightWin();
        }
    }, FIGHT_DEFEAT(30f) {
        public void logEvent(GameContext gameContext) {
            gameContext.getGameStats().fightLost();
        }
    }, EXPLORATION(20f) {
        public void logEvent(GameContext gameContext) {
            gameContext.getGameStats().areaExplored();
        }
    }, RUN_SUCCESS(15f) {
        public void logEvent(GameContext gameContext) {
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

    public abstract void logEvent(GameContext gameContext);

}
