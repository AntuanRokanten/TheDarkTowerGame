package com.anton4j.darktower;

import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Saves game state to the disk.
 *
 * @author ant
 */
public class GamePreserver {

    private static final GamePreserver INSTANCE = new GamePreserver();

    public static GamePreserver getInstance() {
        return INSTANCE;
    }

    private final Path saveFolder = Paths.get(System.getProperty("user.home") + File.separator + "darktower" + File.separator);

    public void save(GameContext gameContext) {
        try {
            createFoldersIfNeeded();

            Path saveFile = saveFolder.resolve("save" + System.nanoTime() + ".ser");
            Files.createFile(saveFile);

            try (FileOutputStream fout = new FileOutputStream(saveFile.toFile());
                 ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                oos.writeObject(gameContext);
            }
            new ConsoleLine("Game state is successfully saved", FontColor.BLUE).println();
        } catch (IOException e) {
            new ConsoleLine("Cannot save game state", FontColor.RED).println();
        }
    }

    public List<Path> listSaves() {
        try {
            createFoldersIfNeeded();
            return Files.list(saveFolder).collect(Collectors.toList());
        } catch (IOException e) {
            new ConsoleLine("Cannot list save files", FontColor.RED).println();
            return Collections.emptyList();
        }
    }

    public Optional<GameContext> restoreFrom(Path savePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath.toFile()))) {
            GameContext context = (GameContext) ois.readObject();
            return Optional.of(context);
        } catch (ClassNotFoundException | IOException e) {
            new ConsoleLine("Cannot restore game state from file", FontColor.RED).println();
            return Optional.empty();
        }
    }

    private void createFoldersIfNeeded() throws IOException {
        if (Files.notExists(saveFolder)) {
            Files.createDirectories(saveFolder);
        }
    }

}
