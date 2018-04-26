package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.component.scene.YesNoScene;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

/**
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

        return new OptionResult<>(EventResult.Status.SUCCESS, null);
    }
}
