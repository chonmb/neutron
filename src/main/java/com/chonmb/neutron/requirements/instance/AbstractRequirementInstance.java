package com.chonmb.neutron.requirements.instance;

import com.chonmb.neutron.event.Stimulation;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.requirements.RequirementInstance;
import com.chonmb.neutron.trigger.ActiveInterceptor;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRequirementInstance implements RequirementInstance {
    private final List<ActiveInterceptor> activeInterceptors=new ArrayList<>();
    @Override
    public void handle(Stimulation stimulation) {
        if (activeInterceptors.isEmpty()||activeInterceptors.stream().allMatch(activeInterceptor -> activeInterceptor.accept(stimulation))){
            this.apply(stimulation);
            stimulation.finish();
        }
    }

    public abstract void apply(Stimulation stimulation);

    @Override
    public boolean equals(Object obj) {
        return ((RequirementInfo)obj).getName().equals(this.name());
    }

    public List<ActiveInterceptor> getActiveInterceptors() {
        return activeInterceptors;
    }
}
