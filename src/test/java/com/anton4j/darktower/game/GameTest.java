package com.anton4j.darktower.game;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.game.component.stage.GameStage;
import com.anton4j.darktower.util.TestConsoleUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
public class GameTest extends OutStreamsInterceprtorTest {

    @Test
    public void start() {
        // ARRANGE
        IntroOutro intro = Mockito.mock(IntroOutro.class);
        IntroOutro outro = Mockito.mock(IntroOutro.class);
        GameStage gameStage = Mockito.mock(GameStage.class);
        GameStats gameStats = Mockito.mock(GameStats.class);
        GameContext gameContext = Mockito.mock(GameContext.class);

        Mockito.when(gameContext.getGameStats()).thenReturn(gameStats);

        String statsString = UUID.randomUUID().toString();
        Mockito.when(gameStats.toString()).thenReturn(statsString);

        Mockito.when(gameStage.stageCompleted()).thenReturn(false, true);

        Game game = new Game(intro, outro, gameStage, gameContext);

        // ACT
        TestConsoleUtils.enterValue("2");
        game.start();

        // ASSERT
        Mockito.verify(gameContext).getGameStats();
        Mockito.verify(intro).play();
        Mockito.verify(outro).play();
        Mockito.verify(gameStage).processScene(gameContext);
        Mockito.verify(gameStage).nextStage();
        Mockito.verify(gameStage, Mockito.times(2)).stageCompleted();
        Mockito.verifyNoMoreInteractions(intro, outro, gameStage, gameContext);

        String consoleOutput = outContent.toString();
        assertTrue(consoleOutput.contains(statsString));
        assertTrue(consoleOutput.contains("Start game again?"));
        assertTrue(consoleOutput.contains("1 - Yes"));
        assertTrue(consoleOutput.contains("2 - No"));
        assertTrue(consoleOutput.contains("See you soon!"));
    }

    @Test
    public void defaultInit() throws IllegalAccessException {
        // ACT
        Game game = new Game();

        // ASSERT
        IntroOutro intro = (IntroOutro) FieldUtils.readField(game, "intro", true);
        IntroOutro outro = (IntroOutro) FieldUtils.readField(game, "outro", true);
        GameStage initialStage = (GameStage) FieldUtils.readField(game, "initialStage", true);
        GameContext gameContext = (GameContext) FieldUtils.readField(game, "gameContext", true);

        assertNotNull(intro);
        assertNotNull(outro);
        assertNotNull(initialStage);
        assertNotNull(gameContext);
    }

}