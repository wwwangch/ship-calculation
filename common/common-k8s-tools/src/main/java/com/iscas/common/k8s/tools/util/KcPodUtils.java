package com.iscas.common.k8s.tools.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.iscas.common.k8s.tools.ApiPaths;
import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.K8sCustomUtils;
import com.iscas.common.k8s.tools.cfg.K8sConstants;
import com.iscas.common.k8s.tools.model.KcResource;
import com.iscas.common.k8s.tools.model.env.KcEnv;
import com.iscas.common.k8s.tools.model.env.KcEnvConfigMap;
import com.iscas.common.k8s.tools.model.env.KcEnvKvConfigMap;
import com.iscas.common.k8s.tools.model.env.KcEnvKvV;
import com.iscas.common.k8s.tools.model.health.*;
import com.iscas.common.k8s.tools.model.pod.*;
import com.iscas.common.tools.core.date.DateSafeUtils;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.ContainerMetrics;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.PodMetrics;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.PodMetricsList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.PodResource;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * 操作POD的工具
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/31 17:52
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class KcPodUtils {
    private KcPodUtils() {}

    /**获取挂载点*/
    public static List<KcPodContainerVoMount> setVolumeMounts(Container container) {
        List<KcPodContainerVoMount> podContainerVoMounts = new ArrayList<>();
        List<VolumeMount> volumeMounts = container.getVolumeMounts();
        if (CollectionUtils.isNotEmpty(volumeMounts)) {
            for (VolumeMount volumeMount : volumeMounts) {
                KcPodContainerVoMount kcPodContainerVoMount = new KcPodContainerVoMount();
                kcPodContainerVoMount.setMountPath(volumeMount.getMountPath())
                        .setName(volumeMount.getName())
                        .setReadOnly(volumeMount.getReadOnly() != null && volumeMount.getReadOnly())
                        .setSubPath(volumeMount.getSubPath())
                        .setSubPathExpr(volumeMount.getSubPathExpr());
                podContainerVoMounts.add(kcPodContainerVoMount);
            }
        }
        return podContainerVoMounts;
    }

    public static LinkedHashMap<String, String> setEnvs(Container container) {
        List<? extends EnvVar> envs = container.getEnv();
        if (CollectionUtils.isNotEmpty(envs)) {
            LinkedHashMap<String, String> kcEnvs = new LinkedHashMap<>();
            for (EnvVar env : envs) {
                String name = env.getName();
                String value = env.getValue();
                kcEnvs.put(name, value);
            }
            return kcEnvs;
        }
        return null;
    }

    public static List<KcEnv> setEnvVar(Container container) {
        List<EnvVar> envs = container.getEnv();
        List<EnvFromSource> envFrom = container.getEnvFrom();
        List<KcEnv> kcEnvs = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(envs)) {
            //处理键值对类型的
            for (EnvVar env : envs) {
                String name = env.getName();
                String value = env.getValue();
                EnvVarSource valueFrom = env.getValueFrom();
                if (value != null) {
                    //值类型的
                    KcEnvKvV kcEnvKvV = new KcEnvKvV();
                    kcEnvKvV.setType("kv");
                    kcEnvKvV.setSubType("v");
                    kcEnvKvV.setKey(name);
                    kcEnvKvV.setValue(value);
                    kcEnvs.add(kcEnvKvV);
                } else if (valueFrom != null) {
                    //暂时只支持configmap类型
                    ConfigMapKeySelector configMapKeyRef = valueFrom.getConfigMapKeyRef();
                    if (configMapKeyRef != null) {
                        String name1 = configMapKeyRef.getName();
                        String key = configMapKeyRef.getKey();
                        KcEnvKvConfigMap kcEnvConfigMap = new KcEnvKvConfigMap();
                        kcEnvConfigMap.setType("kv");
                        kcEnvConfigMap.setSubType("configmap");
                        kcEnvConfigMap.setKey(name);

                        //      env:
                        //        - name: ENV_VAR_USERNAME
                        //          valueFrom:
                        //            configMapKeyRef:
                        //              name: user-configmap
                        //              key: user.name
                        //        - name: ENV_VAR__ID
                        //          valueFrom:
                        //            configMapKeyRef:
                        //              name: user-configmap
                        //              key: user.id
                        //如上，name1对应 configMapKeyRef下的name
                        //key对应configMapKeyRef下的key

                        kcEnvConfigMap.setConfigMapName(name1);
                        kcEnvConfigMap.setConfigMapKey(key);
                        kcEnvs.add(kcEnvConfigMap);
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(envFrom)) {
            for (EnvFromSource envFromSource : envFrom) {
                ConfigMapEnvSource configMapRef = envFromSource.getConfigMapRef();
                KcEnvConfigMap kcEnvConfigMap = new KcEnvConfigMap();
                kcEnvConfigMap.setType("configmap");
                kcEnvConfigMap.setConfigMapName(configMapRef.getName());
                kcEnvs.add(kcEnvConfigMap);
            }
        }
        return kcEnvs.size() == 0 ? null : kcEnvs;
    }

    public static KcResource setResource(Container container) {
        KcResource kcResource = new KcResource();
        if (container.getResources() != null) {
            ResourceRequirements resources = container.getResources();
            Map<String, Quantity> requests = resources.getRequests();
            Map<String, Quantity> limits = resources.getLimits();
            if (MapUtils.isNotEmpty(requests)) {
                Quantity cpu = requests.get("cpu");
                Quantity memory = requests.get("memory");
                if (cpu != null) {
                    kcResource.setCpuMin((int) Math.round((Quantity.getAmountInBytes(cpu)).doubleValue() * 1000));
                }
                if (memory != null) {
                    kcResource.setMemoryMin(((Double)((Quantity.getAmountInBytes(memory)).doubleValue() / 1024 / 1024)).intValue());
                }
            }
            if (MapUtils.isNotEmpty(limits)) {
                Quantity cpu = limits.get("cpu");
                Quantity memory = limits.get("memory");
                if (cpu != null) {
                    kcResource.setCpuMax((int) Math.round((Quantity.getAmountInBytes(cpu)).doubleValue() * 1000));
                }
                if (memory != null) {
                    kcResource.setMemoryMax(((Double)((Quantity.getAmountInBytes(memory)).doubleValue() / 1024 / 1024)).intValue());
                }
            }
        }
        return kcResource;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getHealth(Class<T> tClass, Probe probe) {
        if (probe != null) {
            KcProbe kcProbe;
            if (tClass == KcLivenessProbe.class) {
                kcProbe = new KcLivenessProbe();
            } else {
                kcProbe = new KcReadinessProbe();
            }
            TCPSocketAction tcpSocket = probe.getTcpSocket();
            ExecAction exec = probe.getExec();
            HTTPGetAction httpGet = probe.getHttpGet();
            if (exec != null) {
                kcProbe.setType("COMMAND");
                KcHealthCommandParam healthParam = new KcHealthCommandParam();
                healthParam.setCommands(exec.getCommand());
                setHealthParam(healthParam, probe);
                kcProbe.setHealthCommandParam(healthParam);
            } else if (httpGet != null) {
                kcProbe.setType("HTTP");
                KcHealthHttpParam healthParam = new KcHealthHttpParam();
                List<HTTPHeader> httpHeaders = httpGet.getHttpHeaders();
                if (httpHeaders != null) {
                    Map<String, String> header = new HashMap<>(8);
                    for (HTTPHeader httpHeader : httpHeaders) {
                        header.put(httpHeader.getName(), httpHeader.getValue());
                    }
                    healthParam.setHeaders(header);
                }
                healthParam.setPath(httpGet.getPath())
                        .setPort(httpGet.getPort() == null ? null : httpGet.getPort().getIntVal())
                        .setHost(httpGet.getHost());
                setHealthParam(healthParam, probe);
                healthParam.setProtocol(httpGet.getScheme());
                kcProbe.setHealthHttpParam(healthParam);
            } else if (tcpSocket != null) {
                kcProbe.setType("TCP");
                KcHealthTcpParam healthParam = new KcHealthTcpParam();
                //noinspection ConstantConditions
                healthParam.setPort(tcpSocket.getPort() == null ? null : tcpSocket.getPort().getIntVal());
                setHealthParam(healthParam, probe);
                kcProbe.setHealthTcpParam(healthParam);
            }
            return (T) kcProbe;
        }
        return null;
    }

    public static String  handleImage(String image) {
        //todo 处理image的问题，暂时这么处理，如果没有版本好，使用latest
        if (!image.contains(":")) {
            image += ":latest";
        } else if (image.endsWith(":null")) {
            image = image.replace(":null", ":latest");
        } else if (!image.endsWith(":latest")){
            String rightImage = StringUtils.substringAfter(image, ":");
            if (!rightImage.contains(":")) {
                image += ":latest";
            }
        }
        return image;
    }

    /**
     * 获取pod
     * */
    public static List<KcPod> getPods(String namespace, boolean metrics) throws ParseException {
        List<KcPod> kcPods = null;
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        NonNamespaceOperation<Pod, PodList, PodResource<Pod>> podsInNamespace = kc.pods().inNamespace(namespace);
        if (podsInNamespace != null) {
            PodList podList = podsInNamespace.list();
            if (podList != null) {
                List<Pod> items = podList.getItems();
                if (CollectionUtils.isNotEmpty(items)) {
                    //获取metrics
//                    JSONObject podMetrics = null;
                    PodMetricsList podMetrics = null;
                    if (metrics) {
//                        podMetrics = getPodsMetrics(namespace);
                        try {
                            podMetrics = kc.top().pods().metrics();
                        } catch (Exception e) {
                            System.err.println("获取pod metrics出错");
                            e.printStackTrace();
                        }
                    }

                    kcPods = new ArrayList<>();
                    for (Pod pod : items) {
                        KcPod kcPod = getOnePod(pod, podMetrics);
                        kcPods.add(kcPod);
                    }
                }
            }
        }
        return kcPods;
    }

    private static JSONObject getPodsMetrics(String namespace) throws IOException {
        String url = K8sClient.getConfig().getApiServerPath() + ApiPaths.API_PATH_METRICS_PODS;
        url = url.replaceAll("//+", "/")
                .replace("{namespace}", namespace);
        String s = K8sCustomUtils.doGet(url);
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        try {
            return JSONUtil.parseObj(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static KcPod getOnePod(Pod pod, PodMetricsList podMetricsList) throws ParseException {
        KcPod kcPod = new KcPod();
        setBaseInfo(kcPod, pod, podMetricsList);
        setContainers(kcPod, pod, podMetricsList);
        setInitContainers(kcPod, pod, podMetricsList);
        setConditions(kcPod, pod);
        return kcPod;
    }

    private static void setConditions(KcPod kcPod, Pod pod) throws ParseException {
        List<KcPodCondition> kcPodConditions = new ArrayList<>();
        List<PodCondition> conditions = pod.getStatus().getConditions();
        if (CollectionUtils.isNotEmpty(conditions)) {
            for (PodCondition condition : conditions) {
                KcPodCondition kcPodCondition = new KcPodCondition();
                kcPodCondition.setMessage(condition.getMessage())
                        .setReason(condition.getReason())
                        .setStatus(condition.getStatus())
                        .setType(condition.getType());
                String lastProbeTime = condition.getLastProbeTime();
                if (lastProbeTime != null) {
                    Date lastTime = DateSafeUtils.parse(lastProbeTime, K8sConstants.TIME_PATTERN);
                    kcPodCondition.setLastProbTime(CommonUtils.timeOffset(lastTime));
                }

                String lastTransitionTime = condition.getLastTransitionTime();
                if (lastTransitionTime != null) {
                    Date lastTransTime = DateSafeUtils.parse(lastTransitionTime, K8sConstants.TIME_PATTERN);
                    kcPodCondition.setLastTransitionTime(CommonUtils.timeOffset(lastTransTime));
                }
                kcPodConditions.add(kcPodCondition);
            }
        }
        kcPod.setKcPodConditions(kcPodConditions);
    }

    private static void setInitContainers(KcPod kcPod, Pod pod, PodMetricsList podMetrics) throws ParseException {
        List<KcPodContainer> kcPodContainers = new ArrayList<>();
        PodStatus status = pod.getStatus();
        List<ContainerStatus> containerStatuses = status.getInitContainerStatuses();
        List<Container> containers = pod.getSpec().getInitContainers();
        setKcPodContainer(kcPod, podMetrics, kcPodContainers, containerStatuses, containers);
        kcPod.setInitContainers(kcPodContainers);
    }

    private static void setKcPodContainer(KcPod kcPod, PodMetricsList podMetrics, List<KcPodContainer> kcPodContainers, List<ContainerStatus> containerStatuses, List<Container> containers) throws ParseException {
        if (CollectionUtils.isNotEmpty(containerStatuses)) {
            for (ContainerStatus containerStatus : containerStatuses) {
                for (Container container : containers) {
                    if (Objects.equals(containerStatus.getName(), container.getName())) {
                        KcPodContainer kcPodContainer = getContainer(container, containerStatus, kcPod, podMetrics);
                        kcPodContainers.add(kcPodContainer);
                        break;
                    }
                }
            }
        }
    }

    private static void setContainers(KcPod kcPod, Pod pod, PodMetricsList podMetrics) throws ParseException {
        List<KcPodContainer> kcPodContainers = new ArrayList<>();
        PodStatus status = pod.getStatus();
        List<ContainerStatus> containerStatuses = status.getContainerStatuses();
        List<Container> containers = pod.getSpec().getContainers();
        setKcPodContainer(kcPod, podMetrics, kcPodContainers, containerStatuses, containers);
        kcPod.setContainers(kcPodContainers);

    }

    private static void setHealthParam(KcHealthParam healthParam, Probe probe) {
        healthParam.setHealthThreshold(probe.getSuccessThreshold())
                .setUnHealthThreshold(probe.getFailureThreshold())
                .setInitialDelaySeconds(probe.getInitialDelaySeconds())
                .setPeriodSeconds(probe.getPeriodSeconds())
                .setTimeout(probe.getTimeoutSeconds());
    }

    @SuppressWarnings("DuplicatedCode")
    private static KcPodContainer getContainer(Container container, ContainerStatus containerStatus, KcPod kcPod, PodMetricsList podMetrics) throws ParseException {
        KcPodContainer podContainer = new KcPodContainer();
        String image = container.getImage();
        image = handleImage(image);

        podContainer.setImage(image)
                .setImagePullPolicy(container.getImagePullPolicy());

        Probe livenessProbe = container.getLivenessProbe();
        KcLivenessProbe kcLivenessProbe = getHealth(KcLivenessProbe.class, livenessProbe);

        Probe readinessProbe = container.getReadinessProbe();
        KcReadinessProbe kcReadinessProbe = getHealth(KcReadinessProbe.class, readinessProbe);

        podContainer.setLivenessProbe(kcLivenessProbe)
                .setReadinessProbe(kcReadinessProbe)
                .setName(container.getName());

        List<KcPodContainerPort> kcPodContainerPorts = new ArrayList<>();
        List<ContainerPort> ports = container.getPorts();
        if (CollectionUtils.isNotEmpty(ports)) {
            for (ContainerPort port : ports) {
                KcPodContainerPort kcPodContainerPort = new KcPodContainerPort();
                kcPodContainerPort.setContainerPort(port.getContainerPort())
                        .setHostIp(port.getHostIP())
                        .setHostPort(port.getHostPort() == null ? null : port.getHostPort().toString())
                        .setProtocol(port.getProtocol());
                kcPodContainerPorts.add(kcPodContainerPort);
            }
        }

        podContainer.setContainerPorts(kcPodContainerPorts);
        podContainer.setTerminationMessagePath(container.getTerminationMessagePath())
                .setTerminationMessagePolicy(container.getTerminationMessagePolicy());
        podContainer.setContainerId(containerStatus.getContainerID())
                .setImageId(podContainer.getImageId()).setReady(containerStatus.getReady())
                .setRestartCount(containerStatus.getRestartCount());
        podContainer.setLastStateRunning(getRunning(containerStatus.getLastState().getRunning()))
                .setStateRunning(getRunning(containerStatus.getState().getRunning()))
                .setLastStateTerminated(getTerminated(containerStatus.getLastState().getTerminated()))
                .setStateTerminated(getTerminated(containerStatus.getState().getTerminated()))
                .setLastStateWaiting(getWaiting(containerStatus.getLastState().getWaiting()))
                .setStateWaiting(getWaiting(containerStatus.getState().getWaiting()));

        //resource
        podContainer.setResource(setResource(container));

        //环境变量
        podContainer.setEnvs(setEnvs(container));

        //环境变量新
        podContainer.setEnvVar(setEnvVar(container));

        //挂载点
        podContainer.setVolumeMounts(setVolumeMounts(container));

        if (podMetrics != null) {
            List<PodMetrics> items = podMetrics.getItems();
            if (items != null) {
                for (PodMetrics item : items) {
                    if (item != null) {
                        ObjectMeta metadata = item.getMetadata();
                        String name1 = metadata.getName();
                        if (Objects.equals(name1, kcPod.getBaseInfo().getName())) {
                            long usedMemory = 0L;
                            double usedCpu = 0.0;
                            double usedStorage = 0;
                            List<ContainerMetrics> containers = item.getContainers();
                            if (containers != null) {
                                for (ContainerMetrics cm : containers) {
                                    if (cm != null) {
                                        Map<String, Quantity> usage = cm.getUsage();
                                        String connStr = cm.getName();
                                        if (usage != null && Objects.equals(connStr, podContainer.getName())) {
                                            Quantity memoryStr = usage.get("memory");
                                            Quantity cpuStr = usage.get("cpu");
                                            if (memoryStr != null) {
                                                usedMemory += Quantity.getAmountInBytes(memoryStr).longValue() / 1024;
                                            }
                                            if (cpuStr != null) {
                                                usedCpu += Quantity.getAmountInBytes(cpuStr).doubleValue();
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                            podContainer.setUsedCpu(usedCpu)
                                    .setUsedMemory(usedMemory);
                        }
                    }
                }
            }
        }

        return podContainer;
    }

    private static KcPodContainerStateWaiting getWaiting(ContainerStateWaiting containerStateWaiting) {
        if (containerStateWaiting != null) {
            KcPodContainerStateWaiting kcPodContainerStateWaiting = new KcPodContainerStateWaiting();
            kcPodContainerStateWaiting.setMessage(containerStateWaiting.getMessage())
                    .setReason(containerStateWaiting.getReason());
            return kcPodContainerStateWaiting;
        }
        return null;
    }

    private static KcPodContainerStateRunning getRunning(ContainerStateRunning containerStateRunning) throws ParseException {
        if (containerStateRunning != null) {
            String startedAt = containerStateRunning.getStartedAt();
            Date date = DateSafeUtils.parse(startedAt, K8sConstants.TIME_PATTERN);
            date = CommonUtils.timeOffset(date);
            KcPodContainerStateRunning kcPodContainerStateRunning = new KcPodContainerStateRunning();
            kcPodContainerStateRunning.setStartedAt(date);
            return kcPodContainerStateRunning;
        }
        return null;
    }

    private static KcPodContainerStateTerminated getTerminated(ContainerStateTerminated containerStateTerminated) throws ParseException {
        if (containerStateTerminated != null) {
            KcPodContainerStateTerminated kcPodContainerStateTerminated = new KcPodContainerStateTerminated();
            kcPodContainerStateTerminated.setExitCode(containerStateTerminated.getExitCode())
                    .setFinishedAt(CommonUtils.timeOffset(DateSafeUtils.parse(containerStateTerminated.getFinishedAt(), K8sConstants.TIME_PATTERN)))
                    .setMessage(containerStateTerminated.getMessage())
                    .setReason(containerStateTerminated.getReason())
                    .setSignal(containerStateTerminated.getSignal())
                    .setStartedAt(CommonUtils.timeOffset(DateSafeUtils.parse(containerStateTerminated.getStartedAt(), K8sConstants.TIME_PATTERN)));
            return kcPodContainerStateTerminated;
        }
        return null;
    }


    private static void setBaseInfo(KcPod kcPod, Pod pod, PodMetricsList podMetricsList) throws ParseException {
        KcPodBaseInfo podBaseInfo = new KcPodBaseInfo();
        ObjectMeta metadata = pod.getMetadata();
        PodSpec spec = pod.getSpec();
        PodStatus status = pod.getStatus();
        podBaseInfo.setApiVersion(pod.getApiVersion())
                .setName(metadata.getName());
        Date createTime = DateSafeUtils.parse(metadata.getCreationTimestamp(), K8sConstants.TIME_PATTERN);
        createTime = CommonUtils.timeOffset(createTime);
        podBaseInfo.setCreateTime(createTime).setRunTimeStr(CommonUtils.getTimeDistance(createTime))
                .setNamespace(metadata.getNamespace());
        Map<String, String> labels = metadata.getLabels();
        if (MapUtils.isNotEmpty(labels)) {
            for (Map.Entry<String, String> entry : labels.entrySet()) {
                String[] label = new String[2];
                label[0] = entry.getKey();
                label[1] = entry.getValue();
                podBaseInfo.getLabels().add(label);
            }
        }

        String imagePullSecretStr = null;
        List<LocalObjectReference> imagePullSecrets = spec.getImagePullSecrets();
        if (CollectionUtils.isNotEmpty(imagePullSecrets)) {
            //todo 暂时取第1个元素
            LocalObjectReference localObjectReference = imagePullSecrets.get(0);
            imagePullSecretStr = localObjectReference.getName();
        }

        //获取所属工作负载信息
        StringBuilder ownerReferenceKind = new StringBuilder();
        String ownerReferenceName = "";
        List<OwnerReference> ownerReferences = pod.getMetadata().getOwnerReferences();
        if (CollectionUtils.isNotEmpty(ownerReferences)) {
            for (OwnerReference ownerReference : ownerReferences) {
                if (!"".equals(ownerReferenceKind.toString())) {
                    ownerReferenceKind.append(",");
                }
                if (!"".equals(ownerReferenceName)) {
                    ownerReferenceName += ",";
                }
                String kind = ownerReference.getKind();
                String name = ownerReference.getName();
                ownerReferenceKind.append(kind);
                ownerReferenceName += name;
            }
        }

        podBaseInfo.setDnsPolicy(spec.getDnsPolicy())
                .setEnableServiceLinks(spec.getEnableServiceLinks())
                .setNodeName(spec.getNodeName())
                .setRestartPolicy(spec.getRestartPolicy())
                .setServiceAccount(spec.getServiceAccountName())
                .setTerminationGracePeriodSeconds(spec.getTerminationGracePeriodSeconds())
                .setHostIp(status.getHostIP())
                .setPhase(status.getPhase())
                .setPodIp(status.getPodIP())
                .setQosClass(status.getQosClass())
                .setImagePullSecrets(imagePullSecretStr)
                .setStartTime(CommonUtils.timeOffset(DateSafeUtils.parse(status.getStartTime(), K8sConstants.TIME_PATTERN)))
                .setOwnerReferenceKind(ownerReferenceKind.toString())
                .setOwnerReferenceName(ownerReferenceName);
        setPodMetricsInfo(podBaseInfo, podMetricsList);

        kcPod.setBaseInfo(podBaseInfo);
    }

    @SuppressWarnings("DuplicatedCode")
    private static void setPodMetricsInfo(KcPodBaseInfo baseInfo, PodMetricsList podMetrics) {
        if (podMetrics == null) {
            return;
        }
        String name = baseInfo.getName();
        List<PodMetrics> items = podMetrics.getItems();
        if (items != null) {
            for (PodMetrics item : items) {
                if (item != null) {
                    ObjectMeta metadata = item.getMetadata();
                    String name1 = metadata.getName();
                    if (Objects.equals(name1, name)) {
                        long usedMemory = 0L;
                        double usedCpu = 0.0;
                        double usedStorage = 0;
                        List<ContainerMetrics> containers = item.getContainers();
                        if (containers != null) {
                            for (ContainerMetrics container : containers) {
                                if (container != null) {
                                    Map<String, Quantity> usage = container.getUsage();
                                    if (usage != null) {
                                        Quantity memoryStr = usage.get("memory");
                                        Quantity cpuStr = usage.get("cpu");
                                        if (memoryStr != null) {
                                            usedMemory += Quantity.getAmountInBytes(memoryStr).longValue() / 1024;
                                        }
                                        if (cpuStr != null) {
                                            usedCpu += Quantity.getAmountInBytes(cpuStr).doubleValue();
                                        }
                                    }
                                }
                            }
                        }
                        baseInfo.setUsedCpu(usedCpu)
                                .setUsedMemory(usedMemory);
                        return;
                    }
                }
            }
        }
    }


}
