package com.plus.mevanspn.BBCSoundEditor.WaveFile;

import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidPCMSampleSizeException;
import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidSoundChannelException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

final public class DataChunk {
    DataChunk(FormatChunk formatChunk) throws InvalidPCMSampleSizeException {
        this.formatChunk = formatChunk;
        soundChannels = new LinkedList<>();
        for (int i = 0; i < formatChunk.channels; i++) CreateChannel();
    }

    SoundChannel CreateChannel() throws InvalidPCMSampleSizeException {
        SoundChannel newSoundChannel = null;
        if (soundChannels.size() < formatChunk.channels) {
            newSoundChannel = new SoundChannel(formatChunk.bitsPerSample);
            soundChannels.add(newSoundChannel);
        }
        return newSoundChannel;
    }

    SoundChannel GetChannel(int channel) throws InvalidSoundChannelException {
        if (channel < 0 || channel > formatChunk.channels) throw new InvalidSoundChannelException(channel);
        return soundChannels.get(channel);
    }

    void ToStream(DataOutputStream dataOutputStream) throws IOException {
        if (dataOutputStream != null) {
            dataOutputStream.write("data".getBytes());
            dataOutputStream.write(WaveFile.getLittleEndianDataForInt(GetSize() - 8));
            for (SoundChannel SoundChannel : soundChannels) {
                SoundChannel.ToStream(dataOutputStream);
            }
        }
    }

    int GetSize() {
        int totalChannelsSize = 0;
        for (SoundChannel SoundChannel : soundChannels) {
            totalChannelsSize += SoundChannel.GetChannelDataSizeInBytes();
        }
        return 8 + totalChannelsSize;
    }

    private LinkedList<SoundChannel> soundChannels;
    private FormatChunk formatChunk;
}