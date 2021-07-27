package com.plus.mevanspn.BBCSoundEditor.Envelope;

final class SustainPhase extends Phase {
    SustainPhase(int changeInAmplitudePerStep) {
        if (changeInAmplitudePerStep > 127) {
            while (changeInAmplitudePerStep > 127) changeInAmplitudePerStep -= 127;
        } else if (changeInAmplitudePerStep < -127) {
            while (changeInAmplitudePerStep < -127) changeInAmplitudePerStep += 127;
        }
        this.changeInAmplitudePerStep = changeInAmplitudePerStep;
        this.targetAmplitude = -1;
    }
}
