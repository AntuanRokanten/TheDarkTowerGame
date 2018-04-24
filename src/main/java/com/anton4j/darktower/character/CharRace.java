package com.anton4j.darktower.character;

/**
 * @author anton
 */
public enum CharRace implements Race {

    HUMAN(100, 65, 70, 65), TAHEEN(80, 75, 75, 70), CAN_TOI(105, 55, 60, 80);

    private final int vitality;
    private final int strength;
    private final int defence;
    private final int speed;

    CharRace(int vitality, int strength, int defence, int speed) {
        this.vitality = vitality;
        this.strength = strength;
        this.defence = defence;
        this.speed = speed;
    }

    public int speed() {
        return speed;
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
