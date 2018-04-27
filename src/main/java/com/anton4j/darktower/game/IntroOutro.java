package com.anton4j.darktower.game;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleLines;

/**
 * @author ant
 */
public class IntroOutro {

    private final ConsoleLines banner;
    private final Audio audio;
    private final ConsoleLine text;

    public IntroOutro(ConsoleLines banner, Audio audio, ConsoleLine text) {
        this.banner = banner;
        this.audio = audio;
        this.text = text;
    }

    public void play() {
        new Thread(audio::play).start();
        banner.printSequentially();
        text.println();
    }

}
