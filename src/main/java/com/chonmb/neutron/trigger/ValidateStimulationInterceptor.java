package com.chonmb.neutron.trigger;

import com.chonmb.neutron.event.Stimulation;

public interface ValidateStimulationInterceptor extends Interceptor{
    boolean validate(Stimulation stimulation);
}
