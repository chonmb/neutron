package com.chonmb.neutron.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;

/**
 * @author chonmb
 * @date 2024/8/8 23:18
 * @application neutron
 * @email weichonmb@foxmail.com
 */
public class DefaultConsoleHandler extends ConsoleHandler {
    public DefaultConsoleHandler() {
        super();
        setFormatter(new DefaultConsoleFormatter());
    }
}
