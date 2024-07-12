package com.chonmb.neutron.requirements.info;

import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;
import com.chonmb.neutron.trigger.WaitAllStimulationInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleEndRequirementInfo extends AbstractRequirementInfo {
    private final List<ValidateStimulationInterceptor> interceptors=new ArrayList<>();
    public SimpleEndRequirementInfo() {
        super("end");
    }

    @Override
    public String getExecutorName() {
        return "end";
    }

    @Override
    public List<ValidateStimulationInterceptor> getInterceptors() {
        return interceptors;
    }

    public void waitAllReady(RequirementInfo... requirementInfos) {
        this.interceptors.add(new WaitAllStimulationInterceptor(this, Arrays.stream(requirementInfos).collect(Collectors.toSet())));
    }
}
