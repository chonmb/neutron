package com.chonmb.neutron.factory.impl;

import com.chonmb.neutron.bus.EventGraph;
import com.chonmb.neutron.factory.AbstractEventGraphFactory;
import com.chonmb.neutron.repository.MatrixGraphRepository;


public class MatrixGraphFactory extends AbstractEventGraphFactory {
    private final MatrixGraphRepository matrixGraphRepository;

    public MatrixGraphFactory(MatrixGraphRepository matrixGraphRepository) {
        this.matrixGraphRepository = matrixGraphRepository;
    }

    @Override
    public EventGraph getEventGraph(String topic) {
        return matrixGraphRepository.getEventGraph(topic).map(this::convertDeliverMap).orElse(null);
    }
}
