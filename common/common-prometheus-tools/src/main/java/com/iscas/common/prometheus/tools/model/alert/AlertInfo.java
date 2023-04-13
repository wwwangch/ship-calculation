package com.iscas.common.prometheus.tools.model.alert;

import com.iscas.common.prometheus.tools.model.Annotation;
import com.iscas.common.prometheus.tools.model.Label;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 从alertmanager获取的告警信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/4/6 9:02
 */
@Data
public class AlertInfo implements Serializable {

    /**
     * annotation
     */
    private List<Annotation> annotations;

    /**
     * endsAt
     */
    private LocalDateTime endsAt;

    /**
     * fingerprint
     */
    private String fingerprint;

    /**
     * receivers
     */
    private List<Map<String, Object>> receivers;

    /**
     * startsAt
     */
    private LocalDateTime startsAt;

    /**
     * 状态
     */
    private AlertInfoStatus status;

    /**
     * updatedAt
     * */
    private LocalDateTime updatedAt;

    /**
     * generatorURL
     * */
    private String generatorURL;

    /**
     * labels
     * */
    private List<Label> labels;

}
