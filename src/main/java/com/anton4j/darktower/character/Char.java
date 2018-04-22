package com.anton4j.darktower.character;

/**
 * @author ant
 */
public class Char {

    private final Gender gender;
    private final String name;
    private final Race race;


    public Char(Gender gender, String name, Race race) {
        this.gender = gender;
        this.name = name;
        this.race = race;
    }
}
