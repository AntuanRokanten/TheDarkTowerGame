package com.anton4j.darktower;

import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.character.Mob;
import com.anton4j.darktower.character.MobRace;
import com.anton4j.darktower.util.RandomUtils;

import static com.anton4j.darktower.util.CalculateUtils.calculateFeature;
import static com.anton4j.darktower.util.RandomUtils.integerInRange;
import static com.anton4j.darktower.util.RandomUtils.randomBoolean;

/**
 * @author ant
 */
public class CreatureFactory {

    public static Mob createCharacterEnemy(Char character) {
        MobRace[] mobRaces = MobRace.values();
        MobRace randomRace = mobRaces[RandomUtils.integer(mobRaces.length)];

        int strengthFactor;
        int vitalityFactor;
        int defenceFactor;

        if (randomBoolean() && randomBoolean() && randomBoolean()) { // to make event appear rarely
            strengthFactor = integerInRange(5, 20);
            vitalityFactor = integerInRange(5, 20);
            defenceFactor = integerInRange(5, 20);

            boolean invertStrength = randomBoolean();
            boolean invertVitality = randomBoolean();
            if (invertStrength) {
                strengthFactor = strengthFactor * -1;
            } else if (invertVitality) {
                vitalityFactor = vitalityFactor * -1;
            } else {
                defenceFactor = defenceFactor * -1;
            }
        } else {
            strengthFactor = integerInRange(-30, -20);
            vitalityFactor = integerInRange(-30, -20);
            defenceFactor = integerInRange(-30, -20);
        }

        int defence = calculateFeature(character.defence(), defenceFactor);
        int vitality = calculateFeature(character.vitality(), vitalityFactor);
        int strength = calculateFeature(character.strength(), strengthFactor);

        return new Mob(randomRace, vitality, strength, defence);
    }

}
