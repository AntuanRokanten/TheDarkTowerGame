package com.anton4j.darktower.game.location;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleLines;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.util.TestCharUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static junit.framework.TestCase.*;

/**
 * @author ant
 */
public class GameMapTest extends OutStreamsInterceprtorTest {

    private int nextAccessLevel;

    private Location nextLocation;
    private Location location;
    private GameMap gameMap;

    private List<ConsoleLine> graphicMap;

    @Before
    public void setup() {
        nextAccessLevel = new Random().nextInt();
        nextLocation = new Location(UUID.randomUUID().toString(), null, nextAccessLevel);
        location = new Location(UUID.randomUUID().toString(), nextLocation, 0);

        graphicMap = Arrays.asList(
              new ConsoleLine(UUID.randomUUID().toString()),
              new ConsoleLine(UUID.randomUUID().toString()),
              new ConsoleLine(UUID.randomUUID().toString()),
              new ConsoleLine(UUID.randomUUID().toString())
        );

        gameMap = new GameMap(new ConsoleLines(graphicMap), location);
    }

    @Test
    public void print() throws Exception {
        // ACT
        gameMap.print();

        // ASSERT
        String printedMap = outContent.toString();
        for (ConsoleLine consoleLine : graphicMap) {
            String value = (String) FieldUtils.readField(consoleLine, "value", true);

            assertTrue(printedMap.contains(value));
        }
    }

    @Test
    public void moveToNextLocationLevelNotBigEnough() throws Exception {
        // ARRANGE
        Char character = TestCharUtils.randomChar();
        FieldUtils.writeDeclaredField(character, "level", nextAccessLevel - 1, true);

        // ACT
        gameMap.moveToNextLocation(character);

        // ASSERT
        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Character cannot move to the next location.\n" +
              "You should gain level " + nextAccessLevel));

        Location currentLocation = gameMap.currentLocation();
        assertEquals(location, currentLocation);
    }

    @Test
    public void moveToNextLocationSuccess() throws Exception {
        // ARRANGE
        Char character = TestCharUtils.randomChar();
        FieldUtils.writeDeclaredField(character, "level", nextAccessLevel + 1, true);

        // ACT
        gameMap.moveToNextLocation(character);

        // ASSERT
        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Character moved to " + nextLocation.title() + " location"));

        Location currentLocation = gameMap.currentLocation();
        assertEquals(nextLocation, currentLocation);
    }

    @Test
    public void moveToNextLocationAlreadyFinalDestination() throws Exception {
        // ARRANGE
        Char character = TestCharUtils.randomChar();
        FieldUtils.writeDeclaredField(character, "level", nextAccessLevel + 1, true);
        FieldUtils.writeDeclaredField(location, "next", null, true);

        // ACT
        gameMap.moveToNextLocation(character);

        // ASSERT
        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains("Character already reached the final destination"));
    }

    @Test
    public void isFinalDestination() {
        // ACT
        boolean isFinalDest = gameMap.isFinalDestination();

        // ASSERT
        assertFalse(isFinalDest);
    }

    @Test
    public void currentLocation() {
        // ACT
        Location actualLocation = gameMap.currentLocation();

        // ASSERT
        assertEquals(this.location, actualLocation);
    }

    @Test
    public void currentLocationTitle() {
        // ACT
        String actualLocTitle = gameMap.currentLocationTitle();

        // ASSERT
        assertEquals(location.title(), actualLocTitle);
    }

    @Test
    public void canMoveToNextLocationFailure() throws IllegalAccessException {
        // ARRANGE
        Char character = TestCharUtils.randomChar();
        FieldUtils.writeDeclaredField(character, "level", nextAccessLevel - 1, true);

        // ACT
        boolean canMove = gameMap.canMoveToNextLocation(character);

        // ASSERT
        assertFalse(canMove);
    }

    @Test
    public void canMoveToNextLocationSuccess() throws IllegalAccessException {
        // ARRANGE
        Char character = TestCharUtils.randomChar();
        FieldUtils.writeDeclaredField(character, "level", nextAccessLevel + 1, true);

        // ACT
        boolean canMove = gameMap.canMoveToNextLocation(character);

        // ASSERT
        assertTrue(canMove);
    }

    @Test
    public void canMoveToNextLocationSuccessSameLevel() throws IllegalAccessException {
        // ARRANGE
        Char character = TestCharUtils.randomChar();
        FieldUtils.writeDeclaredField(character, "level", nextAccessLevel, true);

        // ACT
        boolean canMove = gameMap.canMoveToNextLocation(character);

        // ASSERT
        assertTrue(canMove);
    }
}