package com.anton4j.darktower.audio;

/**
 * Class representing an audio as a sequence of tones.
 *
 * @author anton
 */
public class Audio {

    /**
     * Collection of tones of this audio.
     */
    private final AudioTone[] tones;

    private Audio(AudioTone[] tones) {
        this.tones = tones;
    }

    public void play() {
        for (AudioTone tone : tones) {
            tone.play();
        }
    }

    public static Audio fromTones(AudioTone... audioTones) {
        return new Audio(audioTones);
    }

}
