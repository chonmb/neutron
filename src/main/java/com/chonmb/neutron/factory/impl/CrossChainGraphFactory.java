package com.chonmb.neutron.factory.impl;

import com.chonmb.neutron.bus.EventGraph;
import com.chonmb.neutron.factory.AbstractEventGraphFactory;
import com.chonmb.neutron.repository.CrossChainGraphRepository;


public class CrossChainGraphFactory extends AbstractEventGraphFactory {

    private final CrossChainGraphRepository crossChainGraphRepository;

    public CrossChainGraphFactory(CrossChainGraphRepository crossChainGraphRepository) {
        this.crossChainGraphRepository = crossChainGraphRepository;
    }

    @Override
    public EventGraph getEventGraph(String topic) {
        return crossChainGraphRepository.getEventGraph(topic).map(this::convertDeliverMap).orElse(null);
    }

}
