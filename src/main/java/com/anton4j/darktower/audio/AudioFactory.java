package com.anton4j.darktower.audio;

/**
 * @author ant
 */
public class AudioFactory {

    public static Audio mainAudio() {
        return Audio.fromTones(
              new AudioTone(200, 500),
              new AudioTone(400, 500),
              new AudioTone(300, 500),
              new AudioTone(500, 500),
              new AudioTone(400, 500)
        );
    }

    public static Audio looseAudio() {
        return Audio.fromTones(
              new AudioTone(300, 500),
              new AudioTone(200, 800)
        );
    }

    public static Audio winAudio() {
        return Audio.fromTones(
              new AudioTone(300, 500),
              new AudioTone(400, 800)
        );
    }

}
