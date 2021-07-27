package com.plus.mevanspn.BBCSoundEditor.Envelope;

public class InvalidPhaseParameterException extends Throwable {
    public InvalidPhaseParameterException(int changeInAmplitudePerStep, int targetAmplitude, boolean attackPhase) {
        super(targetAmplitude < 0 || targetAmplitude > 126 ? ("Target amplitude of " + targetAmplitude + " not allowed" +
                (attackPhase ? changeInAmplitudePerStep < 0 ? " and amplitude change (" + changeInAmplitudePerStep + ")" +
                        " cannot be negative in attack phase." : "." : changeInAmplitudePerStep > 0 ?
                        " and amplitude change (" + changeInAmplitudePerStep + ") cannot be positive in decay phase." : ".")) :
                attackPhase ? (changeInAmplitudePerStep < 0 ? "Amplitude change (" + changeInAmplitudePerStep + ")" +
                        " cannot be negative in attach phase." : "") : (changeInAmplitudePerStep > 0 ? "Amplitude change (" +
                        changeInAmplitudePerStep + ") cannot be positive in decay phase." : ""));
    }
}
