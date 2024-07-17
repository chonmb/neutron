package com.chonmb.neutron.beans;

import com.chonmb.neutron.EventEngineContext;
import com.chonmb.neutron.bus.Bus;
import com.chonmb.neutron.config.Configuration;
import com.chonmb.neutron.factory.Factory;
import com.chonmb.neutron.repository.Repository;
import com.chonmb.neutron.requirements.RequirementInstance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.chonmb.neutron.utils.ClassUtils.isInterfaceImplemented;

/**
 * 应用上下文
 */
public class ApplicationBeansContext implements EventBeansContext {
    private final BeansMap beansMap = new BeansMap();
    private List<BeansDefine> beansDefines;


    public void scanAndLocatedBeans() {
        this.beansDefines = scanClass("com.chonmb.neutron")
                .stream()
                .filter(this::filterInstantClass)
                .map(ClassDefine::new)
                .collect(Collectors.toList());
        beansDefines.forEach(beansMap::put);
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
     * @param packages 包名
     * @return 类列表
     */
    private Collection<Class<?>> scanClass(String packages) {
        BeanDefineScanner cs2 = new BeanDefineScanner();
        cs2.scanning(packages, false);
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
                // throw new RuntimeException(e);
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

    public void putBean(Object bean) {
        beansMap.put(new ClassDefine<>(bean.getClass()), bean);
    }
}
