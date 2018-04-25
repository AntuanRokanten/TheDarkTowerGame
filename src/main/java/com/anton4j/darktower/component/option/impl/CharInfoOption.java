package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.character.Char;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

/**
 * @author anton
 */
public class CharInfoOption extends Option<Void> {

    public CharInfoOption() {
        super("Print character info");
    }

    @Override
    public OptionResult<Void> processOption() {
        Char mainCharacter = GameContext.getInstance().getMainCharacter();

        new ConsoleLine(mainCharacter.charInfo(), FontColor.WHITE).println();
        return new OptionResult<>(EventResult.Status.SUCCESS, null);
    }

}
