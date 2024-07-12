package com.chonmb.neutron.bus;

import com.chonmb.neutron.event.Stimulation;
import com.chonmb.neutron.event.domain.StimulationStatusEnum;
import com.chonmb.neutron.exception.GraphNotFoundException;
import com.chonmb.neutron.exception.InvalidStimulationException;
import com.chonmb.neutron.factory.impl.EventGraphFactoryAdapter;
import com.chonmb.neutron.factory.impl.ExecutorFactory;
import com.chonmb.neutron.factory.impl.RequirementInstanceFactory;
import com.chonmb.neutron.requirements.RequirementInfo;
import com.chonmb.neutron.requirements.RequirementInstance;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class AbstractBus implements Bus {
    private final EventGraphGroup graphGroup;
    private final EventGraphFactoryAdapter adapter;
    private ThreadPoolExecutor executor;
    private final RequirementInstanceFactory requirementInstanceFactory;
    private final ExecutorFactory executorFactory;

    public AbstractBus(EventGraphFactoryAdapter adapter, RequirementInstanceFactory requirementInstanceFactory, ExecutorFactory executorFactory) {
        this.adapter = adapter;
        this.requirementInstanceFactory = requirementInstanceFactory;
        this.executorFactory = executorFactory;
        this.graphGroup = new EventGraphGroupMap();
    }

    private EventGraph getGraph(String topic) {
        return Optional.ofNullable(graphGroup.getEventGraph(topic))
                .orElseGet(() -> {
                    EventGraph graph=Optional.ofNullable(adapter.getEventGraph(topic)).orElseThrow(GraphNotFoundException::new);
                    graphGroup.putEventGraph(topic,graph);
                    return graph;
                });
    }

    @Override
    public void deliverStimulation(Stimulation stimulation) {
        if (Objects.isNull(executor)) {
            this.executor = executorFactory.getBusThreadPool();
        }
        executor.execute(() -> this.deliver(stimulation));
//    deliver(stimulation);
    }

    @Override
    public void deliverStimulationByBatch(Collection<Stimulation> stimulation) {
        stimulation.forEach(this::deliverStimulation);
    }

    private void deliver(Stimulation stimulation) {
        EventGraph eventGraph = getGraph(stimulation.topic());
        RequirementInfo target = eventGraph.getNext(stimulation.from()).stream().filter(requirementInfo -> requirementInfo.equals(stimulation.to())).findFirst().orElseThrow(() -> new InvalidStimulationException(stimulation));
        RequirementInstance instance = requirementInstanceFactory.getInstance(target);
        stimulation.freshStatus(StimulationStatusEnum.HANDLING);
        instance.handle(stimulation);
    }
}
