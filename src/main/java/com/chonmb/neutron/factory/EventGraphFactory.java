package com.chonmb.neutron.factory;


import com.chonmb.neutron.bus.EventGraph;

public interface EventGraphFactory extends Factory {

    public EventGraph getEventGraph(String topic);
}
