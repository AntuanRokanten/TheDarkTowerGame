package com.anton4j.darktower.console.location;

import com.anton4j.darktower.ConsoleLines;
import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

import java.io.Serializable;

/**
 * @author ant
 */
public class GameMap implements Serializable {

    private final ConsoleLines graphicMap;
    private Location location;

    public GameMap(ConsoleLines graphicMap, Location location) {
        this.graphicMap = graphicMap;
        this.location = location;
    }

    public void print() {
        graphicMap.print();
    }

    public void moveToNextLocation(Char character) {
        if (!isFinalDestination()) {
            if (canMoveToNextLocation(character)) {
                location = location.next();
                new ConsoleLine("Character moved to " + location.title() + " location", FontColor.YELLOW).println();
            } else {
                new ConsoleLine("Character cannot move to the next location." +
                      "\nCharacter should gain level " + location.next().accessLevel(), FontColor.RED).println();
            }
        } else {
            new ConsoleLine("Character already reached the final destination", FontColor.YELLOW).println();

        }
    }

    public boolean isFinalDestination() {
        return location.next() == null;
    }

    public String currentLocationTitle() {
        return location.title();
    }

    private boolean canMoveToNextLocation(Char character) {
        return character.level() >= location.next().accessLevel();
    }

}
