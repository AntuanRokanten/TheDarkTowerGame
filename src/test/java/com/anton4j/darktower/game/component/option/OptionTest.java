package com.anton4j.darktower.game.component.option;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.component.option.impl.TakeRestOption;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
public class OptionTest extends OutStreamsInterceprtorTest {

    @Test
    public void processOptionErrorTest() {
        // ARRANGE
        GameContext context = Mockito.mock(GameContext.class);
        Char character = Mockito.mock(Char.class);

        Mockito.when(context.getMainCharacter()).thenReturn(character);
        Mockito.doThrow(new RuntimeException("TEST EXCEPTION")).when(character).takeRest();

        Option option = new TakeRestOption(context);

        // ACT
        OptionResult optionResult = option.processOption();

        // ASSERT
        assertFalse(optionResult.isSuccess());
        assertTrue(outContent.toString().contains("Error occurred while processing option."));
    }
}