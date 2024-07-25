package com.chonmb.neutron.repository;

import com.chonmb.neutron.repository.domain.EventDeliverMap;
import com.chonmb.neutron.repository.domain.graph.MatrixEventGraph;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.requirements.info.SimpleRequirementInfo;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixGraphRepository implements EventGraphRepository {
    @Override
    public Optional<EventDeliverMap> getEventGraph(String topic) {
//        if ("line".equals(topic)) {
//            boolean[][] map = {{false, true, false}, {false, false, true}, {false, false, false}};
//            RequirementInfo node1 = new SimpleRequirementInfo("111", "echo");
//            RequirementInfo node2 = new SimpleRequirementInfo("222", "echo");
//            RequirementInfo node3 = new SimpleRequirementInfo("333", "echo");
//            MatrixEventGraph matrixEventGraph = new MatrixEventGraph(map, Stream.of(node1, node2, node3).collect(Collectors.toList()));
//            return Optional.of(matrixEventGraph);
//        }
        return Optional.empty();
    }
}
