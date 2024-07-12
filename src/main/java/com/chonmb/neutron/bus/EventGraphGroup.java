package com.chonmb.neutron.bus;

public interface EventGraphGroup {

    public EventGraph getEventGraph(String topic);
    public void putEventGraph(String topic,EventGraph graph);
}
