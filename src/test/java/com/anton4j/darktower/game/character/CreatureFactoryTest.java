package com.anton4j.darktower.game.character;

import com.anton4j.darktower.util.CalculateUtils;
import com.anton4j.darktower.util.TestRandomUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
public class CreatureFactoryTest {

    private Char character;

    @Before
    public void setup() {
        CharRace charRace = TestRandomUtils.randomEnum(CharRace.values());
        Gender gender = TestRandomUtils.randomEnum(Gender.values());
        String name = UUID.randomUUID().toString();

        character = new Char.CharBuilder()
              .withGender(gender)
              .withRace(charRace)
              .withName(name)
              .build();
    }

    @Test
    public void createCharacterEnemy() {
        // ACT
        Mob enemy = CreatureFactory.createCharacterEnemy(character);

        // ASSERT
        assertNotNull(enemy);
        assertNotNull(enemy.race);

        float healthPercentage = CalculateUtils.calculatePercentValue(character.health, enemy.health);
        float strengthPercentage = CalculateUtils.calculatePercentValue(character.strength, enemy.strength);
        float defencePercentage = CalculateUtils.calculatePercentValue(character.defence, enemy.defence);
        float vitalityPercentage = CalculateUtils.calculatePercentValue(character.vitality, enemy.vitality);
        float speedPercentage = CalculateUtils.calculatePercentValue(character.speed, enemy.speed);

        float healthDifference = Math.abs(100 - healthPercentage);
        float strengthDifference = Math.abs(100 - strengthPercentage);
        float defenceDifference = Math.abs(100 - defencePercentage);
        float vitalityDifference = Math.abs(100 - vitalityPercentage);
        float speedDifference = Math.abs(100 - speedPercentage);

        assertDifference(healthDifference);
        assertDifference(strengthDifference);
        assertDifference(defenceDifference);
        assertDifference(vitalityDifference);
        assertDifference(speedDifference);
    }

    private void assertDifference(float value) {
        assertTrue(30 > value && value > 5);
    }
}