package com.chonmb.neutron.repository.domain;

import com.chonmb.neutron.requirements.RequirementInfo;

import java.util.Map;

public interface CombineMap {
    void combine(EventDeliverMap eventDeliverMap, Map<RequirementInfo,RequirementInfo> nodesMapper);
}
