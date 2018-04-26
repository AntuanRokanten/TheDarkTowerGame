package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.GamePreserver;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class SaveExitOption extends Option<Void> {

    public SaveExitOption(GameContext gameContext) {
        super(gameContext, "Save and exit");
    }

    @Override
    public OptionResult<Void> processOption() {
        GamePreserver.getInstance().save(gameContext);
        return new OptionResult<>(EventResult.Status.SUCCESS, null);
    }
}
