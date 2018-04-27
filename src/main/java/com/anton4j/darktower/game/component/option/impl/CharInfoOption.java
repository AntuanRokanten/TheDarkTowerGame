package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.character.Char;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

/**
 * @author ant
 */
public class CharInfoOption extends Option<Void> {

    public CharInfoOption(GameContext gameContext) {
        super(gameContext, "Print character info");
    }

    @Override
    public OptionResult<Void> processOption() {
        Char mainCharacter = gameContext.getMainCharacter();
        new ConsoleLine(mainCharacter.charInfo(), FontColor.WHITE).println();

        return OptionResult.success();
    }

}
