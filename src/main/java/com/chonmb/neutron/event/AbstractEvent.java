package com.chonmb.neutron.event;

import com.chonmb.neutron.bus.EventGraph;
import com.chonmb.neutron.event.domain.EventStatusEnum;
import com.chonmb.neutron.event.domain.StimulationTree;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class AbstractEvent implements Event {
    private EventStatusEnum status;
    private StimulationTree stimulationTree;
    private String topic;
    private final EventGraph template;
    private final Collection<ValidateStimulationInterceptor> stimulationInterceptors;
    private final AtomicInteger aliveTime = new AtomicInteger(60);

    public AbstractEvent(EventStatusEnum status, StimulationTree stimulationTree, String topic, EventGraph template) {
        this.status = status;
        this.stimulationTree = stimulationTree;
        this.topic = topic;
        this.template = template;
        this.stimulationInterceptors = template.getInterceptors();
    }

    public EventStatusEnum getStatus() {
        return status;
    }

    public void setStatus(EventStatusEnum status) {
        this.status = status;
    }

    public StimulationTree getStimulationTree() {
        return stimulationTree;
    }

    public void setStimulationTree(StimulationTree stimulationTree) {
        this.stimulationTree = stimulationTree;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String topic() {
        return getTopic();
    }

    @Override
    public List<Stimulation> apply() {
        RequirementInfo current = template.getStart();
        return buildStimulationByRequirement(current);
    }

    protected abstract Stimulation buildStimulation(RequirementInfo current, RequirementInfo to);

    private Set<RequirementInfo> getNextTemplateRequirement(RequirementInfo current) {
        return template.getNext(current);
    }

    private List<Stimulation> buildStimulationByRequirement(RequirementInfo current) {
        Set<RequirementInfo> tos = getNextTemplateRequirement(current);
        if (tos != null && !tos.isEmpty()) {
            return tos.stream()
                    .map(to -> buildStimulation(current, to))
                    .filter(stimulation -> stimulationInterceptors.stream().allMatch(validateStimulationInterceptor -> validateStimulationInterceptor.validate(stimulation)))
                    .filter(stimulation -> stimulation.to().getInterceptors().stream().allMatch(validateStimulationInterceptor -> validateStimulationInterceptor.validate(stimulation)))
                    .filter(this::addStimulationInTree)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<Stimulation> apply(Stimulation stimulation) {
        RequirementInfo current = stimulation.to();
        return buildStimulationByRequirement(current);
    }

    private boolean addStimulationInTree(Stimulation stimulation) {
        if (aliveTime.decrementAndGet() < 0) {
            return false;
        } else {
            stimulationTree.add(stimulation);
            return true;
        }
    }

    @Override
    public void finish() {
        status = EventStatusEnum.DEATH;
        stimulationTree.printStimulationPath();
    }
}
