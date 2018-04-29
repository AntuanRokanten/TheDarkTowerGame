package com.anton4j.darktower.game.location;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleLines;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.game.character.Char;

import java.io.Serializable;

/**
 * Map of the game.
 *
 * @author ant
 */
public class GameMap implements Serializable {

    /**
     * Graphic representation of the map.
     */
    private final ConsoleLines graphicMap;

    /**
     * Current map location.
     */
    private Location location;

    public GameMap(ConsoleLines graphicMap, Location location) {
        this.graphicMap = graphicMap;
        this.location = location;
    }

    /**
     * Prints map graphics.
     */
    public void print() {
        graphicMap.print();
    }

    /**
     * Moves to next location if possible.
     *
     * @param character main game character.
     */
    public void moveToNextLocation(Char character) {
        if (!isFinalDestination()) {
            if (canMoveToNextLocation(character)) {
                location = location.next();
                new ConsoleLine("Character moved to " + location.title() + " location", FontColor.YELLOW).println();
            } else {
                new ConsoleLine("Character cannot move to the next location." +
                      "\nYou should gain level " + location.next().accessLevel(), FontColor.RED).println();
            }
        } else {
            new ConsoleLine("Character already reached the final destination", FontColor.YELLOW).println();

        }
    }

    /**
     * Checks if current location is the final one.
     *
     * @return true if this location if a final destination.
     */
    public boolean isFinalDestination() {
        return location.next() == null;
    }

    /**
     * @return current location.
     */
    public Location currentLocation() {
        return location;
    }

    /**
     * @return title of the current location.
     */
    public String currentLocationTitle() {
        return location.title();
    }

    /**
     * Checks if the character can move to the next location.
     *
     * @param character character for checking.
     * @return true if character can move to the next locatin.
     */
    public boolean canMoveToNextLocation(Char character) {
        Location next = location.next();
        if (next == null) {
            return false;
        } else {
            return character.level() >= next.accessLevel();
        }
    }

}
