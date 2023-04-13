package com.iscas.common.tools.core.io.file;

import lombok.Cleanup;

import java.io.*;
import java.util.Properties;

/**
 * 配置文件读取工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/10/20 14:30
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class ConfigUtils {
    private ConfigUtils() {
    }

    private static final String DEFUALT_PROPERTIES_NAME = "config.properties";

    public static String readProp(String key) throws RuntimeException {
        return readProp(DEFUALT_PROPERTIES_NAME, key);
    }

    public static String readProp(String fileName, String key) throws RuntimeException {
        try {
            @Cleanup InputStream is = ConfigUtils.class.getClassLoader().getResourceAsStream(fileName);
            Properties properties = new Properties();
            properties.load(is);
            return properties.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(String.format("从配置文件:[%s]读取配置参数:[%s]出错", fileName, key), e);
        }
    }

    /**
     * 读取jar包外部或内部的配置文件
     * `
     */
    public static InputStream getInOutConfigStream(String uri) throws FileNotFoundException {
        String filePath = System.getProperty("user.dir") + uri;
        File file = new File(filePath);
        return file.exists() ? new FileInputStream(file) : ConfigUtils.class.getResourceAsStream(uri);
    }
}
