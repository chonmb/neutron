package com.chonmb.neutron.log;

import com.chonmb.neutron.factory.Factory;

import java.util.logging.Logger;

/**
 * @author chonmb
 * @date 2024/8/8 23:14
 * @application neutron
 * @email weichonmb@foxmail.com
 */
public class LoggerFactory implements Factory {
    private static EventLoggerFactory instance;

    public static Logger getLogger(Class<?> clazz) {
        if (instance == null) {
            instance = new DefaultLoggerFactory();
        }
        return instance.getLogger(clazz);
    }

}
