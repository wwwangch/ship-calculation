package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConstants;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.KcNamespace;
import com.iscas.common.tools.core.date.DateSafeUtils;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.NamespaceStatus;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 15:59
 * @since jdk1.8
 */
public class KcNamespaceUtils {
    private KcNamespaceUtils() {}

    private static KcNamespace getOneNs(Namespace ns) throws K8sClientException {
        KcNamespace kcNamespace = new KcNamespace();

        String name = null;
        String apiVersion;
        String status = null;
        Date createTime = null;
        String runTimeStr = null;

        //注入值
        apiVersion = ns.getApiVersion();
        ObjectMeta metadata = ns.getMetadata();
        if (metadata != null) {
            name = metadata.getName();
            String creationTimestamp = metadata.getCreationTimestamp();
            try {
                createTime  = DateSafeUtils.parse(creationTimestamp, K8sConstants.TIME_PATTERN);
                createTime = CommonUtils.timeOffset(createTime);
                runTimeStr = CommonUtils.getTimeDistance(createTime);
            } catch (ParseException e) {
                throw new K8sClientException("创建时间类型转换出错", e);
            }

        }
        NamespaceStatus nsStatus = ns.getStatus();
        if (nsStatus != null) {
            status = nsStatus.getPhase();
        }

        kcNamespace.setApiVersion(apiVersion)
                .setCreateTime(createTime)
                .setName(name)
                .setRunTimeStr(runTimeStr)
                .setStatus(status);
        return kcNamespace;
    }

    /**
     * 获取当前的命名空间
     * */
    public static List<KcNamespace> getNamespaces() throws K8sClientException {
        List<KcNamespace> kcNamespaces = null;
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        NonNamespaceOperation<Namespace, NamespaceList, Resource<Namespace>> namespaces = kc.namespaces();
        if (namespaces != null) {
            NamespaceList namespaceList = namespaces.list();
            if (namespaceList != null) {
                List<Namespace> items = namespaceList.getItems();
                if (CollectionUtils.isNotEmpty(items)) {
                    kcNamespaces = new ArrayList<>();
                    for (Namespace ns : items) {
                        KcNamespace kcNamespace = getOneNs(ns);
                        kcNamespaces.add(kcNamespace);
                    }
                }
            }
        }

        return kcNamespaces;
    }

    /**
     * 按照命名空间名获取命名空间
     * */
    public static KcNamespace getNamespace(String nsName) throws K8sClientException {
        KcNamespace kcNamespace = null;
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        NonNamespaceOperation<Namespace, NamespaceList, Resource<Namespace>> namespaces = kc.namespaces();
        if (namespaces != null) {
            Resource<Namespace> nsResource = namespaces.withName(nsName);
            if (nsResource != null) {
                Namespace ns = nsResource.get();
                if (ns != null) {
                    kcNamespace = getOneNs(ns);
                }
            }
        }
        return kcNamespace;
    }

    /**
     * 创建一个命名空间
     */
    public static void createNamespace(String nsName) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        Namespace namespace = new Namespace();
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(nsName);
        namespace.setMetadata(objectMeta);
        kc.namespaces().create(namespace);
    }

    /**
     * 删除一个命名空间
     */
    public static void deleteNamespace(String nsName) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        Resource<Namespace> nsResource = kc.namespaces().withName(nsName);
        if (nsResource != null) {
            nsResource.delete();
        }
    }
}
