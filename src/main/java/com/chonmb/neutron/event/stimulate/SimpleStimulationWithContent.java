package com.chonmb.neutron.event.stimulate;

import com.chonmb.neutron.event.Event;
import com.chonmb.neutron.requirements.RequirementInfo;

public class SimpleStimulationWithContent extends SimpleStimulation {

    private final String content;

    public SimpleStimulationWithContent(Event event, RequirementInfo from, RequirementInfo to) {
        super(event, from, to);
        content=from.getName()+" -> "+to.getName();
    }

    @Override
    public Object content() {
        return content;
    }
}
