package com.chonmb.neutron.repository.domain.graph;

import com.chonmb.neutron.repository.domain.DeliverEdge;
import com.chonmb.neutron.requirements.RequirementInfo;

public class CrossChainItem implements DeliverEdge {
    private final RequirementInfo head;
    private final RequirementInfo tail;

    public CrossChainItem(RequirementInfo head, RequirementInfo tail) {
        this.head = head;
        this.tail = tail;
    }

    public RequirementInfo getHead() {
        return head;
    }

    public RequirementInfo getTail() {
        return tail;
    }

    @Override
    public String from() {
        return head.getName();
    }

    @Override
    public String to() {
        return tail.getName();
    }
}

