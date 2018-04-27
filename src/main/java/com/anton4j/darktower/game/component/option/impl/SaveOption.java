package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.GamePreserver;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;

/**
 * Option that saves current game state.
 *
 * @author ant
 */
public class SaveOption extends Option<Void> {

    public SaveOption(GameContext gameContext) {
        super(gameContext, "Save");
    }

    @Override
    public OptionResult<Void> processOption() {
        GamePreserver.getInstance().save(gameContext);
        return OptionResult.success();
    }
}
