package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.GamePreserver;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.util.TestConsoleUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Collections;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({GamePreserver.class})
public class LoadGameOptionTest extends OutStreamsInterceprtorTest {

   @Test
   public void processOptionNoSaveFiles() throws Exception {
      GameContext context = Mockito.mock(GameContext.class);

      GamePreserver preserver = Mockito.mock(GamePreserver.class);
      Mockito.when(preserver.listSaves()).thenReturn(Collections.emptyList());

      PowerMockito.mockStatic(GamePreserver.class);
      PowerMockito.when(GamePreserver.getInstance()).thenReturn(preserver);

      LoadGameOption loadGameOption = new LoadGameOption(context);

      // ACT
      OptionResult<GameContext> result = loadGameOption.processOption();

      // ASSERT
      assertFalse(result.isSuccess());

      Mockito.verify(preserver).listSaves();
      Mockito.verifyNoMoreInteractions(context);

      assertTrue(outContent.toString().contains("No valid save files found"));
   }

   @Test
   public void processOptionSaveFileExist() throws Exception {
      GameContext context = Mockito.mock(GameContext.class);
      GameContext restoredContext = Mockito.mock(GameContext.class);

      Path save1 = Files.createTempFile("save-1", ".tmp");
      Path save2 = Files.createTempFile("save-2", ".tmp");
      Path save3 = Files.createTempFile("save-3", ".tmp");

      try {
         BasicFileAttributes attrs = Mockito.mock(BasicFileAttributes.class);
         GamePreserver preserver = Mockito.mock(GamePreserver.class);

         Mockito.when(attrs.creationTime()).thenReturn(FileTime.from(Instant.now()));

         Mockito.when(preserver.listSaves()).thenReturn(asList(save1, save2, save3));
         Mockito.when(preserver.restoreFrom(save1)).thenReturn(Optional.of(restoredContext));

         PowerMockito.mockStatic(GamePreserver.class);
         PowerMockito.when(GamePreserver.getInstance()).thenReturn(preserver);

         LoadGameOption loadGameOption = new LoadGameOption(context);

         // ACT
         TestConsoleUtils.enterValue(1);
         OptionResult<GameContext> result = loadGameOption.processOption();

         // ASSERT
         assertTrue(result.isSuccess());

         Mockito.verify(preserver).listSaves();
         Mockito.verify(preserver).restoreFrom(save1);
         Mockito.verify(context).copyFrom(restoredContext);
         Mockito.verifyNoMoreInteractions(context, restoredContext, preserver);

         assertTrue(outContent.toString().contains("Choose save file"));
         assertTrue(outContent.toString().contains("1 - Save file created at"));
         assertTrue(outContent.toString().contains("2 - Save file created at"));
         assertTrue(outContent.toString().contains("3 - Save file created at"));
      } finally {
         Files.delete(save1);
         Files.delete(save2);
         Files.delete(save3);
      }
   }

}