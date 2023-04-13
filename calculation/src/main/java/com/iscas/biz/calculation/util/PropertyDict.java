package com.iscas.biz.calculation.util;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author ch w
 * @version 1.0
 * @since 2023/4/12 10:22
 */
public class PropertyDict {
    private static final Map<String, String> PROPERTY_MAP = Maps.newHashMap();

    static {
        // 定义中文属性名到英文属性名的映射表
        PROPERTY_MAP.put("[主尺度配置]船舶类型", "shipType");
        PROPERTY_MAP.put("[主尺度配置]设计水线长", "waterlineLength");
        PROPERTY_MAP.put("[主尺度配置]型宽", "mouldedBreadth");
        PROPERTY_MAP.put("[主尺度配置]型深", "mouldedDepth");
        PROPERTY_MAP.put("[主尺度配置]吃水", "designedDraft");
        PROPERTY_MAP.put("[主尺度配置]站数", "station");
        PROPERTY_MAP.put("[航行区域]区域", "navigationArea");
        PROPERTY_MAP.put("[极限波浪工况]排水量", "extremeDisplacement");
        PROPERTY_MAP.put("[极限波浪工况]重心纵向位置", "extremePortraitGravity");
        PROPERTY_MAP.put("[巡航工况]排水量", "cruisingDisplacement");
        PROPERTY_MAP.put("[巡航工况]重心纵向位置", "cruisingPortraitGravity");
        PROPERTY_MAP.put("[校核准则]准则编码", "standardCode");
    }
}
