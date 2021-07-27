package com.plus.mevanspn.BBCSoundEditor.Envelope;

final class DecayPhase extends Phase {
    DecayPhase(int changeInAmplitudePerStep, int targetAmplitude) throws InvalidPhaseParameterException {
        if (changeInAmplitudePerStep > 0 || changeInAmplitudePerStep < -127 || targetAmplitude < 0 || targetAmplitude > 126) {
            throw new InvalidPhaseParameterException(changeInAmplitudePerStep, targetAmplitude, true);
        }

        this.changeInAmplitudePerStep = changeInAmplitudePerStep;
        this.targetAmplitude = targetAmplitude;
    }
}
