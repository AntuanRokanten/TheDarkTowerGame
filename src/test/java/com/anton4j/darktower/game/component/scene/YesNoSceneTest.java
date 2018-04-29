package com.anton4j.darktower.game.component.scene;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.util.TestConsoleUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author ant
 */
public class YesNoSceneTest extends OutStreamsInterceprtorTest {

    @Test
    public void processSceneYesResponse() {
        // ARRANGE
        GameContext gameContext = Mockito.mock(GameContext.class);
        String sceneTitle = UUID.randomUUID().toString();

        YesNoScene scene = new YesNoScene(new ConsoleLine(sceneTitle));

        // ACT
        TestConsoleUtils.enterValue(1 + "");
        YesNoScene.Response response = scene.processScene();

        // ASSERT
        String actualOut = outContent.toString();
        assertEquals(sceneTitle + "\u001B[0m\n" +
              "1 - Yes\u001B[0m\n" +
              "2 - No\u001B[0m\n", actualOut);

        assertEquals(YesNoScene.Response.YES, response);

        verifyNoMoreInteractions(gameContext);
    }

    @Test
    public void processSceneNoResponse() {
        // ARRANGE
        GameContext gameContext = Mockito.mock(GameContext.class);
        String sceneTitle = UUID.randomUUID().toString();

        YesNoScene scene = new YesNoScene(new ConsoleLine(sceneTitle));

        // ACT
        TestConsoleUtils.enterValue(2 + "");
        YesNoScene.Response response = scene.processScene();

        // ASSERT
        String actualOut = outContent.toString();
        assertEquals(sceneTitle + "\u001B[0m\n" +
              "1 - Yes\u001B[0m\n" +
              "2 - No\u001B[0m\n", actualOut);

        assertEquals(YesNoScene.Response.NO, response);

        verifyNoMoreInteractions(gameContext);
    }

}