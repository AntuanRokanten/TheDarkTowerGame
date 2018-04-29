package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.location.GameMap;
import com.anton4j.darktower.game.location.Location;

/**
 * Option for moving to the next location.
 *
 * @author ant
 */
public class MoveOption extends Option<Void> {

    public MoveOption(GameContext gameContext) {
        super(gameContext, "Move to the next location");
    }

    @Override
    public OptionResult<Void> processOption() {
        GameMap gameMap = gameContext.getGameMap();

        if(gameMap.canMoveToNextLocation(gameContext.getMainCharacter())) {
            gameMap.moveToNextLocation(gameContext.getMainCharacter());
            gameContext.getGameStats().areaExplored();
        } else {
            Location nextLocation = gameMap.currentLocation().next();
            new ConsoleLine("Character cannot move to the next location. Needed level is " + nextLocation.accessLevel()).println();
        }

        return OptionResult.success();
    }

}
