package com.chonmb.neutron.beans;

import java.lang.annotation.*;

/**
 * @author chonmb
 * @date 2024/7/24 15:29
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventScanner {
    String[] scanPages() default {};
}
