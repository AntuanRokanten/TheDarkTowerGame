package com.anton4j.darktower.character;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.console.ProgressBar;

import static com.anton4j.darktower.util.CalculateUtils.calculateFeature;
import static com.anton4j.darktower.util.CalculateUtils.calculatePercentage;

/**
 * @author ant
 */
public class Char extends Creature {

    private final String name;
    private final Gender gender;

    /**
     * Character level. Initially character is of 1 level.
     */
    private int level = 1;

    private int experienceToNextLevel = 100;
    private int experience = 0;

    private Char(Gender gender, String name, CharRace charRace, int vitality, int strength, int defence, int speed) {
        super(charRace, vitality, strength, defence, speed);
        this.name = name;
        this.gender = gender;
    }

    public void increaseExperience(CharEvent charEvent) {
        if (experience == 0) {
            experience = (int) charEvent.experienceFactor();
        } else {
            experience = calculateFeature(experience, charEvent.experienceFactor());
        }

        new ConsoleLine("Character experience increased to " + experience, FontColor.CYAN).println();
        if (experience >= experienceToNextLevel) {
            levelUp();
            experienceToNextLevel = calculateExperienceToNextLevel();
        }
    }

    public void takeRest() {
        if (health == vitality) {
            new ConsoleLine("No need to rest. Character is full of energy!", FontColor.GREEN).println();
            return;
        }

        new ConsoleLine("Character is resting...", FontColor.GREEN).println();
        new ProgressBar(500).start();

        if (health == 0) {
            health = calculatePercentage(vitality, 40);
        } else {
            int healthAfterRest = calculateFeature(this.health, 50f);
            if (healthAfterRest > vitality) {
                healthAfterRest = vitality;
            }
            this.health = healthAfterRest;
        }

        ConsoleUtils.emptyLine();
        new ConsoleLine("Character health increased to " + health, FontColor.GREEN).println();
    }

    private void levelUp() {
        level++;

        int newLevelFactor = 30;
        vitality = calculateFeature(vitality, newLevelFactor + gender.vitalityFactor());
        speed = calculateFeature(speed, newLevelFactor + gender.speedFactor());
        defence = calculateFeature(defence, newLevelFactor + gender.defenceFactor());
        strength = calculateFeature(strength, newLevelFactor + gender.strengthFactor());

        new ConsoleLine("Character level was increased to " + level, FontColor.BLACK).println();
    }

    private int calculateExperienceToNextLevel() {
        return experienceToNextLevel * 3;
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
            int speed = calculateFeature(charRace.defence(), gender.speedFactor());

            return new Char(gender, name, charRace, vitality, strength, defence, speed);
        }
    }

}