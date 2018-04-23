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
        this.vitality = vitality;
        this.strength = strength;
        this.race = race;
    }

}
