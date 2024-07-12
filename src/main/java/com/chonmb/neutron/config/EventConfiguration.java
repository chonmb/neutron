package com.chonmb.neutron.config;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventConfiguration {
    String value() default "";
}
