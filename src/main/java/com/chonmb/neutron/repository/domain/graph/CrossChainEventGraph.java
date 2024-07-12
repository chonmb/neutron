package com.chonmb.neutron.repository.domain.graph;

import com.chonmb.neutron.repository.domain.DeliverEdge;
import com.chonmb.neutron.repository.domain.EventDeliverMap;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrossChainEventGraph implements EventDeliverMap {
    private List<CrossChainItem> items;
    private List<ValidateStimulationInterceptor> interceptors=new ArrayList<>();

    @Override
    public List<ValidateStimulationInterceptor> getInterceptors() {
        return interceptors;
    }


    public void setInterceptors(List<ValidateStimulationInterceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public List<CrossChainItem> getItems() {
        return items;
    }

    public void setItems(List<CrossChainItem> items) {
        this.items = items;
    }

    @Override
    public List<RequirementInfo> getDeliverNodes() {
        return items.stream().flatMap(crossChainItem ->
                Stream.of(crossChainItem.getHead(), crossChainItem.getTail())
        ).distinct().collect(Collectors.toList());
    }

    @Override
    public List<DeliverEdge> getDeliverEdge() {
        return items.stream().map(crossChainItem -> (DeliverEdge) crossChainItem).collect(Collectors.toList());
    }
}
