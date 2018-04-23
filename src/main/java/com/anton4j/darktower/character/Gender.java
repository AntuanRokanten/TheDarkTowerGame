package com.anton4j.darktower.character;

/**
 * @author ant
 */
public enum Gender {

    MALE(15, 10, 5), FEMALE(5, 10, 15);

    private final float vitalityFactor;
    private final float strengthFactor;
    private final float defenceFactor;

    Gender(float vitalityFactor, float strengthFactor, float defenceFactor) {
        this.vitalityFactor = vitalityFactor;
        this.strengthFactor = strengthFactor;
        this.defenceFactor = defenceFactor;
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
