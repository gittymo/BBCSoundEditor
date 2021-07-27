package com.plus.mevanspn.BBCSoundEditor.TimeSignature;

final public class TimeSignature {
    public TimeSignature(int notes, int barBeats) {
        this.notes = notes == 0 ? 4 : notes < 0 ? Math.min(-notes, 8) : Math.min(notes, 8);
        this.barBeats = barBeats == 0 ? 4 : barBeats < 0 ? Math.min(-barBeats, 8) : Math.min(barBeats, 8);
    }

    private int notes, barBeats;

    public static TimeSignature ThreeFour = new TimeSignature(3,4);
}
