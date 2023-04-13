package com.iscas.common.web.tools.json;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/9 11:11
 * @since jdk1.8
 */
@SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal", "unused"})
public class JsonObject implements Json {
    /**初始容量*/
    private final int initialCapacity = 16;

    private Map<String, Object> data ;

    public JsonObject() {
        data = new LinkedHashMap<>(initialCapacity);
    }
    public JsonObject(int capacity) {
        data = new LinkedHashMap<>(capacity);
    }
    public Map<String, Object> toMap() {
        return data;
    }

    @Override
    public String toJson() {
        return data == null ? null : JsonUtils.toJson(data);
    }

    public <T> T fromJson(Class<T> tClass) {
        return JsonUtils.fromJson(toJson(), tClass);
    }

    /**
     * <p>向JsonObject中注入值</p>
     * @since jdk1.8
     * @date 2019/5/9
     * @param key 键
     * @param value 值 支持JsonArray、JsonObject、List、Map、JavaBean
     * @return com.iscas.common.web.tools.json.JsonObject
     */
    public JsonObject set(String key, Object value) {
        if (value != null) {
            data.put(key, JsonUtils.convertValue(value));
        }
        return this;
    }

}
