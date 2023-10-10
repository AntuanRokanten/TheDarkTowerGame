package com.anton4j.darktower.util;

import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.character.CharRace;
import com.anton4j.darktower.game.character.Gender;

import java.util.UUID;

/**
 * @author ant
 */
public class TestCharUtils {

    public static Char randomChar() {
        CharRace charRole = TestRandomUtils.randomEnum(CharRace.values());
        Gender gender = TestRandomUtils.randomEnum(Gender.values());
        String name = UUID.randomUUID().toString();

        return new Char.CharBuilder()
              .withGender(gender)
              .withRace(charRole)
              .withName(name)
              .build();
    }

}
