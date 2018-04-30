package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.component.option.OptionResult;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
public class CharInfoOptionTest extends OutStreamsInterceprtorTest {

    @Test
    public void processOption() {
        String charInfo = UUID.randomUUID().toString();

        GameContext context = Mockito.mock(GameContext.class);
        Char character = Mockito.mock(Char.class);

        Mockito.when(context.getMainCharacter()).thenReturn(character);
        Mockito.when(character.charInfo()).thenReturn(charInfo);

        CharInfoOption infoOption = new CharInfoOption(context);

        // ACT
        OptionResult<Void> result = infoOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());

        Mockito.verify(context).getMainCharacter();
        Mockito.verify(character).charInfo();
        Mockito.verifyNoMoreInteractions(context, character);

        assertTrue(outContent.toString().contains(charInfo));
    }
}