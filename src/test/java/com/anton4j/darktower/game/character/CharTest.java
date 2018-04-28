package com.anton4j.darktower.game.character;

import com.anton4j.darktower.util.TestRandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static com.anton4j.darktower.util.CalculateUtils.calculateFeature;
import static org.apache.commons.lang3.reflect.FieldUtils.readField;
import static org.junit.Assert.*;

/**
 * @author ant
 */
public class CharTest {

    private Char character;

    private CharRace charRace;
    private Gender gender;
    private String name;

    @Before
    public void setup() {
        charRace = TestRandomUtils.randomEnum(CharRace.values());
        gender = TestRandomUtils.randomEnum(Gender.values());
        name = UUID.randomUUID().toString();

        character = new Char.CharBuilder()
              .withGender(gender)
              .withRace(this.charRace)
              .withName(name)
              .build();
    }

    @Test
    public void testCharStats() throws IllegalAccessException {
        assertEquals(character.health, calculateFeature(charRace.vitality(), gender.vitalityFactor()));
        assertEquals(character.speed, calculateFeature(charRace.speed(), gender.speedFactor()));
        assertEquals(character.defence, calculateFeature(charRace.defence(), gender.defenceFactor()));
        assertEquals(character.strength, calculateFeature(charRace.strength(), gender.strengthFactor()));

        assertEquals(character.race, charRace);

        assertEquals(character.vitality, character.health);

        String actualName = (String) readField(character, "name", true);
        assertEquals(name, actualName);

        Gender actualGender = (Gender) readField(character, "gender", true);
        assertEquals(gender, actualGender);

        Integer actualLevel = (Integer) readField(character, "level", true);
        Integer expectedInitialLevel = 0;
        assertEquals(expectedInitialLevel, actualLevel);

        Integer actualExp = (Integer) readField(character, "experience", true);
        Integer expectedInitialExp = 0;
        assertEquals(expectedInitialExp, actualExp);

        Integer actualExpToNextLevel = (Integer) readField(character, "experienceToNextLevel", true);
        Integer expectedInitialExpToNextLevel = 100;
        assertEquals(expectedInitialExpToNextLevel, actualExpToNextLevel);
    }

    @Test
    public void increaseExperience() throws IllegalAccessException {
        // ARRANGE
        CharEvent charEvent = TestRandomUtils.randomEnum(CharEvent.values());
        int speed = character.speed;
        int vitality = character.vitality;
        int defence = character.defence;
        int strength = character.strength;
        int health = character.health;

        // ACT
        character.increaseExperience(charEvent);

        // ASSERT
        Integer expToNextLevel = (Integer) readField(character, "experienceToNextLevel", true);

        Integer actualExp = (Integer) readField(character, "experience", true);
        assertEquals(Integer.valueOf((int) charEvent.experienceFactor()), actualExp);

        // ARRANGE
        charEvent = TestRandomUtils.randomEnum(CharEvent.values());

        // ACT
        character.increaseExperience(charEvent);

        // ASSERT
        actualExp = (Integer) readField(character, "experience", true);
        assertEquals((Integer) calculateFeature(expToNextLevel, charEvent.experienceFactor()), actualExp);

        if (actualExp > expToNextLevel) {
            int actualLevel = character.level();
            int expectedLevel = 1;
            assertEquals(expectedLevel, actualLevel);

            // asserting stats increased
            assertTrue(strength < character.strength);
            assertTrue(speed < character.speed);
            assertTrue(vitality < character.vitality);
            assertTrue(defence < character.defence);

            // asserting health remains same
            assertEquals(health, character.health);
        }
    }

    @Test
    public void takeRest() {
        // ACT
        character.takeRest();

        // ASSERT
        assertEquals(character.health, character.vitality);

        // ARRANGE
        character.health = 0;

        // ACT
        character.takeRest();

        // ASSERT
        assertTrue(character.vitality > character.health);
        assertTrue(character.health > 0);
    }

    @Test
    public void charInfo() {
        String actualCharInfo = character.charInfo();

        assertNotNull(actualCharInfo);
        assertFalse(actualCharInfo.trim().isEmpty());
    }

}