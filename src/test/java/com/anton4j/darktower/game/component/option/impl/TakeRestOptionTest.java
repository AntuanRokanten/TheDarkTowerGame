package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.component.option.OptionResult;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author ant
 */
public class TakeRestOptionTest {

    @Test
    public void processOption() {
        // ARRANGE
        Char character = Mockito.mock(Char.class);

        GameContext gameContext = new GameContext();
        gameContext.setMainCharacter(character);

        TakeRestOption takeRestOption = new TakeRestOption(gameContext);

        // ACT
        OptionResult<Void> result = takeRestOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());

        verify(character).takeRest();
        verifyNoMoreInteractions(character);
    }
}