package com.chonmb.neutron;

import com.chonmb.neutron.event.Event;
import com.chonmb.neutron.utils.EventEngineApplicationTest;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

@EventEngineApplicationTest
public class EventEngineApp {

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        EventEngineApplication application = new EventEngineApplication();
        application.run();
        Event event = application.createEvent("topic");
        application.deliverEvent(event);
        Event event1 = application.createEvent("line");
        application.deliverEvent(event1);
        TimeUnit.SECONDS.sleep(10);

    }

}
