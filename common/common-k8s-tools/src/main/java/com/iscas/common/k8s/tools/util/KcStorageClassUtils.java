package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.storageclass.KcStorageClass;
import com.iscas.templet.exception.BaseException;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.storage.StorageClass;
import io.fabric8.kubernetes.api.model.storage.StorageClassList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.StorageAPIGroupDSL;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.StorageV1Api;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1StorageClass;
import io.kubernetes.client.openapi.models.V1StorageClassList;
import io.kubernetes.client.util.Yaml;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 存储资源管理Utils
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/15 11:06
 * @since jdk1.8
 */
public class KcStorageClassUtils {
    private KcStorageClassUtils() {}

    /**
     * 获取存储资源的yaml
     * */
    public static String yaml(ApiClient apiClient, String name) throws ApiException, K8sClientException {

        StorageV1Api storageV1Api = new StorageV1Api(apiClient);
        V1StorageClassList storageClassList = storageV1Api.listStorageClass("true", false, null, null, null, 100,
                null, 20, null);
        if (storageClassList != null) {
            List<V1StorageClass> items = storageClassList.getItems();
            if (CollectionUtils.isNotEmpty(items)) {
                for (V1StorageClass item : items) {
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
        throw new K8sClientException(String.format("未获取到存储资源:[%s]", name));
    }

    /**
     * 获取存储资源
     * */
    public static List<KcStorageClass> listAll() {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        StorageAPIGroupDSL storage = kc.storage();
        List<KcStorageClass> storageClasses = new ArrayList<>();
        if (storage != null) {
            StorageClassList storageClassList = storage.storageClasses().list();
            if (storageClassList != null) {
                List<StorageClass> items = storageClassList.getItems();
                if (CollectionUtils.isNotEmpty(items)) {
                    for (StorageClass item : items) {
                        ObjectMeta metadata = item.getMetadata();
                        KcStorageClass kcStorageClass = new KcStorageClass();
                        kcStorageClass.setName(metadata.getName())
                                .setProvisioner(item.getProvisioner())
                                .setReclaimPolicy(item.getReclaimPolicy())
                                .setVolumeBindingMode(item.getVolumeBindingMode());
                        storageClasses.add(kcStorageClass);
                    }
                }
            }
        }
        return storageClasses;
    }


    /**
     * 创建存储资源
     * */
    public static void createStorageClass(KcStorageClass kcStorageClass) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        StorageClass storageClass = new StorageClass();
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(kcStorageClass.getName());
        storageClass.setMetadata(objectMeta);
        storageClass.setProvisioner(kcStorageClass.getProvisioner());
        storageClass.setReclaimPolicy(kcStorageClass.getReclaimPolicy());
        storageClass.setVolumeBindingMode(kcStorageClass.getVolumeBindingMode());
        kc.storage().storageClasses().createOrReplace(storageClass);
    }

    public static void deleteStorageClass(String name) throws BaseException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        StorageAPIGroupDSL storage = kc.storage();
        if (storage != null) {
            Resource<StorageClass> storageClassResource = storage.storageClasses().withName(name);
            if (storageClassResource != null) {
                StorageClass storageClass = storageClassResource.get();
                Map<String, String> annotations = storageClass.getMetadata().getAnnotations();
                if (MapUtils.isNotEmpty(annotations)) {
                    if (Objects.equals(annotations.get("storageclass.kubernetes.io/is-default-class"), "true")) {
                        throw new BaseException("默认的存储资源不允许删除");
                    }
                }
                storageClassResource.delete();
            }
        }
    }
}
