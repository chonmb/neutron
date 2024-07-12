package com.chonmb.neutron.bus;

import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface EventGraph {
    Set<RequirementInfo> getNext(RequirementInfo current);

    Set<RequirementInfo> getPreview(RequirementInfo current);

    RequirementInfo getStart();

    Collection<ValidateStimulationInterceptor> getInterceptors();
}
