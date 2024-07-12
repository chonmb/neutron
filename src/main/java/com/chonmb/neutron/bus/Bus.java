package com.chonmb.neutron.bus;

import com.chonmb.neutron.event.Stimulation;

import java.util.Collection;

public interface Bus {

    public void deliverStimulation(Stimulation stimulation);

    public void deliverStimulationByBatch(Collection<Stimulation> stimulations);
}
