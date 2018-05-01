package com.anton4j.darktower.game.component.scene;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.character.CharRace;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.util.OptionsFromEnum;
import com.anton4j.darktower.util.TestConsoleUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author ant
 */
public class OptionsSceneTest extends OutStreamsInterceprtorTest {

    @Test
    public void processScene() {
        // ARRANGE
        GameContext gameContext = Mockito.mock(GameContext.class);
        String sceneTitle = UUID.randomUUID().toString();

        List<Option<CharRace>> options = new OptionsFromEnum<>(CharRace.values()).options(gameContext);
        OptionsScene<CharRace> optionsScene = new OptionsScene<>(options, new ConsoleLine(sceneTitle));

        // ACT
        TestConsoleUtils.enterValue(String.valueOf(options.size() + 10)); // invalid input
        TestConsoleUtils.enterValue(String.valueOf(options.size() - 1)); // valid input

        CharRace sceneResult = optionsScene.processScene();

        // ASSERT
        String actualOut = outContent.toString();
        assertTrue(actualOut.contains(sceneTitle));
        assertTrue(actualOut.contains("1 - Human"));
        assertTrue(actualOut.contains("2 - Taheen"));
        assertTrue(actualOut.contains("3 - Can toi"));

        assertEquals(CharRace.values()[options.size() - 2], sceneResult);

        verifyNoMoreInteractions(gameContext);
    }

}