package com.plus.mevanspn.BBCSoundEditor.WaveFile;

import java.io.DataOutputStream;
import java.io.IOException;

final public class RIFFHeader {
    RIFFHeader(WaveFile waveFile) {
        this.waveFile = waveFile;
    }

    void ToStream(DataOutputStream dataOutputStream) throws IOException {
        if (dataOutputStream != null) {
            dataOutputStream.write("RIFF".getBytes());
            dataOutputStream.write(WaveFile.getLittleEndianDataForInt(4 + waveFile.GetFormatChunk().GetSize() + waveFile.GetDataChunk().GetSize()));
            dataOutputStream.write("WAVE".getBytes());
        }
    }

    private final WaveFile waveFile;
}
