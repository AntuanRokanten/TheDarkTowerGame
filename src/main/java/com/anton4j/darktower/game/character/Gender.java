package com.anton4j.darktower.game.character;

/**
 * @author ant
 */
@SuppressWarnings("SameParameterValue")
public enum Gender {

    MALE(15, 10, 5, 10), FEMALE(10, 10, 15, 5);

    private final float vitalityFactor;
    private final float strengthFactor;
    private final float defenceFactor;
    private final float speedFactor;

    Gender(float vitalityFactor, float strengthFactor, float defenceFactor, float speedFactor) {
        this.vitalityFactor = vitalityFactor;
        this.strengthFactor = strengthFactor;
        this.defenceFactor = defenceFactor;
        this.speedFactor = speedFactor;
    }

    public float speedFactor() {
        return speedFactor;
    }

    public float defenceFactor() {
        return defenceFactor;
    }

    public float strengthFactor() {
        return strengthFactor;
    }

    public float vitalityFactor() {
        return vitalityFactor;
    }

}
