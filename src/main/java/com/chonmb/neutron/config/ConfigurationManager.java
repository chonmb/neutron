package com.chonmb.neutron.config;

import com.chonmb.neutron.EventEngineContext;
import com.chonmb.neutron.beans.EventBeansContext;
import com.chonmb.neutron.repository.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

public class ConfigurationManager implements Repository, EventEngineContext {
    private EventBeansContext eventBeansContext;
    private final Properties properties = new Properties();

    public ConfigurationManager() {
        this.loadProperties();
    }

    private void loadProperty(String resource) {
        try {
            properties.load(Objects.requireNonNull(getClass().getClassLoader().getResource(resource)).openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setEventBeansContext(EventBeansContext eventBeansContext) {
        this.eventBeansContext = eventBeansContext;
        eventBeansContext.getBeansImplement(Configuration.class).forEach(configuration -> {
            Arrays.stream(configuration.getClass().getFields()).forEach(field -> {
                try {
                    if (properties.containsKey(field.getName())){
                        field.setAccessible(true);
                        field.set(configuration, properties.get(field.getName()));
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    private void loadProperties() {
        loadProperty("config/event.properties");
    }
}
