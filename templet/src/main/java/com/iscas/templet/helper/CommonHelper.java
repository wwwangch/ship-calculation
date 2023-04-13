package com.iscas.templet.helper;

import com.iscas.templet.exception.Exceptions;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/16 18:45
 * @since jdk11
 */
public class CommonHelper {
    private CommonHelper() {}

    static Object getVal(Object t, String key, Map<String, Field> fieldCache) {
        if (Objects.isNull(t)) {
            return null;
        } else if (t instanceof Map) {
            return ((Map<?, ?>) t).get(key);
        } else {
            try {
                return fieldCache.computeIfAbsent(key, k -> {
                    try {
                        Field declaredField = t.getClass().getDeclaredField(key);
                        declaredField.setAccessible(true);
                        return declaredField;
                    } catch (NoSuchFieldException e) {
                        throw Exceptions.runtimeException(e);
                    }
                }).get(t);
            } catch (IllegalAccessException e) {
                throw Exceptions.baseRuntimeException("获取实体属性出错", e);
            }
        }
    }
}
