package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import io.fabric8.kubernetes.api.model.ConfigMap;
import io.fabric8.kubernetes.api.model.ConfigMapList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ConfigMap;
import io.kubernetes.client.openapi.models.V1ConfigMapList;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.util.Yaml;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * configmap相关操作
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/10 8:43
 * @since jdk1.8
 */
@SuppressWarnings("unchecked")
public class KcConfigmapUtils {
    private KcConfigmapUtils() {}

    public static Map<String, String> getConfigMapData(String namespace, String name) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        ConfigMap configMap = kc.configMaps().inNamespace(namespace)
                .withName(name).get();
        return configMap.getData();
    }

    public static List<ConfigMap> getConfigMaps(String namespace) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        ConfigMapList list = kc.configMaps().inNamespace(namespace).list();
        if (list != null) {
            return list.getItems();
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 获取配置字典的yaml
     * */
    public static String yaml(ApiClient apiClient, String namespace, String name) throws ApiException, K8sClientException {
        CoreV1Api coreApi = new CoreV1Api(apiClient);
        V1ConfigMapList configMapList = coreApi.listNamespacedConfigMap(namespace, "true", false, null, null, null, 100,
                null, 20, null);
        if (configMapList != null) {
            List<V1ConfigMap> items = configMapList.getItems();
            if (CollectionUtils.isNotEmpty(items)) {
                for (V1ConfigMap item : items) {
                    V1ObjectMeta metadata = item.getMetadata();
                    assert metadata != null;
                    String name1 = metadata.getName();
                    if (Objects.equals(name, name1)) {
                        String yaml = Yaml.dump(item);
                        return YamlUtils.formatToYaml(yaml);
                    }
                }
            }
        }
        throw new K8sClientException(String.format("未获取到配置字典:[%s]", name));
    }


}
