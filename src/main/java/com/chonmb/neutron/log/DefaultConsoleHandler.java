package com.chonmb.neutron.log;

import java.util.logging.Handler;

/**
 * @author chonmb
 * @date 2024/8/8 23:18
 * @application neutron
 * @email weichonmb@foxmail.com
 */
public class DefaultConsoleHandler extends Handler {
    @Override
    public void publish(java.util.logging.LogRecord record) {
        System.out.println(record.getLevel() +": "+ record.getMessage());
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }
}
