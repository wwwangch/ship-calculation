package com.iscas.rule.engine.util;

import com.iscas.rule.engine.exception.RuleException;
import lombok.Cleanup;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/10/20 14:30
 * @since jdk1.8
 */
@SuppressWarnings("JavadocDeclaration")
public class ConfigUtils {
    private ConfigUtils() {}

    private static final String DEFUALT_PROPERTIES_NAME = "config.properties";

    public static String readProp(String key) throws RuleException {
        return readProp(DEFUALT_PROPERTIES_NAME, key);
    }

    public static String readProp(String fileName, String key) throws RuleException {
        try {
            @Cleanup InputStream is = ConfigUtils.class.getClassLoader().getResourceAsStream(fileName);
            Properties properties = new Properties();
            properties.load(is);
            return properties.getProperty(key);
        } catch (IOException e) {
            throw new RuleException(String.format("从配置文件:[%s]读取配置参数:[%s]出错", fileName, key), e);
        }
    }
}
