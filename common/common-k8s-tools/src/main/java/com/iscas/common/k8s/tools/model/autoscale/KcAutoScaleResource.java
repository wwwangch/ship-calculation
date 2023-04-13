package com.iscas.common.k8s.tools.model.autoscale;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 自动扩容资源
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/22 10:36
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class KcAutoScaleResource {

    /**
     * 暂时默认使用这个
     * */
    private String type = "Utilization";
    /**
     * 扩容的阈值(1-100的百分比)
     * */
    private int averageUtilization;

    /**
     * 当前使用量-只做展示
     * */
    private String currentAverageValue = "未知";

    /**
     * 当前使用百分比(按资源限制算)(0-100的百分比)-只做展示
     * */
    private String currentAverageUtilization = "未知";

}
