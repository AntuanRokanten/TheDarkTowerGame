package com.anton4j.darktower.game.component.option.impl;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.game.GameContext;
import com.anton4j.darktower.game.component.option.Option;
import com.anton4j.darktower.game.component.option.OptionResult;
import com.anton4j.darktower.game.component.scene.YesNoScene;

/**
 * Options that exits a game.
 *
 * @author ant
 */
public class ExitOption extends Option<Void> {

    public ExitOption(GameContext gameContext) {
        super(gameContext, "Exit");
    }

    @Override
    public OptionResult<Void> processOption() {
        YesNoScene.Response response = new YesNoScene(new ConsoleLine("Save before exit?", FontColor.GREEN)).processScene();

        if (response.isPositive()) {
            new SaveOption(gameContext).processOption();
        }

        new ConsoleLine("See you soon!", FontColor.WHITE).println();
        System.exit(1);

        return OptionResult.success();
    }
}
