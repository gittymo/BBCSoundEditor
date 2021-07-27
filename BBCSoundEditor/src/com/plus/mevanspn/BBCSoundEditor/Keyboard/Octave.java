package com.plus.mevanspn.BBCSoundEditor.Keyboard;

import java.util.HashMap;

final public class Octave {
    public Octave(final int octaveNumber) throws InvalidOctaveNumberException {
        if (octaveNumber >=1 && octaveNumber <= 6) {
            keys = new HashMap<>();
            int pitch = (octaveNumber - 1) * 48;
            for (String keyDef : keyDefs) {
                keys.put(keyDef, pitch);
                pitch += 4;
                if (pitch == 256) break;
            }
        } else throw new InvalidOctaveNumberException(octaveNumber);
    }

    HashMap<String, Integer> keys;
    static String[] keyDefs = new String[] {
            "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#"
    };
}
