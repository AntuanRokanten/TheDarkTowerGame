package com.anton4j.darktower.game.character;

import com.anton4j.darktower.game.GameContext;

/**
 * Events that can happen to the {@link Char} and increases its level.
 *
 * @author ant
 */
public enum CharEvent {

    /**
     * Event when character wins a fight.
     */
    FIGHT_VICTORY(40f) {
        public void logEvent(GameContext gameContext) {
            gameContext.getGameStats().fightWin();
        }
    },

    /**
     * Event when character loses a fight.
     */
    FIGHT_DEFEAT(30f) {
        public void logEvent(GameContext gameContext) {
            gameContext.getGameStats().fightLost();
        }
    },

    /**
     * Event when character explores new areas.
     */
    EXPLORATION(20f) {
        public void logEvent(GameContext gameContext) {
            gameContext.getGameStats().areaExplored();
        }
    },

    /**
     * Event when character runs away from another creature.
     */
    RUN_SUCCESS(15f) {
        public void logEvent(GameContext gameContext) {
            // no need to log
        }
    };

    /**
     * Factor by which experience should be increased when the event occurs.
     */
    private final float experienceFactor;

    CharEvent(float experienceFactor) {
        this.experienceFactor = experienceFactor;
    }

    /**
     * @return factor by which character experience should be increased.
     */
    public float experienceFactor() {
        return experienceFactor;
    }

    /**
     * Logs the event to the context.
     *
     * @param gameContext game context.
     */
    public abstract void logEvent(GameContext gameContext);

}
