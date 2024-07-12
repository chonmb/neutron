package com.chonmb.neutron.factory;

import com.chonmb.neutron.bus.EventGraph;
import com.chonmb.neutron.bus.SingleStartEventGraphMap;
import com.chonmb.neutron.repository.domain.DeliverEdge;
import com.chonmb.neutron.repository.domain.EventDeliverMap;
import com.chonmb.neutron.requirements.RequirementInfo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractEventGraphFactory implements EventGraphFactory{

    protected EventGraph convertDeliverMap(EventDeliverMap eventDeliverMap) {
        Map<String, RequirementInfo> nodes = eventDeliverMap.getDeliverNodes().stream().collect(Collectors.toMap(RequirementInfo::getName, requirementInfo -> requirementInfo));
        Map<RequirementInfo, Set<RequirementInfo>> ins = eventDeliverMap.getDeliverNodes().stream().collect(Collectors.toMap(requirementInfo -> requirementInfo, requirementInfo -> new HashSet<>()));
        Map<RequirementInfo, Set<RequirementInfo>> outs = eventDeliverMap.getDeliverNodes().stream().collect(Collectors.toMap(requirementInfo -> requirementInfo, requirementInfo -> new HashSet<>()));
        for (DeliverEdge deliverEdge : eventDeliverMap.getDeliverEdge()) {
            outs.get(nodes.get(deliverEdge.from())).add(nodes.get(deliverEdge.to()));
            ins.get(nodes.get(deliverEdge.to())).add(nodes.get(deliverEdge.from()));
        }
        return new SingleStartEventGraphMap(eventDeliverMap.getDeliverNodes(),
                ins.entrySet().stream().filter(requirementInfoSetEntry -> !requirementInfoSetEntry.getValue().isEmpty()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
                outs.entrySet().stream().filter(requirementInfoSetEntry -> !requirementInfoSetEntry.getValue().isEmpty()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
                eventDeliverMap.getInterceptors());
    }
}
