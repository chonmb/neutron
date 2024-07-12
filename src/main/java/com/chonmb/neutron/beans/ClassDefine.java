package com.chonmb.neutron.beans;

public class ClassDefine<T> implements BeansDefine {
    private final Class<T> aClass;
    private final String beanName;

    public ClassDefine(Class<T> aClass, String beanName) {
        this.aClass = aClass;
        this.beanName = beanName;
    }

    public ClassDefine(Class<T> aClass) {
        this(aClass, aClass.getSimpleName());
    }

    @Override
    public String getBeanName() {
        return this.beanName;
    }

    @Override
    public Class<?> getBeanClass() {
        return this.aClass;
    }
}
