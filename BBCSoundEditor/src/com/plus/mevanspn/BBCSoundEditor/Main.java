package com.plus.mevanspn.BBCSoundEditor;

import com.plus.mevanspn.BBCSoundEditor.WaveFile.*;
import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidPCMSampleException;
import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidPCMSampleSizeException;
import com.plus.mevanspn.BBCSoundEditor.WaveFile.Exceptions.InvalidSoundChannelException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            WaveFile waveFile = new WaveFile(1, 8000, 16);
            SoundChannel soundChannel = waveFile.GetChannel(0);
            final double sineLength = 0.008, volume = 50;
            final int sineWaveSamplesLength = (int) (sineLength * waveFile.GetSampleRate());
            final int sampleLengthInSeconds = 5, totalSamples = sampleLengthInSeconds * waveFile.GetSampleRate();
            double waveInc = Math.PI * 2 / sineWaveSamplesLength;
            int i = 0;
            while (i < totalSamples) {
                double waveValue = 0;
                for (int j = 0; i < totalSamples && j < sineWaveSamplesLength; j++) {
                    int sampleValue = (int) (32767 * (volume / 100.0) * Math.sin(waveValue));
                    soundChannel.AddSample(sampleValue);
                    waveValue += waveInc;
                    i++;
                }
            }
            waveFile.ToFile("test.wav");
        } catch (InvalidPCMSampleSizeException issex) {
            issex.printStackTrace();
        } catch (InvalidSoundChannelException iccex) {
            iccex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        } catch (InvalidPCMSampleException isex) {
            isex.printStackTrace();
        }
    }
}
