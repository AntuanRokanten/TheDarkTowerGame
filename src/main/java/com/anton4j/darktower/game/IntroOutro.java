package com.anton4j.darktower.game;

import com.anton4j.darktower.audio.Audio;
import com.anton4j.darktower.console.ConsoleLine;
import com.anton4j.darktower.console.ConsoleLines;

/**
 * Game intro/outro.
 *
 * @author ant
 */
public class IntroOutro {

    /**
     * Banner to be shown.
     */
    private final ConsoleLines banner;

    /**
     * Audio to play.
     */
    private final Audio audio;

    /**
     * Text displayed in intro/outro.
     */
    private final ConsoleLine text;

    public IntroOutro(ConsoleLines banner, Audio audio, ConsoleLine text) {
        this.banner = banner;
        this.audio = audio;
        this.text = text;
    }

    /**
     * Plays this intro/outro.
     */
    public void play() {
        new Thread(audio::play).start();
        banner.printSequentially();
        text.println();
    }

}
