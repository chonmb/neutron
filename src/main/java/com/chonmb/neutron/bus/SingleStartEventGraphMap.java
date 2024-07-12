package com.chonmb.neutron.bus;

import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.requirements.info.SimpleEndRequirementInfo;
import com.chonmb.neutron.requirements.info.SimpleStartRequirementInfo;
import com.chonmb.neutron.requirements.instance.SimpleStartRequirement;
import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;
import com.chonmb.neutron.utils.ValidateUtil;

import java.util.*;
import java.util.stream.Collectors;

public class SingleStartEventGraphMap implements EventGraph {
    private Map<String, Set<RequirementInfo>> nextMap;
    private Map<String, Set<RequirementInfo>> previewMap;
    private final SimpleStartRequirementInfo start = new SimpleStartRequirementInfo();
    private final SimpleEndRequirementInfo end = new SimpleEndRequirementInfo();
    private Map<String, RequirementInfo> nodes;
    private Collection<ValidateStimulationInterceptor> interceptors;


    public SingleStartEventGraphMap(List<RequirementInfo> nodes, Map<RequirementInfo, Set<RequirementInfo>> ins, Map<RequirementInfo, Set<RequirementInfo>> outs, Collection<ValidateStimulationInterceptor> interceptors) {
        this.nextMap = outs.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey().getName(), Map.Entry::getValue));
        this.previewMap = ins.entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey().getName(), Map.Entry::getValue));
        this.nodes = nodes.stream().collect(Collectors.toMap(RequirementInfo::getName, requirementInfo -> requirementInfo));
        Set<RequirementInfo> onlyOut = nextMap.keySet().stream().filter(s -> !previewMap.containsKey(s)).map(s -> this.nodes.get(s)).collect(Collectors.toSet());
        ValidateUtil.validateCollectors(onlyOut);
        nodes.add(start);
        nextMap.put(start.getName(), onlyOut);
        onlyOut.forEach(requirementInfo -> previewMap.put(requirementInfo.getName(), Collections.singleton(start)));
        Set<RequirementInfo> onlyIn = previewMap.keySet().stream().filter(s -> !nextMap.containsKey(s)).map(s -> this.nodes.get(s)).collect(Collectors.toSet());
        onlyIn.forEach(requirementInfo -> nextMap.put(requirementInfo.getName(), Collections.singleton(end)));
        previewMap.put(end.getName(), onlyIn);
        end.waitAllReady(onlyIn.toArray(new RequirementInfo[0]));
        this.interceptors = interceptors;
    }

    public SingleStartEventGraphMap(List<RequirementInfo> nodes, Map<RequirementInfo, Set<RequirementInfo>> ins, Map<RequirementInfo, Set<RequirementInfo>> outs) {
        this(nodes, ins, outs, Collections.emptySet());
    }

    @Override
    public Set<RequirementInfo> getNext(RequirementInfo current) {
        return nextMap.get(current.getName());
    }

    @Override
    public Set<RequirementInfo> getPreview(RequirementInfo current) {
        return previewMap.get(current.getName());
    }

    @Override
    public RequirementInfo getStart() {
        return start;
    }

    @Override
    public Collection<ValidateStimulationInterceptor> getInterceptors() {
        return interceptors;
    }
}
