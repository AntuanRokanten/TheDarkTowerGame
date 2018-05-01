package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.GamePreserver;
import com.anton4j.darktower.util.TestConsoleUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GamePreserver.class})
public class ExitOptionTest extends OutStreamsInterceprtorTest {

   @Rule
   public final ExpectedSystemExit exit = ExpectedSystemExit.none();

   @Test
   public void processOptionDoNotSave() throws Exception {
      GameContext context = Mockito.mock(GameContext.class);

      ExitOption exitOption = new ExitOption(context);

      exit.expectSystemExitWithStatus(0);

      GamePreserver preserver = Mockito.mock(GamePreserver.class);

      PowerMockito.mockStatic(GamePreserver.class);
      PowerMockito.when(GamePreserver.getInstance()).thenReturn(preserver);

      // ACT
      TestConsoleUtils.enterValue(1);
      exitOption.processOption();

      // ASSERT
      Mockito.verify(preserver).save(context);
      Mockito.verifyNoMoreInteractions(context);

      String consoleOutput = outContent.toString();
      assertTrue(consoleOutput.contains("Save before exit?"));
      assertTrue(consoleOutput.contains("1 - Yes"));
      assertTrue(consoleOutput.contains("2 - No"));
      assertTrue(consoleOutput.contains("See you soon!"));
   }

}