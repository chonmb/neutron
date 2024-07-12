package com.chonmb.neutron.requirements;

import com.chonmb.neutron.event.Stimulation;

public interface RequirementInstance {
    void handle(Stimulation stimulation);

    String name();
}
