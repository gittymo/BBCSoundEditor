package com.plus.mevanspn.BBCSoundEditor.WaveFile;

import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidPCMSampleSizeException;
import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidSoundChannelException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final public class WaveFile {
    public WaveFile(int channels, int sampleRate, int bitsPerSample) throws InvalidSoundChannelException, InvalidPCMSampleSizeException {
        this.riffHeader = new RIFFHeader(this);
        this.formatChunk = new FormatChunk(channels, sampleRate, bitsPerSample);
        this.dataChunk = new DataChunk(this.formatChunk);
    }

    public void ToFile(String filename) throws IOException {
        if (filename != null) {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(filename));
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            riffHeader.ToStream(dataOutputStream);
            formatChunk.ToStream(dataOutputStream);
            dataChunk.ToStream(dataOutputStream);
            dataOutputStream.flush();
            dataOutputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }

    public SoundChannel GetChannel(int channel) throws InvalidSoundChannelException {
        return dataChunk.GetChannel(channel);
    }

    public int GetChannelCount() { return formatChunk.channels; }

    public int GetSampleRate() { return formatChunk.sampleRate; }

    public int GetBitsPerSample() { return formatChunk.bitsPerSample; }

    static byte[] getLittleEndianDataForInt(int value) {
        return ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }

    static byte[] getLittleEndianDataForShort(short value) {
        return ByteBuffer.allocate(Short.BYTES).order(ByteOrder.LITTLE_ENDIAN).putShort(value).array();
    }

    FormatChunk GetFormatChunk() { return formatChunk; }

    DataChunk GetDataChunk() { return dataChunk; }



    private RIFFHeader riffHeader;
    private FormatChunk formatChunk;
    private DataChunk dataChunk;
}
