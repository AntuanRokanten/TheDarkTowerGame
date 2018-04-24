package com.anton4j.darktower.character;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.util.CalculateUtils;

import static com.anton4j.darktower.util.CalculateUtils.calculateFeature;

/**
 * @author ant
 */
public class Char extends Creature {

    private final String name;
    private final Gender gender;

    private int level = 1; // initial level value

    private int experienceToNextLevel;
    private int experience = 0;

    private Char(Gender gender, String name, CharRace charRace, int vitality, int strength, int defence) {
        super(charRace, vitality, strength, defence);
        this.name = name;
        this.gender = gender;

        experienceToNextLevel = calculateExperienceToNextLevel();
    }

    public void increaseExperience(CharEvent charEvent) {
        if (experience == 0) {
            experience = 30;
        } else {
            experience = CalculateUtils.calculateFeature(experience, charEvent.experienceFactor());
        }

        if (experience >= experienceToNextLevel) {
            levelUp();
            experienceToNextLevel = calculateExperienceToNextLevel();
        }
    }

    private void levelUp() {
        level++;
        new ConsoleLine("Your character level was increased to " + level, FontColor.BLACK).println();
    }

    private int calculateExperienceToNextLevel() {
        if (experience == 0) {
            return 100;
        } else {
            return experience * 2;
        }
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