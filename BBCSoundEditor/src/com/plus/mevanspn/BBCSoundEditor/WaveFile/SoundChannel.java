package com.plus.mevanspn.BBCSoundEditor.WaveFile;

import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidPCMSampleException;
import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidPCMSampleSizeException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

final public class SoundChannel extends LinkedList<PCMSample> {
    SoundChannel(short bitsPerSample) throws InvalidPCMSampleSizeException {
        super();
        if (bitsPerSample < 8 || bitsPerSample > 16 || bitsPerSample % 8 != 0) throw new InvalidPCMSampleSizeException(bitsPerSample);
        this.bitsPerSample = bitsPerSample;
    }

    public void AddSample(int sample) throws InvalidPCMSampleException, InvalidPCMSampleSizeException {
        add(new PCMSample(sample, bitsPerSample));
    }

    int GetChannelDataSizeInBytes() {
        return (int) Math.ceil(size() * bitsPerSample / 8.0f);
    }

    void ToStream(DataOutputStream dataOutputStream) throws IOException {
        if (dataOutputStream != null) {
            for(PCMSample PCMSample : this) {
                PCMSample.ToStream(dataOutputStream);
            }
        }
    }

    private final short bitsPerSample;
}