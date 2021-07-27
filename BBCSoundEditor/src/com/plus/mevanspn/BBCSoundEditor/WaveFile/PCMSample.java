package com.plus.mevanspn.BBCSoundEditor.WaveFile;

import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidPCMSampleException;
import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidPCMSampleSizeException;

import java.io.DataOutputStream;
import java.io.IOException;

final public class PCMSample {
    PCMSample(int value, short bitsPerSample) throws InvalidPCMSampleSizeException, InvalidPCMSampleException {
        if (bitsPerSample < 8 || bitsPerSample > 16 || bitsPerSample % 8 != 0) throw new InvalidPCMSampleSizeException(bitsPerSample);
        if (bitsPerSample == 8) {
            if (value < 0 || value > 255) throw new InvalidPCMSampleException(value, bitsPerSample);
        } else if (bitsPerSample == 16) {
            if (value < -32768 || value > 32767) throw new InvalidPCMSampleException(value, bitsPerSample);
        }
        this.value = value;
        this.bitsPerSample = bitsPerSample;
    }

    void ToStream(DataOutputStream dataOutputStream) throws IOException {
        if (dataOutputStream != null) {
            if (bitsPerSample == 8) dataOutputStream.writeByte(value);
            else if (bitsPerSample == 16) dataOutputStream.write(WaveFile.getLittleEndianDataForShort((short) value));
        }
    }

    private static int getTwosComplement(int value) {
        return (value ^ 0xFFFFFFFF) + 1;
    }

    static int GetSampleValueSizeInBits(int sampleValue) {
        if (sampleValue < 0) {
            sampleValue = getTwosComplement(sampleValue);
        }
        int bits = 1, bitValue = 1, totalValue = bitValue;
        while (bits <= 16 && sampleValue > totalValue) {
            bits++;
            bitValue *= 2;
            totalValue += bitValue;
        }
        if (sampleValue > totalValue) bits = -1;
        return bits;
    }

    int value;
    short bitsPerSample;
}
