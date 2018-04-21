package com.anton4j.darktower.audio;

import java.util.List;

/**
 * Class representing an audio as a sequence of tones.
 *
 * @author anton
 */
public class Audio {

    /**
     * Collection of tones of this audio.
     */
    private final List<AudioTone> tones;

    public Audio(List<AudioTone> tones) {
        this.tones = tones;
    }

    public void play() {
        for (AudioTone tone : tones) {
            tone.play();
        }
    }

}
