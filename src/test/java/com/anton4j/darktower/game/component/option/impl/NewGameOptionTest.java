package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.character.CharRace;
import com.anton4j.darktower.game.character.Gender;
import com.anton4j.darktower.util.TestConsoleUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author ant
 */
@Ignore("not completed yet")
public class NewGameOptionTest {

    @Test
    public void processOption() throws IllegalAccessException, InterruptedException {
        // ARRANGE
        GameContext gameContext = new GameContext();

        Random random = new Random();

        NewGameOption newGameOption = new NewGameOption(gameContext);

        int raceIndex = random.nextInt(CharRace.values().length - 1) + 1;
        int genderIndex = random.nextInt(Gender.values().length - 1) + 1;
        String name = UUID.randomUUID().toString();

        // ACT
        Thread consoleInputThread = new Thread(() -> {
            try {
                System.err.println("ENTERING");
                TestConsoleUtils.enterValue(raceIndex);
                Thread.sleep(100);
                TestConsoleUtils.enterValue(genderIndex);
                Thread.sleep(100);
                TestConsoleUtils.enterValue(name);
            } catch (Exception ignored) {
            }
        });

        Thread processOptionsThread = new Thread(newGameOption::processOption);

        processOptionsThread.start();
        consoleInputThread.start();

        processOptionsThread.join(5000);

        //assert
        Char mainCharacter = gameContext.getMainCharacter();

        assertNotNull(mainCharacter);

        CharRace actualRace = (CharRace) FieldUtils.readField(mainCharacter, "race", true);
        Gender actualGender = (Gender) FieldUtils.readField(mainCharacter, "gender", true);
        String actualName = (String) FieldUtils.readField(mainCharacter, "name", true);

        assertEquals(name, actualName);
        assertEquals(Gender.values()[genderIndex - 1], actualGender);
        assertEquals(CharRace.values()[raceIndex - 1], actualRace);
    }
}