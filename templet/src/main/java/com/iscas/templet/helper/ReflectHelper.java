package com.iscas.templet.helper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/29 14:27
 * @since jdk1.8
 */
public class ReflectHelper {
    private ReflectHelper() {}

    public static Field[] getFieldsDirectly(Class<?> beanClass, String... ignoreFields) {
        Field[] fields = new Field[0];
        ignoreFields = ignoreFields == null ? new String[0] : ignoreFields;
        String[] finalIgnoreFields = ignoreFields;
        while (beanClass != Object.class) {
            Field[] declaredFields = beanClass.getDeclaredFields();
            declaredFields = Arrays.stream(declaredFields).filter(field -> !contains(finalIgnoreFields, field)).toArray(Field[]::new);
            if (isNotEmpty(declaredFields)) {
                fields = addAll(fields, declaredFields);
            }
            beanClass = beanClass.getSuperclass();
        }
        // 如果有名字重复的，只保留子类的
        Set<String> fieldNames = new HashSet<>();
        return Arrays.stream(fields).filter(field -> {
            boolean contains = fieldNames.contains(field.getName());
            fieldNames.add(field.getName());
            return !contains;
        }).toArray(Field[]::new);
    }

    private static <T> boolean isNotEmpty(T[] t) {
        return t != null && t.length > 0;
    }

    private static <T> boolean contains(T[] ts, T t) {
        if (ts != null) {
            return Arrays.stream(ts).anyMatch(o -> Objects.equals(t, o));
        }
        return false;
    }

    private static Field[] addAll(Field[] main, Field[] add) {
        Field[] res = new Field[main.length + add.length];
        System.arraycopy(main, 0, res, 0, main.length);
        for (int i = main.length; i < res.length; i++) {
            res[i] = add[i - main.length];
        }
        return res;
    }
}
