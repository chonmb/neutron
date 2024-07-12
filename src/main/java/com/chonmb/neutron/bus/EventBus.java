package com.chonmb.neutron.bus;

import com.chonmb.neutron.factory.impl.EventGraphFactoryAdapter;
import com.chonmb.neutron.factory.impl.ExecutorFactory;
import com.chonmb.neutron.factory.impl.RequirementInstanceFactory;
import com.chonmb.neutron.trigger.StatisticEventListener;

public class EventBus extends AbstractBus {
    public EventBus(EventGraphFactoryAdapter adapter, RequirementInstanceFactory requirementInstanceFactory, ExecutorFactory executorFactory) {
        super(adapter, requirementInstanceFactory, executorFactory);
        StatisticEventListener.setBus(this);
    }
}
