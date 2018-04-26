package com.anton4j.darktower;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.console.ConsoleLine;

/**
 * @author anton
 */
public class IntroOutro {

    private final ConsoleLines banner;
    private final Audio mainAudio;
    private final ConsoleLine text;

    public IntroOutro(ConsoleLines banner, Audio audio, ConsoleLine text) {
        this.banner = banner;
        this.mainAudio = audio;
        this.text = text;
    }

    public void play() {
//        new Thread(mainAudio::play).start();
        banner.printSequentially();
        text.println();
    }

}
