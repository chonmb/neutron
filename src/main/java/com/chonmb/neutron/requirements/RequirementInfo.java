package com.chonmb.neutron.requirements;

import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;

import java.util.List;

public interface RequirementInfo extends Requirement {
    String getName();

    String getExecutorName();

    List<ValidateStimulationInterceptor> getInterceptors();
}
