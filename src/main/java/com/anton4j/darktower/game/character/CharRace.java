package com.anton4j.darktower.game.character;

/**
 * Race of the {@link Char} character.
 *
 * @author ant
 */
public enum CharRace implements Race {

    /* races */
    HUMAN(100, 65, 70, 65),
    TAHEEN(80, 75, 75, 70),
    CAN_TOI(105, 55, 60, 80);

    /* initial stats */
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

    /**
     * @return initial race speed value.
     */
    public int speed() {
        return speed;
    }

    /**
     * @return initial race defence value.
     */
    public int defence() {
        return defence;
    }

    /**
     * @return initial race strength value.
     */
    public int strength() {
        return strength;
    }

    /**
     * @return initial race vitality value.
     */
    public int vitality() {
        return vitality;
    }

}
