package com.anton4j.darktower.character;

/**
 * @author ant
 */
public abstract class Creature {

    private final Race race;

    private int vitality;
    private int strength;
    private int defence;

    protected Creature(Race race, int vitality, int strength, int defence) {
        this.defence = defence;
        this.vitality = vitality;
        this.strength = strength;
        this.race = race;
    }

    public int vitality() {
        return vitality;
    }

    public int strength() {
        return strength;
    }

    public int defence() {
        return defence;
    }

    @Override
    public String toString() {
        return "race = " + race +
              ", vitality = " + vitality +
              ", strength = " + strength +
              ", defence = " + defence;
    }
}
