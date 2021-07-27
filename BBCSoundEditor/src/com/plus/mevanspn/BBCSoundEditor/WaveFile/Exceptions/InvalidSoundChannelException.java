package com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions;

public class InvalidSoundChannelException extends Throwable {
    public InvalidSoundChannelException(int channels) {
        super("Cannot create wave file with " + channels + " channels.");
    }
}
