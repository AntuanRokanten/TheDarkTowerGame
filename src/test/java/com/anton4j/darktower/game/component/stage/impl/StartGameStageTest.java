package com.anton4j.darktower.game.component.stage.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.impl.LoadGameOption;
import com.anton4j.darktower.game.component.option.impl.NewGameOption;
import com.anton4j.darktower.game.component.scene.OptionsScene;
import com.anton4j.darktower.game.component.stage.GameStage;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author ant
 */
public class StartGameStageTest {

    @Test
    public void getCompletionStatus() {
        // ARRANGE
        StartGameStage startGameStage = new StartGameStage();
        GameContext gameContext = Mockito.mock(GameContext.class);

        boolean isInitialized = new Random().nextBoolean();
        Mockito.when(gameContext.isInitialized()).thenReturn(isInitialized);

        // ACT
        boolean actualStatus = startGameStage.getCompletionStatus(gameContext);

        // ASSERT
        assertEquals(isInitialized, actualStatus);

        Mockito.verify(gameContext).isInitialized();
        verifyNoMoreInteractions(gameContext);
    }

    @Test
    public void stageScene() throws Exception {
        // ARRANGE
        StartGameStage startGameStage = new StartGameStage();
        GameContext gameContext = Mockito.mock(GameContext.class);

        // ACT
        OptionsScene<GameContext> scene = (OptionsScene<GameContext>) startGameStage.stageScene(gameContext);

        // ASSERT
        List<Option<GameContext>> options = (List<Option<GameContext>>) FieldUtils.readField(scene, "options", true);

        assertEquals(2, options.size());
        assertTrue(options.get(0) instanceof NewGameOption);
        assertTrue(options.get(1) instanceof LoadGameOption);
        verifyNoMoreInteractions(gameContext);
    }

    @Test
    public void nextScene() {
        // ARRANGE
        StartGameStage startGameStage = new StartGameStage();

        // ACT
        GameStage nextStage = startGameStage.nextStage();

        // ASSERT
        assertTrue(nextStage instanceof InProgressStage);
    }

}