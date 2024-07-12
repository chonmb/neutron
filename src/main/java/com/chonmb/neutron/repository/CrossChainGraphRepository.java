package com.chonmb.neutron.repository;

import com.chonmb.neutron.repository.domain.graph.CrossChainEventGraph;
import com.chonmb.neutron.repository.domain.graph.CrossChainItem;
import com.chonmb.neutron.repository.domain.EventDeliverMap;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.requirements.info.SimpleRequirementInfo;

import java.util.Arrays;
import java.util.Optional;

public class CrossChainGraphRepository implements EventGraphRepository {
    @Override
    public Optional<EventDeliverMap> getEventGraph(String topic) {
        if ("topic".equals(topic)){
            CrossChainEventGraph eventGraph = new CrossChainEventGraph();
            RequirementInfo node1 = new SimpleRequirementInfo("111", "echo");
            RequirementInfo node2 = new SimpleRequirementInfo("222", "echo");
            SimpleRequirementInfo node3 = new SimpleRequirementInfo("333", "echo");
            RequirementInfo node4 = new SimpleRequirementInfo("444", "echo");
            RequirementInfo node5 = new SimpleRequirementInfo("555", "echo");
            CrossChainItem item1 = new CrossChainItem(node1, node2);
            CrossChainItem item2 = new CrossChainItem(node2, node3);
            CrossChainItem item3 = new CrossChainItem(node1, node4);
            CrossChainItem item4 = new CrossChainItem(node1, node5);
            CrossChainItem item5 = new CrossChainItem(node5, node3);
            CrossChainItem item6 = new CrossChainItem(node4, node3);
            node3.waitAllReady(node2,node4,node5);
            eventGraph.setItems(Arrays.asList(item1, item2, item3, item4, item5, item6));
            return Optional.of(eventGraph);
        }
        return Optional.empty();
    }
}
