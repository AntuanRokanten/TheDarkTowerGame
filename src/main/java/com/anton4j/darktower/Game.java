package com.anton4j.darktower;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.audio.AudioFactory;
import com.anton4j.darktower.component.scene.YesNoScene;
import com.anton4j.darktower.component.stage.GameStage;
import com.anton4j.darktower.component.stage.impl.StartGameStage;
import com.anton4j.darktower.console.BackgroundColor;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleUtils;
import com.anton4j.darktower.console.FontColor;
import com.anton4j.darktower.console.location.GameMap;
import com.anton4j.darktower.console.location.Location;

import java.util.List;
import java.util.stream.Collectors;

import static com.anton4j.darktower.ResourceUtils.getResourceLines;

/**
 * @author anton
 */
public class Game {

    private final IntroOutro intro;
    private final IntroOutro outro;
    private final GameStage initialStage;
    private final GameContext gameContext;

    public Game() {
        this.intro = initIntro();
        this.outro = initOutro();
        this.initialStage = new StartGameStage();
        this.gameContext = initContext();
    }

    private GameContext initContext() {
        List<String> mapFileLines = getResourceLines("map");

        List<ConsoleLine> mapConsoleLines = mapFileLines
              .stream()
              .map(line -> new ConsoleLine(line, FontColor.GREEN))
              .collect(Collectors.toList());

        ConsoleLines graphicalMap = new ConsoleLines(mapConsoleLines);

        Location theDarkTower = new Location("The Dark Tower", null, 3); // 8
        Location discordia = new Location("Discordia", theDarkTower, 2); // 7
        Location thunderclap = new Location("Thunderclap", discordia, 1); // 4
        Location borderlands = new Location("Borderlands", thunderclap, 0); // 0
        GameMap gameMap = new GameMap(graphicalMap, borderlands);

        GameContext gameContext = new GameContext();
        gameContext.setGameMap(gameMap);
        gameContext.setGameStats(new GameStats());
        return gameContext;
    }

    private IntroOutro initIntro() {
        return initIntroOutro("banner", "The man in Black fled across the Desert, and the Gunslinger followed...");
    }

    private IntroOutro initOutro() {
        return initIntroOutro("end-banner", "You have reached the Dark Tower!");
    }

    private IntroOutro initIntroOutro(String bannerFileName, String text) {
        List<String> bannerFileLines = getResourceLines(bannerFileName);

        List<ConsoleLine> bannerConsoleLines = bannerFileLines
              .stream()
              .map(line -> new ConsoleLine(line, FontColor.WHITE, BackgroundColor.GRAY))
              .collect(Collectors.toList());

        ConsoleLines lines = new ConsoleLines(bannerConsoleLines);

        Audio mainAudio = AudioFactory.mainAudio();

        return new IntroOutro(lines, mainAudio, new ConsoleLine(text, FontColor.BLUE));
    }

    public void start() {
        new ConsoleLine("This game contains sound. Please make sure your speakers are not too loud. Press enter to continue", FontColor.WHITE, BackgroundColor.CYAN).println();
        ConsoleUtils.readLine();

        intro.play();

        GameStage stage = initialStage;
        while (stage != null && !stage.stageCompleted()) {
            stage.processScene(gameContext);

            if (stage.stageCompleted()) {
                stage = stage.nextStage();
            }
        }

        outro.play();
        new ConsoleLine(gameContext.getGameStats().toString(), FontColor.CYAN).println();

        YesNoScene.Response response = new YesNoScene(new ConsoleLine("Start game again?")).processScene();

        if (response.isPositive()) {
            new Game().start();
        } else {
            new ConsoleLine("See you soon!").print();
        }
    }

}
