package com.chonmb.neutron;

import com.chonmb.neutron.beans.ApplicationBeansContext;
import com.chonmb.neutron.beans.EventBeansContext;
import com.chonmb.neutron.bus.EventBus;
import com.chonmb.neutron.event.Event;
import com.chonmb.neutron.factory.impl.EventFactory;

public class EventEngineApplication implements EventEngineContext {
    private EventBus eventBus;
    private EventFactory eventFactory;
    private final ApplicationBeansContext beansContext = new ApplicationBeansContext();

    public Event createEvent(String topic) {
        return eventFactory.create(topic);
    }

    public void deliverEvent(Event event) {
        eventBus.deliverStimulationByBatch(event.apply());
    }

    public void run() {
        beansContext.scanAndLocatedBeans();
        beansContext.putBean(this);
        beansContext.instantBeans();
        beansContext.afterFreshBeans();
    }

    @Override
    public void setEventBeansContext(EventBeansContext eventBeansContext) {
        eventBus = eventBeansContext.getBean(EventBus.class);
        eventFactory = eventBeansContext.getBean(EventFactory.class);
    }
}
