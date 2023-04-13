package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConstants;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.KcContainer;
import com.iscas.common.k8s.tools.model.KcRuntimeInfo;
import com.iscas.common.k8s.tools.model.daemonset.KcDaemonset;
import com.iscas.common.k8s.tools.model.deployment.KcDepBaseInfo;
import com.iscas.common.k8s.tools.model.volume.KcVolume;
import com.iscas.common.k8s.tools.model.volume.KcVolumeClaimTemplate;
import com.iscas.common.tools.core.date.DateSafeUtils;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apps.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.*;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.*;

/**
 * 守护服务工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/10/27
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "unchecked", "DuplicatedCode"})
public class KcDaemonsetUtils {
    private KcDaemonsetUtils() {}

    /**
     * 获取daemonset的信息
     * @since jdk1.8
     * @date 2021/10/27
     * @param namespace 命名空间
     * @throws K8sClientException k8s异常
     */
    public static List<KcDaemonset> getDaemonset(String namespace) throws K8sClientException {

        List<KcDaemonset> kcDaemonsets = null;
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        AppsAPIGroupDSL apps = kc.apps();
        if (apps != null) {
            NonNamespaceOperation<DaemonSet, DaemonSetList, Resource<DaemonSet>> daemonsets = apps.daemonSets().inNamespace(namespace);
            if (daemonsets != null) {
                DaemonSetList daemonsetList = daemonsets.list();
                if (daemonsetList != null) {
                    List<DaemonSet> items = daemonsetList.getItems();
                    if (CollectionUtils.isNotEmpty(items)) {
                        kcDaemonsets = new ArrayList<>();
                        for (DaemonSet item : items) {
                            String name = null;
                            Integer currentRepSum = null;
                            Integer planRepSum = null;
                            String runtimeStr = null;
                            List<KcRuntimeInfo> runtimeInfos;
                            KcDepBaseInfo baseInfo;

                            KcDaemonset kcDaemonset = new KcDaemonset();

                            ObjectMeta metadata = item.getMetadata();
                            if (metadata != null) {
                                //获取name
                                name = metadata.getName();
                                //获取运行时间
                                runtimeStr = CommonUtils.getRuntimeStr(metadata);
                            }

                            DaemonSetStatus status = item.getStatus();
                            if (status != null) {
                                planRepSum = status.getDesiredNumberScheduled();
                                currentRepSum = status.getNumberReady();
                            }

                            //获取基本信息
                            baseInfo = setBaseInfo(item);

                            setVolumns(item, kcDaemonset);

                            //获取运行时信息
                            runtimeInfos = setRuntimeInfo(item);

                            kcDaemonset.setCurrentRepSum(currentRepSum)
                                    .setName(name)
                                    .setPlanRepSum(planRepSum)
                                    .setRuntimeStr(runtimeStr)
                                    .setBaseInfo(baseInfo)
                                    .setRuntimeInfos(runtimeInfos);
                            kcDaemonset.setDaemonsetItem(item);

                            //获取存储卷声明
                            kcDaemonsets.add(kcDaemonset);
                        }
                    }
                }
            }
        }
        return kcDaemonsets;
    }


    /**
     * 创建一个Daemonset
     * */
    public static void createDaemonset(KcDaemonset kcDaemonset) throws K8sClientException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        KcDepBaseInfo baseInfo = kcDaemonset.getBaseInfo();
        List<KcVolume> volumes = kcDaemonset.getVolumes();

        //命名空间
        String namespace  = baseInfo.getNamespace();
        String name = baseInfo.getName();

        Map<String, String> labelMap = KcDeploymentUtils.getLabelMap(baseInfo);
        Map<String, String> annotationMap = KcDeploymentUtils.getAnnotationMap(baseInfo);

        List<KcContainer> initContainers = kcDaemonset.getInitContainer();
        List<KcContainer> containers = kcDaemonset.getContainers();
        String imagePullSecret = kcDaemonset.getImagePullSecret();

        AppsAPIGroupDSL apps = kc.apps();
        if (apps == null) {
            throw new K8sClientException("获取不到apps，无法新建Daemonset");
        }
        Namespace ns = kc.namespaces().withName(namespace).get();
        if (ns == null) {
            throw new K8sClientException("获取不到命名空间，无法新建Daemonset");
        }

        DaemonSet daemonset = new DaemonSet();
        //meta
        ObjectMeta objectMeta = KcDeploymentUtils.createObjectMeta(name, namespace, labelMap, annotationMap);
        daemonset.setMetadata(objectMeta);

        //spec
        DaemonSetSpec daemonsetSpec = new DaemonSetSpec();

        //selector
        LabelSelector labelSelector = new LabelSelector();
        labelSelector.setMatchLabels(labelMap);
        daemonsetSpec.setSelector(labelSelector);

        //template
        PodTemplateSpec podTemplateSpec = new PodTemplateSpec();

        //template-metaData
        ObjectMeta templateMeta = new ObjectMeta();
        templateMeta.setLabels(labelMap);
        templateMeta.setAnnotations(annotationMap);
        podTemplateSpec.setMetadata(templateMeta);

        //template-spec
        PodSpec podSpec = new PodSpec();

        //template-spec-镜像密钥
        if (StringUtils.isNotEmpty(imagePullSecret)) {
            LocalObjectReference localObjectReference = new LocalObjectReference();
            localObjectReference.setName(imagePullSecret);
            podSpec.setImagePullSecrets(Collections.singletonList(localObjectReference));
        }

        //template-spec-init container
        List<Container> initPodContainers = new ArrayList<>();
        if (initContainers != null) {
            //构建初始化container
            for (KcContainer initContainer : initContainers) {
                Container container = new Container();
                initPodContainers.add(container);
                KcDeploymentUtils.setContainer(initContainer, container);
            }
        }
        podSpec.setInitContainers(initPodContainers);

        List<Container> podContainers = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(containers)) {
            //构建镜像
            for (KcContainer container : containers) {
                Container podContainner = new Container();
                podContainers.add(podContainner);
                KcDeploymentUtils.setContainer(container, podContainner);
            }
        }
        podSpec.setContainers(podContainers);

        //存储卷
        podSpec.setVolumes(KcDeploymentUtils.createVolume(volumes));

        podTemplateSpec.setSpec(podSpec);

        daemonsetSpec.setTemplate(podTemplateSpec);

        daemonset.setSpec(daemonsetSpec);

        Resource<DaemonSet> daemonSetResource = apps.daemonSets().inNamespace(namespace).withName(name);
        if (daemonSetResource.get() != null) {
            //编辑
            daemonSetResource.edit(n -> daemonset);
        } else {
            //新增
            apps.daemonSets().inNamespace(namespace).create(daemonset);
        }
    }

    public static void deleteDaemonset(String namespace, String name) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        AppsAPIGroupDSL apps = kc.apps();
        if (apps != null) {
            NonNamespaceOperation<DaemonSet, DaemonSetList, Resource<DaemonSet>> daemonsetResourceOperation = apps.daemonSets().inNamespace(namespace);
            if (daemonsetResourceOperation != null) {
                Resource<DaemonSet> daemonSetResource = daemonsetResourceOperation.withName(name);
                if (daemonSetResource != null && daemonSetResource.get() != null) {
                    daemonSetResource.delete();
                }
            }
        }
    }


    private static List<KcVolumeClaimTemplate> setVolumeClaimTemplate(StatefulSet statefulSet) {
        List<KcVolumeClaimTemplate> kcVolumeClaimTemplates = new ArrayList<>();
        StatefulSetSpec spec = statefulSet.getSpec();
        List<PersistentVolumeClaim> volumeClaimTemplates = spec.getVolumeClaimTemplates();
        if (volumeClaimTemplates != null) {
            for (PersistentVolumeClaim volumeClaimTemplate : volumeClaimTemplates) {
                KcVolumeClaimTemplate kcVolumeClaimTemplate = new KcVolumeClaimTemplate();
                ObjectMeta metadata = volumeClaimTemplate.getMetadata();
                kcVolumeClaimTemplate.setName(metadata.getName());
                PersistentVolumeClaimSpec spec1 = volumeClaimTemplate.getSpec();
                List<String> accessModes = spec1.getAccessModes();
                kcVolumeClaimTemplate.setAccessModes(accessModes);
                String storageClassName = spec1.getStorageClassName();
                kcVolumeClaimTemplate.setStorageClass(storageClassName);
                ResourceRequirements resources = spec1.getResources();
                if (resources != null) {
                    Map<String, Quantity> requests = resources.getRequests();
                    if (requests != null) {
                        Quantity storage = requests.get("storage");
                        //todo
                        kcVolumeClaimTemplate.setStorage(Double.parseDouble(storage.getAmount()));
                    }

                }
                kcVolumeClaimTemplates.add(kcVolumeClaimTemplate);
            }
        }
        return kcVolumeClaimTemplates;
    }


    /**
     * 设置运行时信息
     * */
    private static List<KcRuntimeInfo> setRuntimeInfo(DaemonSet daemonset) throws K8sClientException {
        List<KcRuntimeInfo> kcConditions = null;
        DaemonSetStatus depStatus = daemonset.getStatus();
        if (depStatus != null) {
            List<DaemonSetCondition> conditions = depStatus.getConditions();
            if (CollectionUtils.isNotEmpty(conditions)) {
                kcConditions  = new ArrayList<>();
                for (DaemonSetCondition condition : conditions) {
                    KcRuntimeInfo kcCondtion = new KcRuntimeInfo();
                    String type;
                    String status;
                    Date lastUpdateTime;
                    Date lastTransationTime;
                    String reason;
                    String message;

                    type = condition.getType();
                    status = condition.getStatus();
                    try {

                        lastUpdateTime = DateSafeUtils.parse(condition.getLastTransitionTime(), K8sConstants.TIME_PATTERN);
                        lastUpdateTime = CommonUtils.timeOffset(lastUpdateTime);
                        lastTransationTime = DateSafeUtils.parse(condition.getLastTransitionTime(), K8sConstants.TIME_PATTERN);
                        lastTransationTime = CommonUtils.timeOffset(lastTransationTime);
                    } catch (ParseException e) {
                        throw new K8sClientException("时间类型转换出错", e);
                    }
                    reason = condition.getReason();
                    message = condition.getMessage();
                    kcCondtion.setType(type)
                            .setLastTransitionTime(lastTransationTime)
                            .setLastUpdateTime(lastUpdateTime)
                            .setMessage(message)
                            .setReason(reason)
                            .setStatus(status);
                    kcConditions.add(kcCondtion);
                }
            }
        }
        return kcConditions;
    }


    /**
     *  设置deployment的基本信息
     * */
    @SuppressWarnings("ConstantConditions")
    private static KcDepBaseInfo setBaseInfo(DaemonSet daemonset) {
        KcDepBaseInfo baseInfo = new KcDepBaseInfo();
        String type = "daemonset";
        String name;
        List<String[]> labels;
        List<String[]> annotations;
        String description = null;
        Integer currentRepSum = null;
        Integer planRepSum = null;
        String namespace;

        ObjectMeta metadata = daemonset.getMetadata();

        Map<String, Object> metaDataResultMap = KcDeploymentUtils.setObjectMeta(metadata);
        name = (String) metaDataResultMap.get("name");
        namespace = (String) metaDataResultMap.get("namespace");
        annotations = (List<String[]>) metaDataResultMap.get("annotations");

        DaemonSetSpec spec = daemonset.getSpec();
        List<String[]> matchLabels;
        LabelSelector selector = spec.getSelector();
        PodTemplateSpec template = spec.getTemplate();
        Map<String, Object> specResultMap = KcDeploymentUtils.setSpec(selector, template);
        matchLabels = (List<String[]>) specResultMap.get("matchLabels");
        labels = (List<String[]>) specResultMap.get("labels");

        DaemonSetStatus status = daemonset.getStatus();
        if (status != null) {
            planRepSum = status.getDesiredNumberScheduled();
            currentRepSum = status.getNumberReady();
        }
        baseInfo.setType(type)
                .setName(name)
                .setDescription(description)
                .setLabels(labels)
                .setMatchLabels(matchLabels)
                .setAnnotations(annotations)
                .setPlanRepSum(planRepSum)
                .setNamespace(namespace)
                .setCurrentRepSum(currentRepSum);
        return baseInfo;
    }

    private static void setVolumns(DaemonSet daemonset, KcDaemonset kcDaemonset) {
        DaemonSetSpec spec = daemonset.getSpec();
        if (spec != null) {
            PodTemplateSpec template = spec.getTemplate();
            if (template != null) {
                PodSpec templateSpec = template.getSpec();
                if (templateSpec != null) {
                    List<Volume> volumes = templateSpec.getVolumes();
                    if (CollectionUtils.isNotEmpty(volumes)) {
                        for (Volume volume : volumes) {
                            KcVolume kcVolume = KcDeploymentUtils.setOneVolume(volume);
                            kcDaemonset.getVolumes().add(kcVolume);
                        }
                    }
                }
            }
        }
    }


    public static void restartDaemonset(String namespace, String name) {
        throw new UnsupportedOperationException("daemonset不支持重启");

    }

    /**
     * 伸缩
     * */
    public static void scale(String namespace, String name, Integer maxReplicas) throws K8sClientException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        AppsAPIGroupDSL apps = kc.apps();

        if (apps != null) {
            MixedOperation<StatefulSet, StatefulSetList, RollableScalableResource<StatefulSet>> statefulsets = apps.statefulSets();
            if (statefulsets != null) {
                NonNamespaceOperation<StatefulSet, StatefulSetList, RollableScalableResource<StatefulSet>> nonNamespaceOperation = statefulsets.inNamespace(namespace);
                if (nonNamespaceOperation != null) {
                    RollableScalableResource<StatefulSet> rollableScalableResource = nonNamespaceOperation.withName(name);
                    if (rollableScalableResource != null) {
                        rollableScalableResource.scale(maxReplicas);
                    } else {
                        throw new K8sClientException(String.format("找不到命名空间:[%s]内的服务:[%s]", namespace, name));
                    }
                } else {
                    throw new K8sClientException(String.format("找不到命名空间:[%s]内的服务:[%s]", namespace, name));
                }

            } else {
                throw new K8sClientException(String.format("找不到命名空间:[%s]内的服务:[%s]", namespace, name));
            }
        } else {
            throw new K8sClientException(String.format("找不到命名空间:[%s]内的服务:[%s]", namespace, name));
        }
    }
}
