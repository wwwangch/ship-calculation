package com.iscas.common.etcd.tools.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/28 9:02
 * @since jdk1.8
 */
public class ConfigUtils {
    private static final String CLASSPATH = "classpath:";

    private ConfigUtils() {}

    public static InputStream getConfigIs(String path) throws IOException {
        if (path.startsWith(CLASSPATH)) {
            return ConfigUtils.class.getClassLoader().getResourceAsStream(path.substring(10));
        } else {
            return Files.newInputStream(Paths.get(path));
        }
    }
}
