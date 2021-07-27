package com.plus.mevanspn.BBCSoundEditor.Keyboard;

import com.plus.mevanspn.BBCSoundEditor.Sound;

import java.util.HashMap;

final public class Keyboard {
    public Keyboard(final int channel) throws InvalidOctaveNumberException {
        this(channel, -15);
    }

    public Keyboard(final int channel, final int amplitude) throws InvalidOctaveNumberException {
        SetChannel(channel);
        SetAmplitude(amplitude);
        this.octaves = new HashMap<>();
        for (int i = 1; i <= 6; i++) {
            this.octaves.put(i, new Octave(i));
        }
    }

    public void SetChannel(final int channel) {
        this.channel = channel >= 0 ? channel % 4 : (-channel % 4);
    }

    public int GetChannel() { return this.channel; }

    public void SetAmplitude(final int amplitude) {
        this.amplitude = amplitude <= 0 ? -(-amplitude % 16) : -(amplitude % 16);
    }

    public int GetAmplitude() { return this.amplitude; }

    public Sound GenerateSound(String key, int octave, int duration) {
        if (octave < 1 || octave > 6) return null;
        if (!isValidKey(key)) return null;
        return new Sound(this.channel, this.amplitude, octaves.get(octave).keys.get(key), duration);
    }

    private boolean isValidKey(String key) {
        boolean found = false;
        if (key != null) {
            for (String keyDef : Octave.keyDefs) {
                if (keyDef.equalsIgnoreCase(key)) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
    private int channel, amplitude;
    private final HashMap<Integer, Octave> octaves;
}
