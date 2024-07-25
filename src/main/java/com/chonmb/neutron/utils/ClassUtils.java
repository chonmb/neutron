package com.chonmb.neutron.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<Class<?>> getThreadStackTraceClass(){
        return Arrays.stream(Thread.currentThread().getStackTrace()).map(stackTraceElement -> {
            try {
                return Class.forName(stackTraceElement.getClassName());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
}
