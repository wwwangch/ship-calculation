package com.iscas.base.biz.test.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.iscas.common.web.tools.json.JsonUtils;
import com.iscas.templet.exception.Exceptions;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/6/6 14:04
 * @since jdk11
 */
public class CustomDeserialize extends JsonDeserializer<String> implements ContextualDeserializer {


    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) {
        try {
            if (p != null && StringUtils.isNotEmpty(p.getText())) {
                List<String> strs = new ArrayList<>();
                JsonToken jsonToken;
                while (!p.isClosed() && (jsonToken = p.nextToken()) != null && !JsonToken.FIELD_NAME.equals(jsonToken) &&
                        !JsonToken.END_ARRAY.equals(jsonToken)) {
                    strs.add(p.getValueAsString());
                }
                return JsonUtils.toJson(strs);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw Exceptions.runtimeException(e);
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        //判断beanProperty是不是空
        if (property == null) {
            return ctxt.findNonContextualValueDeserializer(property.getType());
        }
        //判断类型是否是String
        if (Objects.equals(property.getType().getRawClass(), String.class)) {
            CustomStrFormatter annotation = property.getAnnotation(CustomStrFormatter.class);
            if (annotation != null) {
                // 这里可以获取注解中的一些参数
                String pattern = annotation.pattern();
                return this;
            }
        }
        return ctxt.findContextualValueDeserializer(property.getType(), property);
    }
}
