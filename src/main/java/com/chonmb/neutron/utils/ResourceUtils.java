package com.chonmb.neutron.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author chonmb
 * @date 2024/7/25 8:57
 */

public class ResourceUtils {
    private ResourceUtils(){
    }
    public static InputStream loadResourceAsStream(String resource) throws IOException {
        return Objects.requireNonNull(ResourceUtils.class.getClassLoader().getResource(resource)).openStream();
    }
}
