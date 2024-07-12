package com.chonmb.neutron.repository.domain;

public class GraphDeliverEdge implements DeliverEdge{
    private final String from;
    private final String to;

    public GraphDeliverEdge(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String from() {
        return from;
    }

    @Override
    public String to() {
        return to;
    }
}
