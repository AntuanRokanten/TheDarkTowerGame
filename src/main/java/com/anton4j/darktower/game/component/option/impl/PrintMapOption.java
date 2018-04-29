package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.location.GameMap;

/**
 * Option that prints game map.
 *
 * @author ant
 */
public class PrintMapOption extends Option<Void> {

    public PrintMapOption(GameContext gameContext) {
        super(gameContext, "Print map");
    }

    @Override
    protected OptionResult<Void> processOptionForResult() {
        GameMap gameMap = gameContext.getGameMap();

        new ConsoleLine("Character is currently at " + gameMap.currentLocationTitle() + " location", FontColor.YELLOW).println();
        gameMap.print();

        return OptionResult.success();
    }

}
