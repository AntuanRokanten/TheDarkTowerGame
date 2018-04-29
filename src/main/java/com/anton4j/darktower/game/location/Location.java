package com.anton4j.darktower.game.location;

import java.io.Serializable;
import java.util.Objects;

/**
 * Location of a map.
 *
 * @author ant
 */
public class Location implements Serializable {

    /**
     * Location title.
     */
    private final String title;

    /**
     * Reference to the next location.
     */
    private final Location next;

    /**
     * Level the character should be of in order to get access to this location.
     */
    private final int accessLevel;

    public Location(String title, Location next, int accessLevel) {
        this.title = title;
        this.next = next;
        this.accessLevel = accessLevel;
    }

    /**
     * @return title of this location.
     */
    public String title() {
        return title;
    }

    /**
     * @return location that goes after this location.
     */
    public Location next() {
        return next;
    }

    /**
     * @return level needed for accessing location.
     */
    public int accessLevel() {
        return accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return accessLevel == location.accessLevel &&
              Objects.equals(title, location.title) &&
              Objects.equals(next, location.next);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, next, accessLevel);
    }
}