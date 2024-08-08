package com.chonmb.neutron.log;

import java.util.logging.Logger;

/**
 * @author chonmb
 * @date 2024/8/9 00:21
 * @application neutron
 * @email weichonmb@foxmail.com
 */
public interface EventLoggerFactory {
    Logger getLogger(Class<?> clazz);
}
