package com.chonmb.neutron.event;


import com.chonmb.neutron.event.domain.StimulationStatusEnum;
import com.chonmb.neutron.requirements.RequirementInfo;

public interface Stimulation {
    public RequirementInfo from();
    public RequirementInfo to();
    public StimulationStatusEnum status();
    public String topic();
    public void finish();
    public void freshStatus(StimulationStatusEnum status);
    public Object content();
    public Event getEvent();
}
