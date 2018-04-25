package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.GamePreserver;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * @author anton
 */
public class SaveExitOption extends Option<Void> {

    public SaveExitOption(GameContext gameContext) {
        super(gameContext, "Save and exit");
    }

    @Override
    public OptionResult<Void> processOption() {
        OptionResult<Void> result;
        try {
            GamePreserver gamePreserver = GamePreserver.getInstance();

            gamePreserver.save(gameContext);
            List<Path> paths = gamePreserver.listSaves();
            GameContext context = gamePreserver.restoreFrom(paths.get(0));

            result = new OptionResult<>(EventResult.Status.SUCCESS, null);
            //todo
        } catch (IOException e) {
            new ConsoleLine("Unable to save the game: " + e.getClass(), FontColor.RED).println();

            result = new OptionResult<>(EventResult.Status.ERROR, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            result = new OptionResult<>(EventResult.Status.ERROR, null);
        }

        return result;
    }
}
