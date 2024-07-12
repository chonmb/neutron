package com.chonmb.neutron.requirements.info;

import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;

import java.util.Collections;
import java.util.List;

public class SimpleStartRequirementInfo extends AbstractRequirementInfo {
    public SimpleStartRequirementInfo() {
        super("start");
    }

    @Override
    public String getExecutorName() {
        return "start";
    }

    @Override
    public List<ValidateStimulationInterceptor> getInterceptors() {
        return Collections.emptyList();
    }
}
