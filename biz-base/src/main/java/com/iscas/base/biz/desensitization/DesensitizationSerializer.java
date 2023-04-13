package com.iscas.base.biz.desensitization;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Objects;

/**
 * 脱敏序列化类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/1/5 9:24
 * @since jdk1.8
 */
@AllArgsConstructor
@NoArgsConstructor
public class DesensitizationSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private Desensitization desensitization;

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(desensitize(s));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                Desensitization desensitization = beanProperty.getAnnotation(Desensitization.class);
                if (desensitization == null) {
                    desensitization = beanProperty.getContextAnnotation(Desensitization.class);
                }
                if (desensitization != null) {
                    return new DesensitizationSerializer(desensitization);
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }

    /**
     * 脱敏处理
     */
    private String desensitize(String s) {
        if (StrUtil.isNotBlank(s)) {
            PrivacyTypeEnum dataType = desensitization.dataType();
            DesensitizationTypeEnum mode = desensitization.mode();
            return switch (mode) {
                // 默认方式，根据dataType自动选择脱敏方式
                case DEFAULT -> autoDesensitize(s, dataType);
                // 头部脱敏
                case HEAD -> headDesensitize(s);
                // 尾部脱敏
                case TAIL -> tailDesensitize(s);
                // 中间脱敏
                case MIDDLE -> middleDesensitize(s);
                // 头尾脱敏
                case HEAD_TAIL -> headTailDesensitize(s);
                // 全部脱敏
                case ALL -> allDesensitize(s);
                // 不脱敏
                case NONE -> s;
            };
        }
        return s;
    }


    /**
     * 全部脱敏
     */
    private String allDesensitize(String s) {
        return String.valueOf(desensitization.maskCode()).repeat(s.length());
    }

    /**
     * 头尾脱敏
     */
    private String headTailDesensitize(String s) {
        int middleNoMaskLen = desensitization.middleNoMaskLen();
        if (middleNoMaskLen >= s.length()) {
            // 如果中间不脱敏的长度大于等于字符串的长度，不进行脱敏
            return s;
        }
        int len = s.length() - middleNoMaskLen;
        // 头部脱敏
        int headStart = 0;
        int headEnd = len / 2;
        s = StrUtil.replace(s, headStart, headEnd, desensitization.maskCode());
        // 尾部脱敏
        int tailStart = s.length() - (len - len / 2);
        int tailEnd = s.length();
        return StrUtil.replace(s, tailStart, tailEnd, desensitization.maskCode());
    }

    /**
     * 中间脱敏
     */
    private String middleDesensitize(String s) {
        int headNoMaskLen = desensitization.headNoMaskLen();
        int tailNoMaskLen = desensitization.tailNoMaskLen();
        if (headNoMaskLen + tailNoMaskLen >= s.length()) {
            // 如果头部不脱敏的长度+尾部不脱敏长度 大于等于字符串的长度，不进行脱敏
            return s;
        }
        int start = headNoMaskLen;
        int end = s.length() - tailNoMaskLen;
        return StrUtil.replace(s, start, end, desensitization.maskCode());
    }

    /**
     * 尾部脱敏
     */
    private String tailDesensitize(String s) {
        int headNoMaskLen = desensitization.headNoMaskLen();
        if (headNoMaskLen >= s.length()) {
            // 如果头部不脱敏的长度大于等于字符串的长度，不进行脱敏
            return s;
        }
        int start = headNoMaskLen;
        int end = s.length();
        return StrUtil.replace(s, start, end, desensitization.maskCode());
    }

    /**
     * 头部脱敏
     */
    private String headDesensitize(String s) {
        int tailNoMaskLen = desensitization.tailNoMaskLen();
        if (tailNoMaskLen >= s.length()) {
            // 如果尾部不脱敏的长度大于等于字符串的长度，不进行脱敏
            return s;
        }
        int start = 0;
        int end = s.length() - tailNoMaskLen;
        return StrUtil.replace(s, start, end, desensitization.maskCode());
    }


    /**
     * 根据数据类型自动脱敏
     */
    private String autoDesensitize(String s, PrivacyTypeEnum dataType) {
        return switch (dataType) {
            case CHINESE_NAME -> DesensitizedUtil.chineseName(s);
            case FIXED_PHONE -> DesensitizedUtil.fixedPhone(s);
            case MOBILE_PHONE -> DesensitizedUtil.mobilePhone(s);
            case ADDRESS -> DesensitizedUtil.address(s, 8);
            case PASSWORD -> DesensitizedUtil.password(s);
            case BANK_CARD -> DesensitizedUtil.bankCard(s);
            case EMAIL -> DesensitizedUtil.email(s);
            case ID_CARD -> DesensitizedUtil.idCardNum(s, 1, 2);
            // 其他类型的不支持以默认方式脱敏，直接返回
            case OTHER -> s;
        };
    }
}
