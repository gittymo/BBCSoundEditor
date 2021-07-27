package com.plus.mevanspn.BBCSoundEditor.TimeSignature;

public class InvalidTimeSignatureException extends Throwable {
    public InvalidTimeSignatureException(int beats, int barBeats) {
        super("Given time signature " + beats + "/" + barBeats + " is invalid.");
    }
}
