package com.chonmb.neutron.event;

import java.util.List;

public interface Event {
    String topic();
    List<Stimulation> apply();
    List<Stimulation> apply(Stimulation stimulation);
    void finish();
}
