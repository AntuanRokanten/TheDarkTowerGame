package com.anton4j.darktower.game.component.stage.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.impl.*;
import com.anton4j.darktower.game.component.scene.OptionsScene;
import com.anton4j.darktower.game.component.stage.GameStage;
import com.anton4j.darktower.game.location.GameMap;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author ant
 */
public class InProgressStageTest {

    @Test
    public void getCompletionStatus() {
        // ARRANGE
        boolean isFinalDestination = new Random().nextBoolean();

        InProgressStage inProgressStage = new InProgressStage();
        GameContext gameContext = Mockito.mock(GameContext.class);
        GameMap gameMap= Mockito.mock(GameMap.class);

        Mockito.when(gameContext.getGameMap()).thenReturn(gameMap);
        Mockito.when(gameMap.isFinalDestination()).thenReturn(isFinalDestination);

        // ACT
        boolean actualStatus = inProgressStage.getCompletionStatus(gameContext);

        // ASSERT
        assertEquals(isFinalDestination, actualStatus);

        Mockito.verify(gameContext).getGameMap();
        Mockito.verify(gameMap).isFinalDestination();
        verifyNoMoreInteractions(gameContext, gameMap);
    }

    @Test
    public void stageScene() throws Exception {
        // ARRANGE
        InProgressStage inProgressStage = new InProgressStage();
        GameContext gameContext = Mockito.mock(GameContext.class);

        // ACT
        OptionsScene<Void> scene = (OptionsScene<Void>) inProgressStage.stageScene(gameContext);

        // ASSERT
        List<Option<Void>> options = (List<Option<Void>>) FieldUtils.readField(scene, "options", true);

        assertEquals(7, options.size());

        assertTrue(options.get(0) instanceof ExploreOption);
        assertTrue(options.get(1) instanceof TakeRestOption);
        assertTrue(options.get(2) instanceof MoveOption);
        assertTrue(options.get(3) instanceof CharInfoOption);
        assertTrue(options.get(4) instanceof PrintMapOption);
        assertTrue(options.get(5) instanceof SaveOption);
        assertTrue(options.get(6) instanceof ExitOption);

        verifyNoMoreInteractions(gameContext);
    }

    @Test
    public void nextScene() {
        // ARRANGE
        InProgressStage inProgressStage = new InProgressStage();

        // ACT
        GameStage nextStage = inProgressStage.nextStage();

        // ASSERT
        assertNull(nextStage);
    }

}