package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.RoundOutcome;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class TakeRestOption extends Option<RoundOutcome> {

    public TakeRestOption() {
        super("Take a rest");
    }

    @Override
    public OptionResult<RoundOutcome> processOption() {
        GameContext.getInstance().getMainCharacter().takeRest();

        return new OptionResult<>(EventResult.Status.SUCCESS, new RoundOutcome());
    }

}
