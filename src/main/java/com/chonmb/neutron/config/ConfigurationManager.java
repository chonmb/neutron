package com.chonmb.neutron.config;

import com.chonmb.neutron.EventEngineContext;
import com.chonmb.neutron.beans.EventBeansContext;
import com.chonmb.neutron.repository.Repository;
import com.chonmb.neutron.utils.ResourceUtils;

import java.io.IOException;
import java.util.*;

public class ConfigurationManager implements Repository, EventEngineContext {
    private EventBeansContext eventBeansContext;
    private final Properties properties = new Properties();
    private final GlobalEventConfiguration globalEventConfiguration;

    public ConfigurationManager(GlobalEventConfiguration globalEventConfiguration) {
        this.globalEventConfiguration = globalEventConfiguration;
        this.loadProperties();
    }

    private void loadProperty(String resource) {
        try {
            properties.load(ResourceUtils.loadResourceAsStream(resource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setEventBeansContext(EventBeansContext eventBeansContext) {
        this.eventBeansContext = eventBeansContext;
        eventBeansContext.getBeansImplement(Configuration.class).forEach(configuration -> {
            String configurationPrefix = Optional.ofNullable(configuration.getClass().getAnnotation(EventConfiguration.class)).map(EventConfiguration::value).orElse("");
            Arrays.stream(configuration.getClass().getFields()).forEach(field -> {
                try {
                    String key = configurationPrefix.length() == 0 ? field.getName() : configurationPrefix + "." + field.getName();
                    if (properties.containsKey(key)) {
                        if (field.get(configuration) instanceof Collection) {
                            ((Collection) field.get(configuration)).add(properties.get(key));
                        } else {
                            field.setAccessible(true);
                            field.set(configuration, properties.get(key));
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    private void loadProperties() {
        globalEventConfiguration.resources.forEach(this::loadProperty);
    }
}
