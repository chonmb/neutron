package com.chonmb.neutron.utils;

public class ClassUtils {

    private ClassUtils(){}

    public static boolean isInterfaceImplemented(Class<?> clazz, Class<?> interfaceClass) {
        if (interfaceClass.isAssignableFrom(clazz)) {
            return true;
        }
        for (Class<?> iface : clazz.getInterfaces()) {
            if (isInterfaceImplemented(iface, interfaceClass)) {
                return true;
            }
        }
        return false;
    }
}
