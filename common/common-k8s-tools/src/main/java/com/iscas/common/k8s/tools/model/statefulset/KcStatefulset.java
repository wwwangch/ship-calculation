package com.iscas.common.k8s.tools.model.statefulset;

import com.iscas.common.k8s.tools.model.deployment.KcDeployment;
import com.iscas.common.k8s.tools.model.volume.KcVolumeClaimTemplate;
import io.fabric8.kubernetes.api.model.apps.StatefulSet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Statefulset
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/8 17:39
 * @since jdk1.8
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class KcStatefulset extends KcDeployment {

    private StatefulSet statefulSetItem;

    /**存储卷声明*/
    private List<KcVolumeClaimTemplate> volumeClaimTemplates;
}
