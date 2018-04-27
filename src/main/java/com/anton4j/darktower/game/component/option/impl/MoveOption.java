package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.location.GameMap;

/**
 * @author ant
 */
public class MoveOption extends Option<Void> {

    public MoveOption(GameContext gameContext) {
        super(gameContext, "Move to the next location");
    }

    @Override
    public OptionResult<Void> processOption() {
        GameMap gameMap = gameContext.getGameMap();
        gameMap.moveToNextLocation(gameContext.getMainCharacter());

        return OptionResult.success();
    }

}
