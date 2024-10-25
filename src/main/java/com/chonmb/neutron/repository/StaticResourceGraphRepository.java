package com.chonmb.neutron.repository;

import com.chonmb.neutron.repository.domain.EventDeliverMap;
import com.chonmb.neutron.repository.domain.graph.MatrixEventGraph;
import com.chonmb.neutron.repository.domain.graph.StaticGraphData;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.requirements.info.AbstractRequirementInfo;
import com.chonmb.neutron.requirements.info.SimpleRequirementInfo;
import com.chonmb.neutron.trigger.WaitAllStimulationInterceptor;
import com.chonmb.neutron.utils.ResourceUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chonmb
 * @application neutron
 * @email weichonmb@foxmail.com
 * @date 2024/7/25 8:53
 */

public class StaticResourceGraphRepository implements EventGraphRepository {
    private final Map<String, EventDeliverMap> resources = new HashMap<>();

    public StaticResourceGraphRepository() {
        try {
            Reader reader = new InputStreamReader(ResourceUtils.loadResourceAsStream("map/graph.json"));
            Gson gson = new Gson();
            Type type = new TypeToken<List<StaticGraphData>>() {
            }.getType();
            List<StaticGraphData> data = gson.fromJson(reader, type);
            for (StaticGraphData datum : data) {
                Map<String, SimpleRequirementInfo> nodes = datum.getNodes().stream().map(node -> new SimpleRequirementInfo(node.getName(), node.getExecutorName())).collect(Collectors.toMap(AbstractRequirementInfo::getName, o -> o));
                datum.getNodes().forEach(node -> {
                    if (node.getPreNodesAllReady() != null && !node.getPreNodesAllReady().isEmpty()) {
                        nodes.get(node.getName()).waitAllReady(node.getPreNodesAllReady().stream().map(nodes::get).toArray(RequirementInfo[]::new));
                    }
                });
                resources.put(datum.getTopic(), new MatrixEventGraph(datum.getMatrix(), datum.getNodes().stream().map(node -> nodes.get(node.getName())).collect(Collectors.toList())));
            }
        } catch (IOException e) {

        }
    }

    @Override
    public Optional<EventDeliverMap> getEventGraph(String topic) {
        return Optional.ofNullable(resources.get(topic));
    }
}
