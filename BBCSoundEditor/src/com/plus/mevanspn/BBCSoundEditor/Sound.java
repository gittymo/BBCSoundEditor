package com.plus.mevanspn.BBCSoundEditor;

final public class Sound {
    public Sound() {
        this(0, 0, 0, 0);
    }

    public Sound(int channel, int amplitude, int pitch, int duration) {
        SetChannel(channel);
        SetAmplitude(amplitude);
        SetPitch(pitch);
        SetDuration(duration);
        System.out.println("Sound defition: channel = " + this.channel + ", amplitude = " + this.amplitude + ", " +
                "pitch = " + this.pitch + ", duration = " + this.duration);
    }

    public void SetChannel(int channel) {
        this.channel = (byte) (channel >= 0 ? channel % 4 : (-channel % 4));
    }

    public byte GetChannel() { return this.channel; }

    public void SetAmplitude(int amplitude) {
        this.amplitude = (byte) (amplitude <= 0 ? -(-amplitude % 16) : -(amplitude % 16));
    }

    public byte GetAmplitude() { return this.amplitude; }

    public void SetPitch(int pitch) {
        this.pitch = (short) (pitch >= 0 ? pitch % 256 : -pitch % 256);
    }

    public short GetPitch() { return this.pitch; }

    public void SetDuration(int duration) {
        this.duration = (short) (duration >=0 ? duration % 256 : -duration % 256);
    }

    public short GetDuration() { return this.duration; }

    public short GetDurationInCentiSeconds() { return (short) (this.duration * 20); }

    public static void main(String[] args) {
        Sound s = new Sound(0, -10, 48, 5);
    }

    private byte channel, amplitude;
    private short pitch, duration;
}
