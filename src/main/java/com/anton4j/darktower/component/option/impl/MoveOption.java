package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.console.location.GameMap;

/**
 * @author anton
 */
public class MoveOption extends Option<Void> {

    public MoveOption() {
        super("Move to the next location");
    }

    @Override
    public OptionResult<Void> processOption() {
        GameMap gameMap = GameContext.getInstance().getGameMap();
        gameMap.moveToNextLocation(GameContext.getInstance().getMainCharacter());

        return new OptionResult<>(EventResult.Status.SUCCESS, null);
    }

}
