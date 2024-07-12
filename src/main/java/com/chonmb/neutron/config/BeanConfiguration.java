package com.chonmb.neutron.config;

import java.util.Properties;

@EventConfiguration("")
public class BeanConfiguration implements Configuration{
    public String scanPackage="com.chonmb.neutron";
    public String map="222";

    @Override
    public String toString() {
        return "BeanConfiguration{" +
                "scanPackage='" + scanPackage + '\'' +
                ", map='" + map + '\'' +
                '}';
    }
}
