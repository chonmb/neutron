package com.chonmb.neutron.bus;

import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.requirements.info.SimpleStartRequirementInfo;
import com.chonmb.neutron.requirements.instance.SimpleStartRequirement;
import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;

import java.util.*;

public class EventGraphCacheMap implements EventGraph {
    private Map<String, Set<RequirementInfo>> nextMap=new HashMap<>();
    private Map<String, Set<RequirementInfo>> previewMap=new HashMap<>();
    private RequirementInfo start;

    public EventGraphCacheMap() {
        start = new SimpleStartRequirementInfo();
    }

    @Override
    public Set<RequirementInfo> getNext(RequirementInfo current) {
        return nextMap.get(current.getName());
    }

    @Override
    public Set<RequirementInfo> getPreview(RequirementInfo current) {
        return previewMap.get(current.getName());
    }

    @Override
    public RequirementInfo getStart() {
        return start;
    }

    @Override
    public Collection<ValidateStimulationInterceptor> getInterceptors() {
        return Collections.emptySet();
    }
}
