package com.anton4j.darktower;

import com.anton4j.darktower.audio.Audio;

/**
 * @author anton
 */
public class Intro {

    private final ConsoleLines mainBanner;
    private final Audio mainAudio;

    public Intro(ConsoleLines mainBanner, Audio mainAudio) {
        this.mainBanner = mainBanner;
        this.mainAudio = mainAudio;
    }

    public void playIntro() {
//        new Thread(mainAudio::play).start();
        mainBanner.printSequentially();
    }

}
