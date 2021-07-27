package com.plus.mevanspn.BBCSoundEditor.WaveFile;

import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidPCMSampleSizeException;
import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidSoundChannelException;

import java.io.DataOutputStream;
import java.io.IOException;

final public class FormatChunk {
    FormatChunk(int channels, int sampleRate, int bitsPerSample) throws InvalidSoundChannelException, InvalidPCMSampleSizeException {
        if (channels < 1 || channels > 2) throw new InvalidSoundChannelException(channels);
        if (bitsPerSample < 8 || bitsPerSample > 16 || bitsPerSample % 8 != 0) {
            throw new InvalidPCMSampleSizeException((short) bitsPerSample);
        }
        this.channels = (short) channels;
        this.sampleRate = sampleRate;
        this.bitsPerSample = (short) bitsPerSample;
    }

    void ToStream(DataOutputStream dataOutputStream) throws IOException {
        if (dataOutputStream != null) {
            dataOutputStream.write("fmt ".getBytes());  // Format chunk header.
            dataOutputStream.write(WaveFile.getLittleEndianDataForInt(16));           // Subchunk size (size of the subchunk after this = 16 bytes)
            dataOutputStream.write(WaveFile.getLittleEndianDataForShort((short) 1));          // Audio format (PCM = 1)
            dataOutputStream.write(WaveFile.getLittleEndianDataForShort(channels));      // Number of channels, 1 = mono, 2 = stereo, etc.
            dataOutputStream.write(WaveFile.getLittleEndianDataForInt(sampleRate));      // 8000, 44100, etc.
            dataOutputStream.write(WaveFile.getLittleEndianDataForInt((sampleRate * channels * bitsPerSample) / 8)); // Byte rate.
            dataOutputStream.write(WaveFile.getLittleEndianDataForShort((short)((channels * bitsPerSample) / 8)));  // Block align.
            dataOutputStream.write(WaveFile.getLittleEndianDataForShort(bitsPerSample));   // 8 = 8 bits, 16 = 16 bits, etc.
        }
    }

    int GetSize() { return 24; }

    short channels;
    int sampleRate;
    short bitsPerSample;
}