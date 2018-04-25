package com.anton4j.darktower;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ant
 */
public class GamePreserver {

    private static final GamePreserver INSTANCE = new GamePreserver();

    public static GamePreserver getInstance() {
        return INSTANCE;
    }

    private final Path saveFolder = Paths.get(System.getProperty("user.home") + File.separator + "darktower" + File.separator);

    public void save(GameContext gameContext) throws IOException {
        createFoldersIfNeeded();

        Path saveFile = saveFolder.resolve("save" + System.nanoTime() + ".ser");
        Files.createFile(saveFile);

        try (FileOutputStream fout = new FileOutputStream(saveFile.toFile());
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(gameContext);
        }
    }

    public List<Path> listSaves() throws IOException {
        createFoldersIfNeeded();
        return Files.list(saveFolder).collect(Collectors.toList());
    }

    public GameContext restoreFrom(Path savePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savePath.toFile()))) {
            return (GameContext) ois.readObject();
        }
    }

    private void createFoldersIfNeeded() throws IOException {
        if (Files.notExists(saveFolder)) {
            Files.createDirectories(saveFolder);
        }
    }

}
