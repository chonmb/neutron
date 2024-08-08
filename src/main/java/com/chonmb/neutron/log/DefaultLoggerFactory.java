package com.chonmb.neutron.log;

import java.util.logging.Logger;

/**
 * @author chonmb
 * @date 2024/8/8 23:15
 * @application neutron
 * @email weichonmb@foxmail.com
 */
public class DefaultLoggerFactory implements EventLoggerFactory {
    @Override
    public Logger getLogger(Class<?> clazz) {
        Logger logger= Logger.getLogger(clazz.getName());
        logger.setUseParentHandlers(false);
        logger.addHandler(new DefaultConsoleHandler());
        return logger;
    }
}
