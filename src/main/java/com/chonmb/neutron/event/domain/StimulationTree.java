package com.chonmb.neutron.event.domain;

import com.chonmb.neutron.event.Stimulation;
import com.chonmb.neutron.requirements.RequirementInfo;

import java.util.HashMap;
import java.util.Map;

public class StimulationTree {
    private StimulationNode root = new StimulationNode();
    private final Map<RequirementInfo, StimulationNode> leaves = new HashMap<>();

    public void add(Stimulation stimulation) {
        StimulationNode node = new StimulationNode(stimulation);
        if (leaves.containsKey(stimulation.from())) {
            leaves.get(stimulation.from()).addNext(node);
        } else {
            root.addNext(node);
        }
        leaves.put(stimulation.to(), node);
    }

    public StimulationNode getRoot() {
        return root;
    }

    public void setRoot(StimulationNode root) {
        this.root = root;
    }

    public void printStimulationPath() {
        System.out.println("path : " + root);
    }
}
