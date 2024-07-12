package com.chonmb.neutron.trigger;

import com.chonmb.neutron.event.Stimulation;

public interface ActiveInterceptor extends Interceptor {

    boolean accept(Stimulation stimulation);
}
