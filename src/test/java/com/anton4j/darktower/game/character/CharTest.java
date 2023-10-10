package com.anton4j.darktower.game.character;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.game.character.encounter.EncounterOutcome;
import com.anton4j.darktower.util.TestRandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static com.anton4j.darktower.util.CalculateUtils.calculateFeature;
import static com.anton4j.darktower.util.CalculateUtils.calculatePercentage;
import static org.apache.commons.lang3.reflect.FieldUtils.readField;
import static org.junit.Assert.*;

/**
 * @author ant
 */
public class CharTest extends OutStreamsInterceprtorTest {

    private Char character;

    private CharRace charRole;
    private Gender gender;
    private String name;

    @Before
    public void setup() {
        charRole = TestRandomUtils.randomEnum(CharRace.values());
        gender = TestRandomUtils.randomEnum(Gender.values());
        name = UUID.randomUUID().toString();

        character = new Char.CharBuilder()
              .withGender(gender)
              .withRace(this.charRole)
              .withName(name)
              .build();
    }

    @Test
    public void testCharStats() throws IllegalAccessException {
        assertEquals(character.health, calculateFeature(charRole.vitality(), gender.vitalityFactor()));
        assertEquals(character.deceit, calculateFeature(charRole.deceit(), gender.speedFactor()));
        assertEquals(character.defence, calculateFeature(charRole.defence(), gender.defenceFactor()));
        assertEquals(character.strength, calculateFeature(charRole.strength(), gender.strengthFactor()));

        assertEquals(character.race, charRole);

        assertEquals(character.vitality, character.health);

        String actualName = (String) readField(character, "name", true);
        assertEquals(name, actualName);

        Gender actualGender = (Gender) readField(character, "gender", true);
        assertEquals(gender, actualGender);

        Integer actualLevel = (Integer) readField(character, "level", true);
        Integer expectedInitialLevel = 1;
        assertEquals(expectedInitialLevel, actualLevel);

        Integer actualExp = (Integer) readField(character, "experience", true);
        Integer expectedInitialExp = 0;
        assertEquals(expectedInitialExp, actualExp);

        Integer actualExpToNextLevel = (Integer) readField(character, "experienceToNextLevel", true);
        Integer expectedInitialExpToNextLevel = 100;
        assertEquals(expectedInitialExpToNextLevel, actualExpToNextLevel);
    }

    @Test
    public void fightWinTest() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        enemy.health = 10; // to ensure win

        // ACT
        EncounterOutcome outcome = character.fight(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.SUCCESS, outcome);

        String fightLog = outContent.toString();
        assertTrue(fightLog.contains("Starting a fight"));
        assertTrue(fightLog.contains(charRole + " attacks"));
        assertTrue(fightLog.contains(enemy.race + " health after attack"));

        assertTrue(enemy.health < 10);
        assertTrue(character.health <= character.vitality);
    }

    @Test
    public void fightLoseTest() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        character.health = 10; // to ensure fight will be lost

        // ACT
        EncounterOutcome outcome = character.fight(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.FAILURE, outcome);

        String fightLog = outContent.toString();
        assertTrue(fightLog.contains("Starting a fight"));
        assertTrue(fightLog.contains(enemy.race + " attacks"));
        assertTrue(fightLog.contains(character.race + " health after attack"));

        assertTrue(character.health < 10);
        assertTrue(enemy.health <= enemy.vitality);
    }

    @Test
    public void runAwaySuccessSpeedHigher() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        enemy.deceit = 10; // to ensure run success

        // ACT
        EncounterOutcome outcome = character.deceit(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.SUCCESS, outcome);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR"));
        assertTrue(consoleOutput.contains("You can move on")); // not asserting font color
    }

    @Test
    public void runAwaySuccessSpeedSlightlyHigherHealthHigher() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        enemy.deceit = character.deceit - 1;
        enemy.health = character.health - 1;

        // ACT
        EncounterOutcome outcome = character.deceit(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.SUCCESS, outcome);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR"));
        assertTrue(consoleOutput.contains("You can move on")); // not asserting font color
    }

    @Test
    public void runAwaySuccessSpeedSlightlyHigherHealthSlightlyLower() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        enemy.deceit = character.deceit - 1;
        character.health = enemy.health - 1;

        // ACT
        EncounterOutcome outcome = character.deceit(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.SUCCESS, outcome);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR"));
        assertTrue(consoleOutput.contains("You can move on")); // not asserting font color
    }

    @Test
    public void runAwayFailureSpeedSlightlyHigherHealthLower() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        enemy.deceit = character.deceit - 1;
        character.health = enemy.health - 100;

        // ACT
        EncounterOutcome outcome = character.deceit(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.FAILURE, outcome);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR"));
        assertTrue(consoleOutput.contains("You was caught by the creature")); // not asserting font color
    }

    @Test
    public void runAwayFailureSpeedSlightlyLowerHealthSlightlyLower() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        character.deceit = enemy.deceit - 1;
        character.health = enemy.health - 1;

        // ACT
        EncounterOutcome outcome = character.deceit(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.FAILURE, outcome);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR"));
        assertTrue(consoleOutput.contains("You was caught by the creature")); // not asserting font color
    }

    @Test
    public void runAwayFailureSpeedSlightlyLowerHealthSlightlyHigher() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        character.deceit = enemy.deceit - 1;
        enemy.health = character.health - 1;

        // ACT
        EncounterOutcome outcome = character.deceit(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.FAILURE, outcome);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR"));
        assertTrue(consoleOutput.contains("You was caught by the creature")); // not asserting font color
    }

    @Test
    public void runAwayFailureSpeedSlightlyLowerHealthHigher() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        character.deceit = enemy.deceit - 1;
        enemy.health = character.health - 100;

        // ACT
        EncounterOutcome outcome = character.deceit(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.SUCCESS, outcome);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR"));
        assertTrue(consoleOutput.contains("You can move on")); // not asserting font color
    }

    @Test
    public void runAwayFailureHealth() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        enemy.deceit = character.deceit - 1;
        character.health = enemy.health - 100;

        // ACT
        EncounterOutcome outcome = character.deceit(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.FAILURE, outcome);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR"));
        assertTrue(consoleOutput.contains("You was caught by the creature")); // not asserting font color
    }

    @Test
    public void runAwayFailureSpeedLower() {
        // ARRANGE
        Mob enemy = CreatureFactory.createCharacterEnemy(character);
        character.deceit = 10; // to ensure run failure

        // ACT
        EncounterOutcome outcome = character.deceit(enemy);

        // ASSERT
        assertEquals(EncounterOutcome.FAILURE, outcome);

        String consoleOutput = outContent.toString();

        assertTrue(consoleOutput.contains("You are applying Jedi Mind Trick: THESE AREN'T THE DROIDS YOU'RE LOOKING FOR"));
        assertTrue(consoleOutput.contains("You was caught by the creature")); // not asserting font color
    }

    @Test
    public void increaseExperience() throws IllegalAccessException {
        // ARRANGE
        CharEvent charEvent = TestRandomUtils.randomEnum(CharEvent.values());
        int speed = character.deceit;
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
        int experienceIncreaseFactor = (Integer) readField(character, "experienceIncreaseFactor", true);
        assertEquals(100, experienceIncreaseFactor);

        Integer expectedExp = actualExp + calculatePercentage(experienceIncreaseFactor, charEvent.experienceFactor());
        actualExp = (Integer) readField(character, "experience", true);
        assertEquals(expectedExp, actualExp);

        if (actualExp > expToNextLevel) {
            int actualLevel = character.level();
            int expectedLevel = 1;
            assertEquals(expectedLevel, actualLevel);

            // asserting stats increased
            assertTrue(strength < character.strength);
            assertTrue(speed < character.deceit);
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