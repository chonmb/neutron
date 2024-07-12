package com.chonmb.neutron.requirements.info;


import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;
import com.chonmb.neutron.trigger.WaitAllStimulationInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleRequirementInfo extends AbstractRequirementInfo {
    private final String executorName;
    private final List<ValidateStimulationInterceptor> interceptors;

    public SimpleRequirementInfo(String name, String executorName) {
        this(name, executorName, new ArrayList<>());
    }

    public SimpleRequirementInfo(String name, String executorName, List<ValidateStimulationInterceptor> interceptors) {
        super(name);
        this.executorName = executorName;
        this.interceptors = interceptors;
    }

    @Override
    public String getExecutorName() {
        return executorName;
    }

    @Override
    public List<ValidateStimulationInterceptor> getInterceptors() {
        return interceptors;
    }

    public void waitAllReady(RequirementInfo... requirementInfos) {
        this.interceptors.add(new WaitAllStimulationInterceptor(this, Arrays.stream(requirementInfos).collect(Collectors.toSet())));
    }

}
