package com.iscas.base.biz.test.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.iscas.common.web.tools.json.JsonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/6 14:04
 * @since jdk11
 */
public class CustomSerialize extends JsonSerializer<String> implements ContextualSerializer {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            TypeReference<List<String>> typeReference = new TypeReference<>() {};
            gen.writeObject(JsonUtils.fromJson(value, typeReference));
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        //判断beanProperty是不是空
        if (property == null){
            return prov.findNullValueSerializer(property);
        }
        //判断类型是否是String
        if (Objects.equals(property.getType().getRawClass(),String.class)){
            CustomStrFormatter annotation = property.getAnnotation(CustomStrFormatter.class);
            if (annotation != null){
                // 这里可以获取注解中的一些参数
                String pattern = annotation.pattern();
                return this;
            }
        }
        return prov.findValueSerializer (property.getType (), property);
    }
}
