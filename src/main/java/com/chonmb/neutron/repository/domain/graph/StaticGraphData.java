package com.chonmb.neutron.repository.domain.graph;

import java.util.List;

/**
 * @author chonmb
 * @date 2024/7/25 9:54
 */

public class StaticGraphData {
    private String topic;
    private boolean[][] matrix;
    private List<StaticGraphNode> nodes;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    public List<StaticGraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<StaticGraphNode> nodes) {
        this.nodes = nodes;
    }

    public static class StaticGraphNode {
        private String name;
        private String executorName;
        private List<String> preNodesAllReady;

        public List<String> getPreNodesAllReady() {
            return preNodesAllReady;
        }

        public void setPreNodesAllReady(List<String> preNodesAllReady) {
            this.preNodesAllReady = preNodesAllReady;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExecutorName() {
            return executorName;
        }

        public void setExecutorName(String executorName) {
            this.executorName = executorName;
        }
    }
}
