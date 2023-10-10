package com.anton4j.darktower.game.character;

/**
 * Gender that can be assigned to {@link Char}
 *
 * @author ant
 */
@SuppressWarnings("SameParameterValue")
public enum Gender {

    MALE(15, 10, 5, 5),
    FEMALE(10, 10, 15, 10);

    private final float vitalityFactor;
    private final float strengthFactor;
    private final float defenceFactor;
    private final float deceitFactor;

    Gender(float vitalityFactor, float strengthFactor, float defenceFactor, float deceitFactor) {
        this.vitalityFactor = vitalityFactor;
        this.strengthFactor = strengthFactor;
        this.defenceFactor = defenceFactor;
        this.deceitFactor = deceitFactor;
    }

    /**
     * @return factor that is taken into account when calculating character's speed value.
     */
    public float speedFactor() {
        return deceitFactor;
    }

    /**
     * @return factor that is taken into account when calculating character's defence value.
     */
    public float defenceFactor() {
        return defenceFactor;
    }

    /**
     * @return factor that is taken into account when calculating character's strength value.
     */
    public float strengthFactor() {
        return strengthFactor;
    }

    /**
     * @return factor that is taken into account when calculating character's vitality value.
     */
    public float vitalityFactor() {
        return vitalityFactor;
    }

}
