package com.chonmb.neutron.factory.impl;

import com.chonmb.neutron.EventEngineContext;
import com.chonmb.neutron.beans.EventBeansContext;
import com.chonmb.neutron.factory.Factory;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.requirements.RequirementInstance;

import java.util.Map;
import java.util.stream.Collectors;

public class RequirementInstanceFactory implements Factory, EventEngineContext {
    private Map<String, RequirementInstance> requirementBeans;
    private EventBeansContext eventBeansContext;

    public RequirementInstance getInstance(RequirementInfo requirementInfo) {
        return requirementBeans.get(requirementInfo.getExecutorName());
    }

    @Override
    public void setEventBeansContext(EventBeansContext eventBeansContext) {
        this.eventBeansContext = eventBeansContext;
        this.requirementBeans = eventBeansContext.getBeansImplement(RequirementInstance.class).stream().collect(Collectors.toMap(RequirementInstance::name, instance -> instance));
    }
}
