package com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions;

public class PCMSampleSizeMismatchException extends Throwable {
    public PCMSampleSizeMismatchException(short sampleBitSize, short channelBitSize) {
        super("Cannot add sample using bit size " + sampleBitSize + " to " + channelBitSize + " bit channel.");
    }
}
