package com.chonmb.neutron.requirements.instance;

import com.chonmb.neutron.event.Stimulation;


public class EchoRequirement extends AbstractRequirementInstance {

    @Override
    public void apply(Stimulation stimulation) {
        System.out.println("echo executor execute stimulation, print content: "+stimulation.content());
    }

    @Override
    public String name() {
        return "echo";
    }
}
