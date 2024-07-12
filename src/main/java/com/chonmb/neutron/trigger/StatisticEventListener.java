package com.chonmb.neutron.trigger;

import com.chonmb.neutron.bus.Bus;
import com.chonmb.neutron.event.Stimulation;

import java.util.Collection;

public class StatisticEventListener implements BusTrigger {
    private static Bus bus;

    public static void deliverStimulationByBatch(Collection<Stimulation> stimulations) {
        bus.deliverStimulationByBatch(stimulations);
    }

    public static void setBus(Bus bus) {
        StatisticEventListener.bus = bus;
    }
}
