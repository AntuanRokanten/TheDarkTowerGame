package com.anton4j.darktower;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.audio.AudioFactory;
import com.anton4j.darktower.component.stage.Stage;
import com.anton4j.darktower.component.stage.impl.StartGameStage;
import com.anton4j.darktower.console.BackgroundColor;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anton
 */
public class Game {

    private final Intro intro;
    private final Stage initialStage;

    public Game() {
        List<String> bannerFileLines = ResourceUtils.getResourceLines("banner");
        List<ConsoleLine> bannerConsoleLines = bannerFileLines
              .stream()
              .map(line -> new ConsoleLine(line, FontColor.WHITE, BackgroundColor.WHITE))
              .collect(Collectors.toList());

        ConsoleLines mainBanner = new ConsoleLines(bannerConsoleLines);

        Audio mainAudio = AudioFactory.mainAudio();

        this.intro = new Intro(mainBanner, mainAudio);
        this.initialStage = new StartGameStage();

        List<String> mapFileLines = ResourceUtils.getResourceLines("map");

        List<ConsoleLine> mapConsoleLines = mapFileLines
              .stream()
              .map(line -> new ConsoleLine(line, FontColor.GREEN))
              .collect(Collectors.toList());

        ConsoleLines gameMap = new ConsoleLines(mapConsoleLines);
        GameContext.getInstance().setGameMap(gameMap);
    }

    public void start() {
        new ConsoleLine("This game contains sound. Please make sure your speakers are not too loud. Press enter to continue", FontColor.WHITE, BackgroundColor.CYAN).println();
        ConsoleUtils.readLine();

        intro.playIntro();

        Stage stage = initialStage;

        while (stage != null && !stage.stageCompleted()) {
            stage.processScene();

            if (stage.stageCompleted()) {
                stage = stage.nextStage();
            }
        }

    }

}
