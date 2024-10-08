package com.chonmb.neutron.beans;

import com.chonmb.neutron.EventEngineContext;
import com.chonmb.neutron.bus.Bus;
import com.chonmb.neutron.config.Configuration;
import com.chonmb.neutron.config.GlobalEventConfiguration;
import com.chonmb.neutron.factory.Factory;
import com.chonmb.neutron.repository.Repository;
import com.chonmb.neutron.requirements.RequirementInstance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.chonmb.neutron.utils.ClassUtils.getThreadStackTraceClass;
import static com.chonmb.neutron.utils.ClassUtils.isInterfaceImplemented;

/**
 * 应用上下文
 */
public class ApplicationBeansContext implements EventBeansContext {
    private final BeansMap beansMap = new BeansMap();
    private List<BeansDefine> beansDefines;
    private final GlobalEventConfiguration globalEventConfiguration = new GlobalEventConfiguration();


    public void scanAndLocatedBeans() {
        freshGlobalConfigurationBeforeStart();
        this.beansDefines = scanClass()
                .stream()
                .filter(this::filterInstantClass)
                .map(ClassDefine::new)
                .collect(Collectors.toList());
        beansDefines.forEach(beansMap::put);
    }

    private void freshGlobalConfigurationBeforeStart() {
        globalEventConfiguration.getScanPackage().addAll(getAnnotatedScanPackages());
        putBean(globalEventConfiguration);
    }

    private Set<String> getAnnotatedScanPackages() {
        return getThreadStackTraceClass()
                .stream()
                .flatMap(aClass ->
                        Optional.ofNullable(aClass.getAnnotation(EventScanner.class))
                                .map(eventScanner -> Stream.of(eventScanner.scanPages()))
                                .orElse(Stream.empty())
                ).collect(Collectors.toSet());
    }

    public boolean filterInstantClass(Class<?> aClass) {
        if (((isInterfaceImplemented(aClass, Factory.class)
                || isInterfaceImplemented(aClass, Bus.class)
                || isInterfaceImplemented(aClass, Repository.class)
                || isInterfaceImplemented(aClass, Configuration.class)
                || isInterfaceImplemented(aClass, RequirementInstance.class)) && !Modifier.isAbstract(aClass.getModifiers()))) {
            return true;
        }
        return false;
    }

    /**
     * 获取指定包下的所有类
     *
     * @return 类列表
     */
    private Collection<Class<?>> scanClass() {
        BeanDefineScanner cs2 = new BeanDefineScanner();
        globalEventConfiguration.getScanPackage().forEach(packages -> cs2.scanning(packages, false));
        return cs2.getClasses().values();
    }

    private boolean existBean(BeansDefine beansDefine) {
        return beansMap.existBean(beansDefine);
    }

    private Object getBean(BeansDefine beansDefine) {
        if (existBean(beansDefine)) {
            return beansMap.getBean(beansDefine);
        } else {
            return instanceBean(beansDefine);
        }
    }

    @Override
    public <T> T getBean(String name, Class<T> tClass) {
        return (T) beansMap.getBean(new ClassDefine<>(tClass, name));
    }

    @Override
    public <T> T getBean(Class<T> tClass) {
        return (T) beansMap.getBean(new ClassDefine<>(tClass));
    }

    @Override
    public <T> List<T> getBeansImplement(Class<T> tClass) {
        return beansMap.getBeansImplement(tClass);
    }

    private Object instanceBean(BeansDefine beansDefine) {
        for (Constructor<?> constructor : beansDefine.getBeanClass().getConstructors()) {
            try {
                List<Object> parameters = Arrays.stream(constructor.getParameters()).map(parameter -> getBean(new ClassDefine<>(parameter.getType()))).collect(Collectors.toList());
                Object instance = constructor.newInstance(parameters.size() == 0 ? null : parameters.toArray());
                beansMap.put(beansDefine, instance);
                return instance;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                 throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void instantBeans() {
        for (BeansDefine beansDefine : beansDefines) {
            if (!existBean(beansDefine)) {
                instanceBean(beansDefine);
            }
        }
    }

    public void afterFreshBeans() {
        beansMap.getBeansImplement(EventEngineContext.class).forEach(eventEngineContext -> eventEngineContext.setEventBeansContext(this));
    }

    @Override
    public void putBean(Object bean) {
        beansMap.put(new ClassDefine<>(bean.getClass()), bean);
    }
}
