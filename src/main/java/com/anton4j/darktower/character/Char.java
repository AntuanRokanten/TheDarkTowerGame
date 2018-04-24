package com.anton4j.darktower.character;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

import static com.anton4j.darktower.util.CalculateUtils.calculateFeature;

/**
 * @author ant
 */
public class Char extends Creature {

    private final String name;
    private final Gender gender;

    private int level = 1; // initial level value

    private int expericenceToNextLevel;
    private int experience = 0;

    private Char(Gender gender, String name, CharRace charRace, int vitality, int strength, int defence) {
        super(charRace, vitality, strength, defence);
        this.name = name;
        this.gender = gender;

        expericenceToNextLevel = 50;
    }

    public void levelUp() {
        level++;
    }

    public void runAway(Mob enemy) {
        new ConsoleLine("You are running away ...", FontColor.PURPLE).println();
        // todo print result
    }

    public static class CharBuilder {

        private String name;
        private Gender gender;
        private CharRace charRace;

        public CharBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CharBuilder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public CharBuilder withRace(CharRace charRace) {
            this.charRace = charRace;
            return this;
        }

        public Char build() {
            int vitality = calculateFeature(charRace.vitality(), gender.vitalityFactor());
            int strength = calculateFeature(charRace.strength(), gender.strengthFactor());
            int defence = calculateFeature(charRace.defence(), gender.defenceFactor());

            return new Char(gender, name, charRace, vitality, strength, defence);
        }
    }

}