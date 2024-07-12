package com.chonmb.neutron.bus;

import com.chonmb.neutron.utils.ValidateUtil;

import java.util.HashMap;
import java.util.Map;

public class EventGraphGroupMap implements EventGraphGroup {
    private final Map<String, EventGraph> map = new HashMap<>();

    @Override
    public EventGraph getEventGraph(String topic) {
        ValidateUtil.validateString(topic);
        return map.get(topic);
    }

    @Override
    public void putEventGraph(String topic, EventGraph graph) {
        ValidateUtil.validateString(topic);
        ValidateUtil.validateObject(graph);
        map.put(topic, graph);
    }

}
