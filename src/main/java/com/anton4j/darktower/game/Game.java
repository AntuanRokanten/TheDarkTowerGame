package com.anton4j.darktower.game;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.audio.AudioFactory;
import com.anton4j.darktower.console.*;
import com.anton4j.darktower.game.component.scene.YesNoScene;
import com.anton4j.darktower.game.component.stage.GameStage;
import com.anton4j.darktower.game.component.stage.impl.StartGameStage;
import com.anton4j.darktower.game.location.GameMap;
import com.anton4j.darktower.game.location.Location;

import java.util.List;
import java.util.stream.Collectors;

import static com.anton4j.darktower.util.ResourceUtils.getResourceLines;

/**
 * Main game class.
 *
 * @author ant
 */
public class Game {

    /**
     * Game intro.
     */
    private final IntroOutro intro;

    /**
     * Game outro.
     */
    private final IntroOutro outro;

    /**
     * First stage of the game.
     */
    private final GameStage initialStage;

    /**
     * Context of the game.
     */
    private final GameContext gameContext;

    public Game(IntroOutro intro, IntroOutro outro, GameStage initialStage, GameContext gameContext) {
        this.intro = intro;
        this.outro = outro;
        this.initialStage = initialStage;
        this.gameContext = gameContext;
    }

    public Game() {
        this.intro = initIntro();
        this.outro = initOutro();
        this.initialStage = new StartGameStage();
        this.gameContext = initContext();
    }

    /**
     * Initiates context of the game.
     * <p>
     * return initiated game context.
     */
    private GameContext initContext() {
        List<String> mapFileLines = getResourceLines("map");

        List<ConsoleLine> mapConsoleLines = mapFileLines
              .stream()
              .map(line -> new ConsoleLine(line, FontColor.GREEN))
              .collect(Collectors.toList());

        ConsoleLines graphicalMap = new ConsoleLines(mapConsoleLines);

        Location theDarkTower = new Location("The Dark Tower", null, 7);
        Location discordia = new Location("Discordia", theDarkTower, 5);
        Location thunderclap = new Location("Thunderclap", discordia, 3);
        Location borderlands = new Location("Borderlands", thunderclap, 1);
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

    /**
     * Starts game.
     */
    public void start() {
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
