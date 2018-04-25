package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.console.location.GameMap;

/**
 * @author anton
 */
public class PrintMapOption extends Option<Void> {

    public PrintMapOption(GameContext gameContext) {
        super(gameContext, "Print map");
    }

    @Override
    public OptionResult<Void> processOption() {
        GameMap gameMap = gameContext.getGameMap();

        new ConsoleLine("Character is currently at " + gameMap.currentLocationTitle() + " location", FontColor.YELLOW).println();
        gameMap.print();

        return new OptionResult<>(EventResult.Status.SUCCESS, null);
    }

}
