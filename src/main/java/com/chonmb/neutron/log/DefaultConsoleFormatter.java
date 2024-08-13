package com.chonmb.neutron.log;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author chonmb
 * @date 2024/8/14 00:08
 * @application neutron
 * @email weichonmb@foxmail.com
 */
public class DefaultConsoleFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record.getLevel() +": "+ record.getMessage() +"\n";
    }
}
