package com.anton4j.darktower;

import com.anton4j.darktower.audio.Audio;

/**
 * @author anton
 */
public class Intro {

    private final Banner mainBanner;
    private final Audio mainAudio;

    public Intro(Banner mainBanner, Audio mainAudio) {
        this.mainBanner = mainBanner;
        this.mainAudio = mainAudio;
    }

    public void playIntro() {
//        new Thread(mainAudio::play).start();
        mainBanner.displaySequentially();
    }

}
