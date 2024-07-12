package com.chonmb.neutron.event.stimulate;

import com.chonmb.neutron.event.Event;
import com.chonmb.neutron.event.Stimulation;
import com.chonmb.neutron.event.domain.StimulationStatusEnum;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.trigger.StatisticEventListener;

public class SimpleStimulation implements Stimulation {
    private final RequirementInfo from;
    private final RequirementInfo to;
    private StimulationStatusEnum status;
    private final Event event;

    public SimpleStimulation(Event event,RequirementInfo from, RequirementInfo to) {
        this.from = from;
        this.to = to;
        this.event=event;
        this.status=StimulationStatusEnum.ACTIVE;
    }

    @Override
    public RequirementInfo from() {
        return from;
    }

    @Override
    public RequirementInfo to() {
        return to;
    }

    @Override
    public StimulationStatusEnum status() {
        return status;
    }

    @Override
    public String topic() {
        return event.topic();
    }

    @Override
    public void finish() {
        freshStatus(StimulationStatusEnum.STILL);
    }

    @Override
    public void freshStatus(StimulationStatusEnum status) {
        switch (status){
            case STILL:
                this.status=status;
                StatisticEventListener.deliverStimulationByBatch(event.apply(this));
                break;
            case HANDLING:
                this.status=status;
                break;
            default:
                break;
        }
    }

    @Override
    public Object content() {
        return null;
    }

    @Override
    public Event getEvent() {
        return event;
    }
}
