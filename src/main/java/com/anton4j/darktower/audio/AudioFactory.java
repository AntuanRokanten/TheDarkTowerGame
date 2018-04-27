package com.anton4j.darktower.audio;

/**
 * Factory for audios.
 *
 * @author ant
 */
public class AudioFactory {

    /**
     * @return main game audio.
     */
    public static Audio mainAudio() {
        return Audio.fromTones(
              new AudioTone(200, 500),
              new AudioTone(400, 500),
              new AudioTone(300, 500),
              new AudioTone(500, 500),
              new AudioTone(400, 500)
        );
    }

    /**
     * @return audio to be played when character loses.
     */
    public static Audio looseAudio() {
        return Audio.fromTones(
              new AudioTone(300, 500),
              new AudioTone(200, 800)
        );
    }

    /**
     * @return audio to be played when character wins..
     */
    public static Audio winAudio() {
        return Audio.fromTones(
              new AudioTone(300, 500),
              new AudioTone(400, 800)
        );
    }

}
