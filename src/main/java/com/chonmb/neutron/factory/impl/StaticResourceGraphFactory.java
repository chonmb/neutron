package com.chonmb.neutron.factory.impl;

import com.chonmb.neutron.bus.EventGraph;
import com.chonmb.neutron.factory.AbstractEventGraphFactory;
import com.chonmb.neutron.repository.StaticResourceGraphRepository;


public class StaticResourceGraphFactory extends AbstractEventGraphFactory {

    private final StaticResourceGraphRepository staticResourceGraphRepository;

    public StaticResourceGraphFactory(StaticResourceGraphRepository staticResourceGraphRepository) {
        this.staticResourceGraphRepository = staticResourceGraphRepository;
    }

    @Override
    public EventGraph getEventGraph(String topic) {
        return staticResourceGraphRepository.getEventGraph(topic).map(this::convertDeliverMap).orElse(null);
    }

}
