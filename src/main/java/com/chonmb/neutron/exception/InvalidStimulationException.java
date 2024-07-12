package com.chonmb.neutron.exception;

import com.chonmb.neutron.event.Stimulation;

public class InvalidStimulationException extends RuntimeException {
    public InvalidStimulationException(Stimulation stimulation) {
        System.out.println(stimulation.from().getName());
        System.out.println(stimulation.to().getName());
    }
}
