package com.chonmb.neutron.utils;

import com.chonmb.neutron.exception.InvalidParameterException;

import java.util.Collection;


public class ValidateUtil {
    private ValidateUtil() {
    }

    public static void validateString(String s) {
        if (s == null || s.length() == 0) {
            throw new InvalidParameterException();
        }
    }

    public static void validateObject(Object o) {
        if (o == null) {
            throw new InvalidParameterException();
        }
    }

    public static void validateCollectors(Collection<?> o) {
        if (o == null || o.isEmpty()) {
            throw new InvalidParameterException();
        }
    }
}
