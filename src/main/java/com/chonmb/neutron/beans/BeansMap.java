package com.chonmb.neutron.beans;

import com.chonmb.neutron.exception.BeanNotFoundException;
import com.chonmb.neutron.utils.ClassUtils;

import java.util.*;
import java.util.stream.Collectors;

public class BeansMap {
    private final Map<Class<?>, Map<String, Object>> beansMap = new HashMap<>();

    public Object getBean(BeansDefine beansDefine) {
        return Optional.ofNullable(beansMap.get(beansDefine.getBeanClass()))
                .map(map -> map.get(beansDefine.getBeanName()))
                .orElseThrow(()->new BeanNotFoundException(beansDefine.getClass().getName()));
    }

    public void put(BeansDefine beansDefine, Object o) {
        Map<String, Object> nameMap = beansMap.getOrDefault(beansDefine.getBeanClass(), new HashMap<>());
        nameMap.put(beansDefine.getBeanName(), o);
        beansMap.put(beansDefine.getBeanClass(), nameMap);
    }

    public void put(BeansDefine beansDefine) {
        Map<String, Object> nameMap = beansMap.getOrDefault(beansDefine.getBeanClass(), new HashMap<>());
        beansMap.put(beansDefine.getBeanClass(), nameMap);
    }

    public boolean existBean(BeansDefine beansDefine) {
        return Optional.ofNullable(beansMap.get(beansDefine.getBeanClass()))
                .map(map -> map.containsKey(beansDefine.getBeanName()) && map.get(beansDefine.getBeanName()) != null)
                .orElse(false);
    }

    public <T> List<T> getBeansImplement(Class<T> tClass) {
        return beansMap.entrySet()
                .stream()
                .filter(classMapEntry -> ClassUtils.isInterfaceImplemented(classMapEntry.getKey(), tClass))
                .flatMap(classMapEntry -> Optional.ofNullable(classMapEntry.getValue())
                        .map(Map::values).orElse(new ArrayList<>())
                        .stream().map(o -> (T) o))
                .collect(Collectors.toList());
    }
}
