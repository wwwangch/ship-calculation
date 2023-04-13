package com.iscas.common.web.tools.json;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/9 11:12
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "FieldCanBeLocal", "FieldMayBeFinal"})
public class JsonArray implements Json {
    private int capacity = 12;
    private List<Object> list;

    public JsonArray() {
        list = new ArrayList<>(capacity);
    }

    public JsonArray(int capacity) {
        list = new ArrayList<>(capacity);
    }

    public List<Object> toList() {
        return list;
    }

    @Override
    public String toJson() {
        return JsonUtils.toJson(list);
    }

    public JsonArray add(Object value) {
        if (value != null) {
            list.add(JsonUtils.convertValue(value));
        }
        return this;
    }

    public JsonArray set(int index, Object value) {
        if (value != null) {
            list.set(index, JsonUtils.convertValue(value));
        }
        return this;
    }

}
