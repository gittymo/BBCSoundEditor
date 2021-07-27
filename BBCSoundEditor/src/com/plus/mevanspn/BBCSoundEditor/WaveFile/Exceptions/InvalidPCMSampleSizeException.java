package com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions;

public class InvalidPCMSampleSizeException extends Throwable {
    public InvalidPCMSampleSizeException(short bitsPerSample) {
        super("Cannot use sample size of " + bitsPerSample + " bits.");
    }
}
