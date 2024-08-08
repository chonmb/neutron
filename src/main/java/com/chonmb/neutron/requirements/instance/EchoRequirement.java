package com.chonmb.neutron.requirements.instance;

import com.chonmb.neutron.event.Stimulation;
import com.chonmb.neutron.log.LoggerFactory;

import java.util.logging.Logger;


public class EchoRequirement extends AbstractRequirementInstance {
    private final Logger logger = LoggerFactory.getLogger(EchoRequirement.class);;

    @Override
    public void apply(Stimulation stimulation) {
        logger.info("echo executor execute stimulation, print content: "+stimulation.content());
    }

    @Override
    public String name() {
        return "echo";
    }
}
