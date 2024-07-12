package com.chonmb.neutron.factory.impl;

import com.chonmb.neutron.bus.EventGraph;
import com.chonmb.neutron.event.Event;
import com.chonmb.neutron.event.SimpleEvent;
import com.chonmb.neutron.factory.Factory;

public class EventFactory implements Factory {
    private final EventGraphFactoryAdapter adapter;

    public EventFactory(EventGraphFactoryAdapter adapter) {
        this.adapter = adapter;
    }

    public Event create(String topic){
        EventGraph eventGraphTemplate=adapter.getEventGraph(topic);

        return new SimpleEvent(topic,eventGraphTemplate);
    }
}
