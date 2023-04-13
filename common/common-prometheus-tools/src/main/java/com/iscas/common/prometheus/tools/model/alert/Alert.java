package com.iscas.common.prometheus.tools.model.alert;

import com.iscas.common.prometheus.tools.model.Annotation;
import com.iscas.common.prometheus.tools.model.Label;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 告警
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/31 15:22
 */
@Data
public class Alert implements Serializable {

    /**
     * 标签
     */
    private List<Label> labels;

    /**
     * 注解
     * */
    private List<Annotation> annotations;

    /**
     * 状态
     * */
    private String state;

    /**
     * 时间
     * */
    private LocalDateTime activeAt;

    /**
     * 值
     * */
    private String value;

}
