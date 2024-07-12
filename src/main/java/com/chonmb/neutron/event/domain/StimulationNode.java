package com.chonmb.neutron.event.domain;

import com.chonmb.neutron.event.Stimulation;
import com.chonmb.neutron.requirements.RequirementInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StimulationNode {
    private Stimulation stimulation;
    private List<StimulationNode> next = new ArrayList<>();

    public StimulationNode(Stimulation stimulation) {
        this.stimulation = stimulation;
    }

    public StimulationNode() {
    }

    public Stimulation getStimulation() {
        return stimulation;
    }

    public void setStimulation(Stimulation stimulation) {
        this.stimulation = stimulation;
    }

    public List<StimulationNode> getNext() {
        return next;
    }

    public void setNext(List<StimulationNode> next) {
        this.next = next;
    }

    public void addNext(StimulationNode next) {
        this.next.add(next);
    }

    @Override
    public String toString() {
        return (Objects.isNull(stimulation) ? "root" : Stream.of(stimulation.from(), stimulation.to()).filter(Objects::nonNull).map(RequirementInfo::getName).collect(Collectors.joining(" -> "))) +
                (next.isEmpty() ? "" : "\n" + next.stream().map(Objects::toString).collect(Collectors.joining("\n")));
    }
}
