package com.anton4j.darktower.character;

/**
 * @author anton
 */
public enum CharRace implements Race {

    HUMAN(100, 60, 70), TAHEEN(80, 75, 75), CAN_TOI(110, 55, 65);

    private final int vitality;
    private final int strength;
    private final int defence;

    CharRace(int vitality, int strength, int defence) {
        this.vitality = vitality;
        this.strength = strength;
        this.defence = defence;
    }

    public int defence() {
        return defence;
    }

    public int strength() {
        return strength;
    }

    public int vitality() {
        return vitality;
    }

}
