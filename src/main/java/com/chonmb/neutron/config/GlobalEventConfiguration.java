package com.chonmb.neutron.config;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GlobalEventConfiguration implements Configuration {
    public Set<String> scanPackage = Stream.of("com.chonmb.neutron").collect(Collectors.toSet());
    public List<String> resources = Stream.of("config/event.properties").collect(Collectors.toList());

    public Set<String> getScanPackage() {
        return scanPackage;
    }

    public List<String> getResources() {
        return resources;
    }
}
