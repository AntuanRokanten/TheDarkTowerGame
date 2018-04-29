package com.anton4j.darktower.game;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.console.ConsoleLines;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.character.CharRace;
import com.anton4j.darktower.game.character.Gender;
import com.anton4j.darktower.game.location.GameMap;
import com.anton4j.darktower.game.location.Location;
import com.anton4j.darktower.util.TestRandomUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
public class GamePreserverTest extends OutStreamsInterceprtorTest {

    private final GamePreserver instance = GamePreserver.getInstance();


    private GameContext gameContext;
    private Path saveFile;
    private List<Path> savesBefore;


    @Before
    public void setUp() {
        GamePreserver instance = GamePreserver.getInstance();

        Location location = new Location(UUID.randomUUID().toString(), null, 0);
        GameMap gameMap = new GameMap(new ConsoleLines(Collections.emptyList()), location);

        Gender gender = TestRandomUtils.randomEnum(Gender.values());
        CharRace race = TestRandomUtils.randomEnum(CharRace.values());
        String name = UUID.randomUUID().toString();

        Char character = new Char.CharBuilder()
              .withGender(gender)
              .withRace(race)
              .withName(name)
              .build();

        gameContext = new GameContext();
        gameContext.setGameMap(gameMap);
        gameContext.setGameStats(new GameStats());
        gameContext.setMainCharacter(character);

        savesBefore = instance.listSaves();
    }

    @Test
    public void saveRestoreTest() {
        // ACT
        instance.save(gameContext);

        // ASSERT
        assertTrue(outContent.toString().contains("Game state is successfully saved"));

        List<Path> savesAfter = instance.listSaves();
        savesAfter.removeAll(savesBefore);

        assertEquals(1, savesAfter.size());

        saveFile = savesAfter.get(0);

        // ACT
        Optional<GameContext> restoredContextOptional = instance.restoreFrom(saveFile);

        // ASSERT
        assertTrue(restoredContextOptional.isPresent());
        GameContext restoredContext = restoredContextOptional.get();

        assertEquals(gameContext, restoredContext);
    }

    @After
    public void cleanUp() throws IOException {
        if (saveFile != null) {
            Files.delete(saveFile);
        }
    }

}