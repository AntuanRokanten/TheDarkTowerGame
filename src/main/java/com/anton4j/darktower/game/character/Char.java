package com.anton4j.darktower.game.character;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.console.ProgressBar;
import com.anton4j.darktower.util.CalculateUtils;
import com.anton4j.darktower.util.RandomUtils;

import java.io.Serializable;

import static com.anton4j.darktower.util.CalculateUtils.calculateFeature;
import static com.anton4j.darktower.util.CalculateUtils.calculatePercentage;

/**
 * Class for main game character
 *
 * @author ant
 */
public class Char extends Creature implements Serializable {

    /**
     * Name of the character.
     */
    private final String name;

    /**
     * Character gender.
     */
    private final Gender gender;

    /**
     * Character level. Initially character is of 0 level.
     */
    private int level = 0;

    /**
     * Experience value that should be reached in order to get next level.
     */
    private int experienceToNextLevel = 100;

    /**
     * Current character experience.
     */
    private int experience = 0;

    private Char(Gender gender, String name, CharRace charRace, int vitality, int strength, int defence, int speed) {
        super(charRace, vitality, strength, defence, speed);
        this.name = name;
        this.gender = gender;
    }

    /**
     * Increases experience based on specified event.
     *
     * @param charEvent character event.
     */
    public void increaseExperience(CharEvent charEvent) {
        if (experience == 0) {
            experience = (int) charEvent.experienceFactor();
        } else {
            experience = calculateFeature(experienceToNextLevel, charEvent.experienceFactor());
        }

        new ConsoleLine("Character experience increased to " + experience, FontColor.CYAN).println();
        if (experience >= experienceToNextLevel) {
            levelUp();
            experienceToNextLevel = calculateExperienceToNextLevel();
        }
    }

    /**
     * Increases character's health.
     */
    public void takeRest() {
        if (health == vitality) {
            new ConsoleLine("No need to rest. Character is full of energy!", FontColor.GREEN).println();
            return;
        }

        new ConsoleLine("Character is resting...", FontColor.GREEN).println();
        new ProgressBar(500).start();

        int healthAfterRest = health + calculatePercentage(vitality, RandomUtils.integerInRange(20, 30));
        if (healthAfterRest > vitality) {
            healthAfterRest = vitality;
        }
        this.health = healthAfterRest;

        ConsoleUtils.emptyLine();
        new ConsoleLine("Character health increased to " + health, FontColor.GREEN).println();
    }

    /**
     * @return formatted character information.
     */
    public String charInfo() {
        float healthPercentage = CalculateUtils.calculatePercentValue(vitality, health);

        return "        Character info" +
              "\nName: " + name +
              "\nRace: " + race +
              "\nStats: strength = " +
              strength + "; defence = " + defence + "; speed = " + speed + "; vitality = " + vitality +
              "\nHealth: " + health + " (" + (int) healthPercentage + "% of vitality)" +
              "\nLevel: " + level +
              "\nExperience: " + experience + " (gain " + experienceToNextLevel + " to get next level)";
    }

    /**
     * Increments level of the character.
     */
    private void levelUp() {
        level++;

        int newLevelFactor = 30;
        vitality = calculateFeature(vitality, newLevelFactor + gender.vitalityFactor());
        speed = calculateFeature(speed, newLevelFactor + gender.speedFactor());
        defence = calculateFeature(defence, newLevelFactor + gender.defenceFactor());
        strength = calculateFeature(strength, newLevelFactor + gender.strengthFactor());

        new ConsoleLine("Character level was increased to " + level, FontColor.WHITE).println();
    }

    /**
     * @return experience needed to get next level.
     */
    private int calculateExperienceToNextLevel() {
        return experienceToNextLevel * 3;
    }

    /**
     * @return current level of the character,
     */
    public int level() {
        return level;
    }

    /**
     * Character builder class.
     */
    public static class CharBuilder {

        private String name;
        private Gender gender;
        private CharRace charRace;

        /**
         * Sets name of the character.
         *
         * @param name name to be set.
         * @return current builder instance.
         */
        public CharBuilder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets name gender of the character.
         *
         * @param gender gender to be set.
         * @return current builder instance.
         */
        public CharBuilder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        /**
         * Sets race of the character.
         *
         * @param charRace race to be set.
         * @return current builder instance.
         */
        public CharBuilder withRace(CharRace charRace) {
            this.charRace = charRace;
            return this;
        }

        /**
         * Builds {@link Char} instance.
         */
        public Char build() {
            int vitality = calculateFeature(charRace.vitality(), gender.vitalityFactor());
            int strength = calculateFeature(charRace.strength(), gender.strengthFactor());
            int defence = calculateFeature(charRace.defence(), gender.defenceFactor());
            int speed = calculateFeature(charRace.speed(), gender.speedFactor());

            return new Char(gender, name, charRace, vitality, strength, defence, speed);
        }
    }

}