package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;

/**
 * @author ant
 */
public class TakeRestOption extends Option<Void> {

    public TakeRestOption(GameContext gameContext) {
        super(gameContext, "Take a rest");
    }

    @Override
    public OptionResult<Void> processOption() {
        gameContext.getMainCharacter().takeRest();

        return OptionResult.success();
    }

}
