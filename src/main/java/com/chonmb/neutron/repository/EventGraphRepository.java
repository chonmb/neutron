package com.chonmb.neutron.repository;

import com.chonmb.neutron.repository.domain.EventDeliverMap;

import java.util.Optional;

public interface EventGraphRepository extends Repository {

    Optional<EventDeliverMap> getEventGraph(String topic);
}
