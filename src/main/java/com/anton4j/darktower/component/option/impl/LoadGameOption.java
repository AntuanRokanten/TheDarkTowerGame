package com.anton4j.darktower.component.option.impl;

import com.anton4j.darktower.GameContext;
import com.anton4j.darktower.GamePreserver;
import com.anton4j.darktower.component.event.EventResult;
import com.anton4j.darktower.component.option.Option;
import com.anton4j.darktower.component.option.OptionResult;
import com.anton4j.darktower.component.option.ValueOption;
import com.anton4j.darktower.component.scene.OptionsScene;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author anton
 */
public class LoadGameOption extends Option<GameContext> {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");

    public LoadGameOption(GameContext gameContext) {
        super(gameContext, "Load game");
    }

    @Override
    public OptionResult<GameContext> processOption() {
        GamePreserver gamePreserver = GamePreserver.getInstance();

        List<Path> savesList = gamePreserver.listSaves();
        if (savesList.isEmpty()) {
            new ConsoleLine("No valid save files found", FontColor.RED).println();
            return new OptionResult<>(EventResult.Status.ERROR, null);
        }

        List<Option<Path>> options = savesList
              .stream()
              .map(saveFile -> {
                  try {
                      BasicFileAttributes fileAttr = Files.readAttributes(saveFile, BasicFileAttributes.class);
                      return new ValueOption<>(saveFile, "Save file " +
                            "created at " + dateFormat.format(fileAttr.creationTime().toMillis()), gameContext);
                  } catch (IOException e) {
                      new ConsoleLine("Cannot read attributes of save file").println();
                      return null;
                  }
              })
              .collect(Collectors.toList());

        Path saveFile = new OptionsScene<>(options, new ConsoleLine("Choose save file", FontColor.WHITE)).processScene();

        Optional<GameContext> contextOptional = gamePreserver.restoreFrom(saveFile);
        if (contextOptional.isPresent()) {
            GameContext loadedContext = contextOptional.get();

            gameContext.copyFrom(loadedContext);

            return new OptionResult<>(EventResult.Status.SUCCESS, contextOptional.get()); // todo
        } else {
            return new OptionResult<>(EventResult.Status.ERROR, null);
        }
    }
}
