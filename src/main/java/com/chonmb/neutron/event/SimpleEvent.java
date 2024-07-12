package com.chonmb.neutron.event;

import com.chonmb.neutron.bus.EventGraph;
import com.chonmb.neutron.event.domain.EventStatusEnum;
import com.chonmb.neutron.event.domain.StimulationTree;
import com.chonmb.neutron.event.stimulate.SimpleStimulationWithContent;
import com.chonmb.neutron.requirements.RequirementInfo;

public class SimpleEvent extends AbstractEvent{


    public SimpleEvent(String topic, EventGraph template) {
        this(EventStatusEnum.ALIVE, new StimulationTree(), topic, template);
    }

    public SimpleEvent(EventStatusEnum status, StimulationTree stimulationTree, String topic, EventGraph template) {
        super(status, stimulationTree, topic, template);
    }

    @Override
    protected Stimulation buildStimulation(RequirementInfo current, RequirementInfo to) {
        return new SimpleStimulationWithContent(this,current,to);
    }
}
