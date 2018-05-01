package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.character.CharRace;
import com.anton4j.darktower.game.character.Gender;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

/**
 * @author ant
 */
public class NewGameOptionTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

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
        systemInMock.provideLines(raceIndex + "", genderIndex + "", name);
        newGameOption.processOption();

        //ASSERT
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