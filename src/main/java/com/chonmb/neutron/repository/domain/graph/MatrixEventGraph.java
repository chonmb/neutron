package com.chonmb.neutron.repository.domain.graph;

import com.chonmb.neutron.bus.EventGraph;
import com.chonmb.neutron.repository.domain.DeliverEdge;
import com.chonmb.neutron.repository.domain.EventDeliverMap;
import com.chonmb.neutron.repository.domain.GraphDeliverEdge;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.trigger.ValidateStimulationInterceptor;

import java.util.*;

public class MatrixEventGraph implements EventDeliverMap {

    private boolean[][] matrix;
    private List<RequirementInfo> nodes;
    private List<ValidateStimulationInterceptor> interceptors;


    public MatrixEventGraph(boolean[][] matrix, List<RequirementInfo> nodes) {
        this(matrix,nodes, new ArrayList<>());
    }

    public MatrixEventGraph(boolean[][] matrix, List<RequirementInfo> nodes, List<ValidateStimulationInterceptor> interceptors) {
        this.matrix = matrix;
        this.nodes = nodes;
        this.interceptors = interceptors;
    }

    @Override
    public List<RequirementInfo> getDeliverNodes() {
        return nodes;
    }

    @Override
    public List<DeliverEdge> getDeliverEdge() {
        List<DeliverEdge> edges=new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                if (matrix[i][j]){
                    edges.add(new GraphDeliverEdge(nodes.get(i).getName(),nodes.get(j).getName()));
                }
            }
        }
        return edges;
    }

    @Override
    public List<ValidateStimulationInterceptor> getInterceptors() {
        return interceptors;
    }


}
