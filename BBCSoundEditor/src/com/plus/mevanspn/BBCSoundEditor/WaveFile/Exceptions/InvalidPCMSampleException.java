package com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions;

public class InvalidPCMSampleException extends Throwable {
    public InvalidPCMSampleException(int value, int bitsPerSample) {
        super("Value " + value + " is out of range for " + bitsPerSample + "bits per sample (" +
                (bitsPerSample == 8 ? "unsigned" : "signed") + ") PCM data.");
    }
}
