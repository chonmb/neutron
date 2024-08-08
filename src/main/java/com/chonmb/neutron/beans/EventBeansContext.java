package com.chonmb.neutron.beans;

import java.util.List;

public interface EventBeansContext {
    public <T> T getBean(String name, Class<T> tClass);

    public <T> T getBean(Class<T> tClass);

    public <T> List<T> getBeansImplement(Class<T> tClass);

    void putBean(Object bean);
}
