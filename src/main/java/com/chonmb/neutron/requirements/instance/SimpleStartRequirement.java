package com.chonmb.neutron.requirements.instance;

import com.chonmb.neutron.event.Stimulation;

public class SimpleStartRequirement extends AbstractRequirementInstance {

    public SimpleStartRequirement() {
        super();
    }

    @Override
    public void apply(Stimulation stimulation) {

    }

    @Override
    public String name() {
        return "start";
    }
}
