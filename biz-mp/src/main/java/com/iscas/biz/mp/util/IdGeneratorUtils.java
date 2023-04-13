package com.iscas.biz.mp.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.iscas.common.tools.core.reflect.ReflectUtils;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author admin
 */
@SuppressWarnings("rawtypes")
public class IdGeneratorUtils {
    private IdGeneratorUtils() {
    }

    public static String generator(Class clazz, String primaryKey) {
        Field declaredField = ReflectUtils.getCurrentField(clazz, primaryKey);
        if (ReflectUtils.isAnnotationPresent(declaredField, TableId.class)) {
            TableId annotation = declaredField.getAnnotation(TableId.class);
            IdType type = annotation.type();
            if (Objects.equals(type.name(), IdType.ASSIGN_ID.name())) {
                return IdWorker.getIdStr();
            }
        }
        return null;
    }
}
