package com.chonmb.neutron.factory.impl;

import com.chonmb.neutron.factory.Factory;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorFactory implements Factory {
    private ThreadPoolExecutor busInstance;

    public ThreadPoolExecutor getBusThreadPool() {
        if (Objects.isNull(busInstance)) {
            busInstance = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        }
        return busInstance;
    }
}
