package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.OutStreamsInterceprtorTest;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.location.GameMap;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.Assert.assertTrue;

/**
 * @author ant
 */
public class PrintMapOptionTest extends OutStreamsInterceprtorTest {

    @Test
    public void processOption() {
        String locationTitle = UUID.randomUUID().toString();

        GameContext context = Mockito.mock(GameContext.class);
        GameMap map = Mockito.mock(GameMap.class);

        Mockito.when(context.getGameMap()).thenReturn(map);
        Mockito.when(map.currentLocationTitle()).thenReturn(locationTitle);

        PrintMapOption infoOption = new PrintMapOption(context);

        // ACT
        OptionResult<Void> result = infoOption.processOption();

        // ASSERT
        assertTrue(result.isSuccess());

        Mockito.verify(context).getGameMap();
        Mockito.verify(map).print();
        Mockito.verify(map).currentLocationTitle();
        Mockito.verifyNoMoreInteractions(context, map);

        assertTrue(outContent.toString().contains("You is currently at " + locationTitle + " location"));
    }

}