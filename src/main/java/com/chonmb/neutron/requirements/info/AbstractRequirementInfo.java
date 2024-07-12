package com.chonmb.neutron.requirements.info;

import com.chonmb.neutron.requirements.RequirementInfo;


public abstract class AbstractRequirementInfo implements RequirementInfo {
    private final String name;
    public AbstractRequirementInfo(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        return ((RequirementInfo)obj).getName().equals(this.getName());
    }

    @Override
    public String getName() {
        return name;
    }
}
