package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.character.RoundOutcome;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.console.ProgressBar;

/**
 * @author anton
 */
public class TakeRestOption extends Option<RoundOutcome> {

    public TakeRestOption() {
        super("Take a rest");
    }

    @Override
    public OptionResult<RoundOutcome> processOption() {
        new ConsoleLine("You are resting with your Ka-Tet", FontColor.GREEN).println();
        new ProgressBar(500).start();

        return new OptionResult<>(EventResult.Status.SUCCESS, new RoundOutcome());
    }

}
