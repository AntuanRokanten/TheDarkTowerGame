package com.anton4j.darktower;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.audio.AudioFactory;
import com.anton4j.darktower.component.stage.Stage;
import com.anton4j.darktower.component.stage.impl.StartGameStage;
import com.anton4j.darktower.console.BackgroundColor;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.console.location.GameMap;
import com.anton4j.darktower.console.location.Location;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anton
 */
public class Game {

    private final GameIntro intro;
    private final Stage initialStage;
    private final GameContext gameContext;

    public Game() {
        this.gameContext = initContext();
        this.intro = initIntro();

        this.initialStage = new StartGameStage(gameContext);
    }

    private GameIntro initIntro() {
        List<String> bannerFileLines = ResourceUtils.getResourceLines("banner");
        List<ConsoleLine> bannerConsoleLines = bannerFileLines
              .stream()
              .map(line -> new ConsoleLine(line, FontColor.WHITE, BackgroundColor.WHITE))
              .collect(Collectors.toList());

        ConsoleLines mainBanner = new ConsoleLines(bannerConsoleLines);

        Audio mainAudio = AudioFactory.mainAudio();

        return new GameIntro(mainBanner, mainAudio);
    }

    private GameContext initContext() {
        List<String> mapFileLines = ResourceUtils.getResourceLines("map");

        List<ConsoleLine> mapConsoleLines = mapFileLines
              .stream()
              .map(line -> new ConsoleLine(line, FontColor.GREEN))
              .collect(Collectors.toList());

        ConsoleLines graphicalMap = new ConsoleLines(mapConsoleLines);

        Location theDarkTower = new Location("The Dark Tower", null, 9);
        Location discordia = new Location("Discordia", theDarkTower, 7);
        Location thunderclap = new Location("Thunderclap", discordia, 4);
        Location borderlands = new Location("Borderlands", thunderclap, 0);
        GameMap gameMap = new GameMap(graphicalMap, borderlands);

        return new GameContext(gameMap);
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
