package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;

/**
 * @author anton
 */
public class PrintMapOption extends Option<Void> {

    public PrintMapOption() {
        super("Print map");
    }

    @Override
    public OptionResult<Void> processOption() {
        GameContext.getInstance().getGameMap().print();
        //todo print current location
        return new OptionResult<>(EventResult.Status.SUCCESS, null);
    }

}
