package com.iscas.common.prometheus.tools.model.alert;

import lombok.Data;

import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/4/6 9:06
 */
@Data
public class AlertInfoStatus {
    /**
     * 被抑制
     * */
    private List<String> inhibitedBy;

    /**
     * silencedBy
     * */
    private List<String> silencedBy;

    /**
     * 状态
     * */
    private String state;
}
