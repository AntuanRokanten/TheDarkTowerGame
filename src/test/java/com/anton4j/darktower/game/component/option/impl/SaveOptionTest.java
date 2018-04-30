package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.GamePreserver;
import com.anton4j.darktower.game.component.option.OptionResult;
import org.junit.Test;
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
public class SaveOptionTest {

    @Test
    public void processOption() {
        GameContext context = Mockito.mock(GameContext.class);
        GamePreserver preserver = Mockito.mock(GamePreserver.class);

        PowerMockito.mockStatic(GamePreserver.class);
        PowerMockito.when(GamePreserver.getInstance()).thenReturn(preserver);

        SaveOption saveOption = new SaveOption(context);

        // ACT
        OptionResult<Void> result = saveOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());

        Mockito.verify(preserver).save(context);
        Mockito.verifyNoMoreInteractions(context, preserver);
    }
}