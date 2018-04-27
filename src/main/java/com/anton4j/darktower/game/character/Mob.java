package com.anton4j.darktower.game.character;

/**
 * Non-personified creature.
 * Used as enemy for {@link Char}.
 *
 * @author ant
 */
public class Mob extends Creature {

    public Mob(MobRace race, int vitality, int strength, int defence, int speed) {
        super(race, vitality, strength, defence, speed);
    }

}
