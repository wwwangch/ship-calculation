package com.iscas.templet.helper;

import com.iscas.templet.view.table.ComboboxData;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 下拉列表操作的帮助工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/16 18:14
 * @since jdk11
 */
@SuppressWarnings("unused")
public class ComboboxHelper {
    private ComboboxHelper() {
    }

    /**
     * 生成下拉列表
     *
     * @param data      数据
     * @param valueKey     数据实体或map需要映射为combobox的value的属性key
     * @param labelKey  数据实体或map需要映射为comboboc的label的属性key
     * @return List
     * @date 2022/6/16
     * @since jdk11
     */
    public static <T> List<ComboboxData<T>> createCombobox(List<T> data, String valueKey, String labelKey) {
        Map<String, Field> fieldCache = new HashMap<>(4);
        if (!Objects.isNull(data)) {
            return data.stream().map(datum -> {
                Object value = CommonHelper.getVal(datum, valueKey, fieldCache);
                Object labelObj = CommonHelper.getVal(datum, labelKey, fieldCache);
                String label = Objects.isNull(labelObj) ? null : String.valueOf(labelObj);
                return new ComboboxData<T>().setValue(value).setLabel(label).setData(datum);
            }).toList();
        }
        return new ArrayList<>();
    }


}
