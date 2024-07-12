package com.chonmb.neutron.repository.domain;

import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;

import java.util.List;

public interface EventDeliverMap {
    List<RequirementInfo> getDeliverNodes();
    List<DeliverEdge> getDeliverEdge();
    List<ValidateStimulationInterceptor> getInterceptors();
}
