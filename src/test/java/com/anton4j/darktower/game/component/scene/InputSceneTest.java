package com.anton4j.darktower.game.component.scene;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.util.TestConsoleUtils;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
public class InputSceneTest extends OutStreamsInterceprtorTest {

    @Test
    public void processScene() {
        // ARRANGE
        String sceneTitle = UUID.randomUUID().toString();
        InputScene inputScene = new InputScene(new ConsoleLine(sceneTitle));

        String sceneEnteredValue = UUID.randomUUID().toString();

        // ACT
        TestConsoleUtils.enterValue(sceneEnteredValue);
        String sceneResult = inputScene.processScene();

        // ASSERT
        assertEquals(sceneEnteredValue, sceneResult);
        assertTrue(outContent.toString().contains(sceneTitle));
    }

}