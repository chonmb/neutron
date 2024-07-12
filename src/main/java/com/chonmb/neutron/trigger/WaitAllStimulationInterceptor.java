package com.chonmb.neutron.trigger;

import com.chonmb.neutron.event.Stimulation;
import com.chonmb.neutron.requirements.RequirementInfo;

import java.util.HashSet;
import java.util.Set;

public class WaitAllStimulationInterceptor implements ValidateStimulationInterceptor {
    private final RequirementInfo to;
    private final Set<RequirementInfo> froms;
    private final Set<Stimulation> readyCount = new HashSet<>();

    public WaitAllStimulationInterceptor(RequirementInfo to, Set<RequirementInfo> froms) {
        this.to = to;
        this.froms = froms;
    }

    @Override
    public boolean validate(Stimulation stimulation) {
        if (stimulation.to().equals(to)) {
            if (froms.contains(stimulation.from())) {
                readyCount.add(stimulation);
                if (readyCount.size() != froms.size()){
                    System.out.println("wait interceptor actived by stimulation: "+stimulation.content());
                }
                return readyCount.size() == froms.size();
            }
        }
        return true;
    }

}
