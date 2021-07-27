package com.plus.mevanspn.BBCSoundEditor.Keyboard;

public class InvalidOctaveNumberException extends Throwable {
    public InvalidOctaveNumberException(int number) {
        super(number + " is not a valid octave number, must be 1 to 6.");
    }
}
