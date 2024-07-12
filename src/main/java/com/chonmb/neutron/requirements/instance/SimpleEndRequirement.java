package com.chonmb.neutron.requirements.instance;

import com.chonmb.neutron.event.Stimulation;

public class SimpleEndRequirement extends AbstractRequirementInstance {

    public SimpleEndRequirement() {
        super();
    }

    @Override
    public void apply(Stimulation stimulation) {
        System.out.println("event end");
        stimulation.getEvent().finish();
    }

    @Override
    public String name() {
        return "end";
    }
}
