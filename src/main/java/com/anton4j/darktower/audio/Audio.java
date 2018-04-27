package com.anton4j.darktower.audio;

/**
 * Class representing an audio as a sequence of tones.
 *
 * @author ant
 */
public class Audio {

    /**
     * Collection of tones of this audio.
     */
    private final AudioTone[] tones;

    private Audio(AudioTone[] tones) {
        this.tones = tones;
    }

    /**
     * Creates {@link Audio} from the array of {@link AudioTone}
     *
     * @param audioTones tones stat will constitute the audio.
     * @return composed audio.
     */
    public static Audio fromTones(AudioTone... audioTones) {
        return new Audio(audioTones);
    }

    public void play() {
        for (AudioTone tone : tones) {
            tone.play();
        }
    }

}
