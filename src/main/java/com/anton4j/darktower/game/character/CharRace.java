package com.anton4j.darktower.game.character;

/**
 * Role of the {@link Char} character.
 *
 * @author ant
 */
public enum CharRace implements Race {

    /* races */
    JEDI(80, 65, 75, 100),
    HUMAN(100, 75, 65, 80);

    /* initial stats */
    private final int vitality;
    private final int strength;
    private final int defence;
    private final int deceit;

    CharRace(int vitality, int strength, int defence, int deceit) {
        this.vitality = vitality;
        this.strength = strength;
        this.defence = defence;
        this.deceit = deceit;
    }

    /**
     * @return initial race deceit value.
     */
    public int deceit() {
        return deceit;
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
