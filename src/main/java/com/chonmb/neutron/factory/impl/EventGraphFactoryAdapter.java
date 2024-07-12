package com.chonmb.neutron.factory.impl;

import com.chonmb.neutron.EventEngineContext;
import com.chonmb.neutron.beans.EventBeansContext;
import com.chonmb.neutron.bus.EventGraph;
import com.chonmb.neutron.exception.GraphNotFoundException;
import com.chonmb.neutron.factory.EventGraphFactory;
import com.chonmb.neutron.factory.Factory;

import java.util.List;
import java.util.Objects;

public class EventGraphFactoryAdapter implements Factory, EventEngineContext {
    private List<EventGraphFactory> factories;

    public EventGraph getEventGraph(String topic) {
        return factories.stream().map(eventGraphFactory -> eventGraphFactory.getEventGraph(topic)).filter(Objects::nonNull).findFirst().orElseThrow(GraphNotFoundException::new);
    }

    @Override
    public void setEventBeansContext(EventBeansContext eventBeansContext) {
        factories = eventBeansContext.getBeansImplement(EventGraphFactory.class);
    }
}
