package com.anton4j.darktower;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.audio.AudioTone;
import com.anton4j.darktower.component.stage.Stage;
import com.anton4j.darktower.component.stage.impl.StartGameStage;
import com.anton4j.darktower.console.BackgroundColor;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.FontColor;

import java.util.ArrayList;
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
              .map(line -> new ConsoleLine(line, FontColor.BLACK, BackgroundColor.WHITE))
              .collect(Collectors.toList());

        Banner mainBanner = new Banner(bannerConsoleLines);

        List<AudioTone> tones = new ArrayList<>();
        tones.add(new AudioTone(200, 500));
        tones.add(new AudioTone(400, 500));
        tones.add(new AudioTone(300, 500));
        tones.add(new AudioTone(500, 500));
        tones.add(new AudioTone(400, 500));
        Audio mainAudio = new Audio(tones);

        this.intro = new Intro(mainBanner, mainAudio);
        this.initialStage = new StartGameStage();
    }

    public void start() {
        intro.playIntro();


        Stage currentStage = initialStage;
        currentStage.getScene().processScene();

//        while (!currentStage.stageCompleted()) {
//            for (Option option : currentStage.options()) {
//                option.consoleLine().println();
//            }
//
//            String selectedIndex = ConsoleUtils.readLine();
//
////            currentStage.handleSelection(Integer.valueOf(selectedIndex));
//        }

    }

}
