package com.iscas.common.prometheus.tools.model.target;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/3/31 10:45
 *
 */
@Data
public class TargetResponse implements Serializable {
    private List<Target> activeTargets;
    private List<Target> droppedTargets;
}
