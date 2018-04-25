package com.anton4j.darktower.console.location;

/**
 * @author ant
 */
public class Location {

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


    public String title() {
        return title;
    }

    public Location next() {
        return next;
    }

    public int accessLevel() {
        return accessLevel;
    }

}