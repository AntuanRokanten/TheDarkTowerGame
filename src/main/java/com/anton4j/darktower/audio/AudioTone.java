package com.anton4j.darktower.audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * Class represents a single audio tone.
 *
 * @author ant
 */
public class AudioTone {

    /**
     * Rate of the tone.
     */
    private final float rate;

    /**
     * Frequency of the tone.
     */
    private final int frequency;

    /**
     * Length of the tone.
     */
    private final int length;

    /**
     * Volume of the tone.
     */
    private final double volume;

    public AudioTone(int frequency, int length, double vol, float rate) {
        this.frequency = frequency;
        this.length = length;
        this.volume = vol;
        this.rate = rate;
    }

    public AudioTone(int frequency, int length) {
        this(frequency, length, 1.0, 8000f);
    }

    public void play() {
        try {
            byte[] buf = new byte[1];
            AudioFormat af =
                  new AudioFormat(
                        rate,
                        8,
                        1,
                        true,
                        false);
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();
            for (int i = 0; i < length * 8; i++) {
                double angle = i / (rate / frequency) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 127.0 * volume);
                sdl.write(buf, 0, 1);
            }
            sdl.drain();
            sdl.stop();
            sdl.close();
        } catch (LineUnavailableException e) {
            System.err.println("Unable to play: " + toString());
        }
    }

    @Override
    public String toString() {
        return "AudioTone{" +
              "rate=" + rate +
              ", frequency=" + frequency +
              ", length=" + length +
              ", volume=" + volume +
              '}';
    }

}
