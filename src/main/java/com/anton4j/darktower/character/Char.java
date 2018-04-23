package com.anton4j.darktower.character;

/**
 * @author ant
 */
public class Char extends Creature {

    private final String name;
    private final Gender gender;

    private int level = 1; // initial level value

    private Char(Gender gender, String name, CharRace charRace, int vitality, int strength, int defence) {
        super(charRace, vitality, strength, defence);
        this.name = name;
        this.gender = gender;
    }

    public void levelUp() {
        level++;
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

        private int calculateFeature(int value, float factor) {
            return value + (int) (value * (factor / 100.0f));
        }

    }

}