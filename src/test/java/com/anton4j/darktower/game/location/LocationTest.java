package com.anton4j.darktower.game.location;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author ant
 */
public class LocationTest {

    @Test
    public void title() {
        // ARRANGE
        String title = UUID.randomUUID().toString();
        Location location = new Location(title, null, 0);

        // ACT
        String actualTitle = location.title();

        // ASSERT
        assertEquals(title, actualTitle);
    }

    @Test
    public void next() {
        // ARRANGE
        Location nextLocation = new Location(UUID.randomUUID().toString(), null, 0);
        Location location = new Location(UUID.randomUUID().toString(), nextLocation, 0);

        // ACT
        Location actualNextLocation = location.next();

        // ASSERT
        assertEquals(nextLocation, actualNextLocation);
    }

    @Test
    public void accessLevel() {
        // ARRANGE
        int accessLevel = new Random().nextInt();
        Location location = new Location(UUID.randomUUID().toString(), null, accessLevel);

        // ACT
        int actualAccessLevel = location.accessLevel();

        // ASSERT
        assertEquals(accessLevel, actualAccessLevel);
    }
}