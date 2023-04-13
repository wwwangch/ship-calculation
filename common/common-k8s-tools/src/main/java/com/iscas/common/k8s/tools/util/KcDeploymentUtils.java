package com.iscas.common.k8s.tools.util;

import cn.hutool.core.convert.Convert;
import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConstants;
import com.iscas.common.k8s.tools.exception.K8sCleintRuntimeException;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.KcContainer;
import com.iscas.common.k8s.tools.model.KcContainerPort;
import com.iscas.common.k8s.tools.model.KcResource;
import com.iscas.common.k8s.tools.model.KcRuntimeInfo;
import com.iscas.common.k8s.tools.model.autoscale.KcAutoScaleResource;
import com.iscas.common.k8s.tools.model.autoscale.KcAutoscale;
import com.iscas.common.k8s.tools.model.deployment.KcDepBaseInfo;
import com.iscas.common.k8s.tools.model.deployment.KcDeployment;
import com.iscas.common.k8s.tools.model.env.*;
import com.iscas.common.k8s.tools.model.health.KcHealthTcpParam;
import com.iscas.common.k8s.tools.model.health.KcLivenessProbe;
import com.iscas.common.k8s.tools.model.health.KcReadinessProbe;
import com.iscas.common.k8s.tools.model.pod.KcPodContainerVoMount;
import com.iscas.common.k8s.tools.model.volume.KcVoConfigMapParam;
import com.iscas.common.k8s.tools.model.volume.KcVoHostPathParam;
import com.iscas.common.k8s.tools.model.volume.KcVoNfsParam;
import com.iscas.common.k8s.tools.model.volume.KcVolume;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.common.tools.core.string.StringRaiseUtils;
import com.iscas.templet.exception.BaseException;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apps.*;
import io.fabric8.kubernetes.api.model.autoscaling.v2beta2.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.*;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/8 17:30
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class KcDeploymentUtils {
    private static final String TCP = "TCP";

    private static final String APP = "app";

    private KcDeploymentUtils() {}

    public static void setContainer(KcContainer container, Container specNestedInitContainer) throws K8sClientException {
        specNestedInitContainer.setName(container.getName());
        specNestedInitContainer.setImage(container.getImage());
        specNestedInitContainer.setImagePullPolicy(container.getImagePullPolicy());

        //命令
        List<String> commands = container.getCommands();
        //参数
        List<String> args = container.getArgs();
        //环境变量
        LinkedHashMap<String, String> env = container.getEnv();

        //环境变量新，env弃用
        List<KcEnv> kcEnvVar = container.getEnvVar();
        //挂载点
        List<KcPodContainerVoMount> volumeMounts = container.getVolumeMounts();
        //就绪检查
        KcReadinessProbe readinessProbe = container.getReadinessProbe();
        //存活检查
        KcLivenessProbe livenessProbe = container.getLivenessProbe();

        //资源限制配置
        KcResource resource = container.getResource();

        //端口
        List<KcContainerPort> ports = container.getPorts();

        if (CollectionUtils.isNotEmpty(commands)) {
            for (String command : commands) {
                specNestedInitContainer.getCommand().add(command);
            }
        }
        if (CollectionUtils.isNotEmpty(args)) {
            for (String arg : args) {
                specNestedInitContainer.getArgs().add(arg);
            }
        }
        if (MapUtils.isNotEmpty(env)) {
            for (Map.Entry<String ,String> entry : env.entrySet()) {
                EnvVar envVar = new EnvVar();
                envVar.setName(entry.getKey());
                envVar.setValue(entry.getValue());
                specNestedInitContainer.getEnv().add(envVar);
            }
        }

        //设置环境变量新，原来的env弃用
        if (CollectionUtils.isNotEmpty(kcEnvVar)) {
            for (KcEnv kcEnv : kcEnvVar) {
                String type = kcEnv.getType();
                switch (type) {
                    case "configmap": {
                        KcEnvConfigMap kcEnvConfigMap = (KcEnvConfigMap) kcEnv;
                        EnvFromSource envFromSource = new EnvFromSource();
                        ConfigMapEnvSource configMapEnvSource = new ConfigMapEnvSource();
                        configMapEnvSource.setName(kcEnvConfigMap.getConfigMapName());
                        envFromSource.setConfigMapRef(configMapEnvSource);
                        specNestedInitContainer.getEnvFrom().add(envFromSource);
                        break;
                    }
                    case "kv": {
                        KcEnvKv kcEnvKv = (KcEnvKv) kcEnv;
                        String subType = kcEnvKv.getSubType();
                        switch (subType) {
                            case "v": {
                                KcEnvKvV kcEnvKvV = (KcEnvKvV) kcEnvKv;
                                EnvVar envVar = new EnvVar();
                                envVar.setName(kcEnvKvV.getKey());
                                envVar.setValue(kcEnvKvV.getValue());
                                specNestedInitContainer.getEnv().add(envVar);
                                break;
                            }
                            case "configmap": {
                                KcEnvKvConfigMap kcEnvKvConfigMap = (KcEnvKvConfigMap) kcEnvKv;
                                EnvVar envVar = new EnvVar();
                                envVar.setName(kcEnvKvConfigMap.getKey());
                                EnvVarSource envVarSource = new EnvVarSource();
                                ConfigMapKeySelector configMapKeySelector = new ConfigMapKeySelector();
                                configMapKeySelector.setName(kcEnvKvConfigMap.getConfigMapName());
                                configMapKeySelector.setKey(kcEnvKvConfigMap.getConfigMapKey());
                                envVarSource.setConfigMapKeyRef(configMapKeySelector);
                                envVar.setValueFrom(envVarSource);
                                specNestedInitContainer.getEnv().add(envVar);
                                break;
                            }
                            default: {
                                throw new K8sCleintRuntimeException(MessageFormat.format("不支持的环境变量类型:{0}-{1}", type, subType));
                            }
                        }
                        break;
                    }
                    default:{
                        throw new K8sCleintRuntimeException(MessageFormat.format("不支持的环境变量类型:{0}", type));
                    }
                }
            }
        }

        //挂载点
        if (CollectionUtils.isNotEmpty(volumeMounts)) {
            for (KcPodContainerVoMount volumeMount : volumeMounts) {
                if (volumeMount.getName() == null) {
                    continue;
                }
                VolumeMount vm = new VolumeMount();
                vm.setName(volumeMount.getName());
                vm.setMountPath(volumeMount.getMountPath());
                vm.setSubPath(volumeMount.getSubPath() == null ? null : volumeMount.getSubPath());
                vm.setReadOnly(volumeMount.isReadOnly());
                vm.setSubPathExpr(volumeMount.getSubPathExpr());
                specNestedInitContainer.getVolumeMounts().add(vm);
            }
        }
        if (readinessProbe != null) {
            String type = readinessProbe.getType();
            if (!TCP.equalsIgnoreCase(type)) {
                throw new K8sClientException("暂时只支持TCP的健康检查");
            }
            KcHealthTcpParam tcpParam = readinessProbe.getHealthTcpParam();
            Probe rProbe = getProbe(tcpParam);
            specNestedInitContainer.setReadinessProbe(rProbe);

        }

        if (livenessProbe != null) {
            String type = livenessProbe.getType();
            if (!TCP.equalsIgnoreCase(type)) {
                throw new K8sClientException("暂时只支持TCP的健康检查");
            }
            KcHealthTcpParam tcpParam = livenessProbe.getHealthTcpParam();
            Probe lProbe = getProbe(tcpParam);
            specNestedInitContainer.setLivenessProbe(lProbe);
        }

        if (CollectionUtils.isNotEmpty(ports)) {
            for (KcContainerPort port : ports) {
                ContainerPort containerPort = new ContainerPort();
                if (StringUtils.isNotEmpty(port.getName())) {
                    containerPort.setName(port.getName());
                }
                if (port.getContainerPort() != null) {
                    containerPort.setContainerPort(port.getContainerPort());
                }
                if (port.getHostPort() != null) {
                    containerPort.setHostPort(port.getHostPort());
                }
                if (port.getProtocol() != null) {
                    containerPort.setProtocol(port.getProtocol());
                }
                if (port.getHostIp() != null) {
                    containerPort.setHostIP(port.getHostIp());
                }
                specNestedInitContainer.getPorts().add(containerPort);
            }
        }

        if (resource != null) {
            ResourceRequirements resourceRequirements = new ResourceRequirements();
            Integer cpuMax = resource.getCpuMax();
            Integer cpuMin = resource.getCpuMin();
            Integer memoryMax = resource.getMemoryMax();
            Integer memoryMin = resource.getMemoryMin();
            Map<String, Quantity> requests = new HashMap<>(16);
            Map<String, Quantity> limits = new HashMap<>(16);
            if (cpuMax != null) {
                limits.put("cpu", Quantity.parse(cpuMax + "m"));
            }
            if (cpuMin != null) {
                requests.put("cpu", Quantity.parse(cpuMin + "m"));
            }
            if (memoryMax != null) {
                limits.put("memory", Quantity.parse(memoryMax + "Mi"));
            }
            if (memoryMin != null) {
                requests.put("memory", Quantity.parse(memoryMin + "Mi"));
            }
            resourceRequirements.setLimits(limits);
            resourceRequirements.setRequests(requests);
            specNestedInitContainer.setResources(resourceRequirements);
        }
    }

    private static Probe getProbe(KcHealthTcpParam tcpParam) {
        Probe rProbe = new Probe();
        TCPSocketAction tcpSocketAction = new TCPSocketAction();
        tcpSocketAction.setPort(new IntOrString(tcpParam.getPort()));
        rProbe.setTcpSocket(tcpSocketAction);
        rProbe.setTimeoutSeconds(tcpParam.getTimeout());
        rProbe.setInitialDelaySeconds(tcpParam.getInitialDelaySeconds());
        rProbe.setPeriodSeconds(tcpParam.getPeriodSeconds());
        rProbe.setSuccessThreshold(tcpParam.getHealthThreshold());
        rProbe.setFailureThreshold(tcpParam.getUnHealthThreshold());
        return rProbe;
    }

    /**
     * 创建一个Deployment
     * */
    @SuppressWarnings("DuplicatedCode")
    public static void createDeployment(KcDeployment kcDeployment) throws K8sClientException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        KcDepBaseInfo baseInfo = kcDeployment.getBaseInfo();
        List<KcVolume> volumes = kcDeployment.getVolumes();

        //命名空间
        String namespace  = baseInfo.getNamespace();
        String name = baseInfo.getName();

        //如果存在，删除这个deployment
//        KcDeploymentUtils.deleteDeployment(namespace, name);

        Map<String, String> labelMap = getLabelMap(baseInfo);
        Map<String, String> annotationMap = getAnnotationMap(baseInfo);

        List<KcContainer> initContainers = kcDeployment.getInitContainer();
        List<KcContainer> containers = kcDeployment.getContainers();
        String imagePullSecret = kcDeployment.getImagePullSecret();

        AppsAPIGroupDSL apps = kc.apps();
        if (apps == null) {
            throw new K8sClientException("获取不到apps，无法新建deployment");
        }
        Namespace ns = kc.namespaces().withName(namespace).get();
        if (ns == null) {
            throw new K8sClientException("获取不到命名空间，无法新建deployment");
        }

        Deployment deployment = new Deployment();
        //meta
        ObjectMeta objectMeta = createObjectMeta(name, namespace, labelMap, annotationMap);
        deployment.setMetadata(objectMeta);

        //spec
        DeploymentSpec deploymentSpec = new DeploymentSpec();

        //replics
        deploymentSpec.setReplicas(baseInfo.getPlanRepSum());

        //selector
        LabelSelector labelSelector = new LabelSelector();
        labelSelector.setMatchLabels(labelMap);
        deploymentSpec.setSelector(labelSelector);

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
        createImagePullSecret(imagePullSecret, podSpec);

        //template-spec-init container
        List<Container> initPodContainers = new ArrayList<>();
        if (initContainers != null) {
            //构建初始化container
            for (KcContainer initContainer : initContainers) {
                Container container = new Container();
                initPodContainers.add(container);
                setContainer(initContainer, container);
            }
        }
        podSpec.setInitContainers(initPodContainers);

        List<Container> podContainers = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(containers)) {
            //构建镜像
            for (KcContainer container : containers) {
                Container podContainner = new Container();
                podContainers.add(podContainner);
                setContainer(container, podContainner);
            }
        }
        podSpec.setContainers(podContainers);

        //存储卷
        podSpec.setVolumes(createVolume(volumes));

        podTemplateSpec.setSpec(podSpec);

        deploymentSpec.setTemplate(podTemplateSpec);

        deployment.setSpec(deploymentSpec);

        RollableScalableResource<Deployment> deploymentRollableScalableResource = apps.deployments().inNamespace(namespace).withName(name);
        if (deploymentRollableScalableResource.get() == null) {
            //新增
            apps.deployments().inNamespace(namespace).create(deployment);
        } else {
            //编辑
            validMatchLabel(deploymentRollableScalableResource, deployment);
            deploymentRollableScalableResource.edit(n -> deployment);
        }
    }

    private static void validMatchLabel(RollableScalableResource<Deployment> deploymentRollableScalableResource, Deployment deployment) throws K8sClientException {
        Deployment deployment1 = deploymentRollableScalableResource.get();
        Map<String, String> labels = deployment.getMetadata().getLabels();
        Map<String, String> labels1 = deployment1.getMetadata().getLabels();
        if (!labels1.containsKey(APP) && labels.containsKey(APP)) {
            //删除matchLabel,否则修改会失败
            LabelSelector selector = deployment.getSpec().getSelector();
            if (selector != null && selector.getMatchLabels() != null && selector.getMatchLabels().containsKey(APP)) {
                throw new K8sClientException("不允许修改matchlabel,请克隆此服务或删除此服务，重新创建");
            }
        }
    }

    public static Map<String, String> getLabelMap(KcDepBaseInfo baseInfo) {
        List<String[]> labels = baseInfo.getLabels();
        Map<String, String> labelMap = new LinkedHashMap<>();
        if (CollectionUtils.isNotEmpty(labels)) {
            for (String[] label : labels) {
                labelMap.put(label[0], label[1]);
            }
        }
        return labelMap;
    }

    public static Map<String, String> getAnnotationMap(KcDepBaseInfo baseInfo) {
        List<String[]> annotations = baseInfo.getAnnotations();
        Map<String, String> annotationMap = new LinkedHashMap<>();
        if (CollectionUtils.isNotEmpty(annotations)) {
            for (String[] annotation : annotations) {
                annotationMap.put(annotation[0], annotation[1]);
            }
        }
        return annotationMap;
    }

    public static ObjectMeta createObjectMeta(String name, String namespace, Map<String, String> labelMap,
                                              Map<String, String> annotationMap) {
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(name);
        objectMeta.setNamespace(namespace);
        objectMeta.setLabels(labelMap);
        objectMeta.setAnnotations(annotationMap);
        return objectMeta;
    }

    public static void createImagePullSecret(String imagePullSecret, PodSpec podSpec) {
        if (StringUtils.isNotEmpty(imagePullSecret)) {
            LocalObjectReference localObjectReference = new LocalObjectReference();
            localObjectReference.setName(imagePullSecret);
            podSpec.setImagePullSecrets(Collections.singletonList(localObjectReference));
        }
    }


    public static List<Volume> createVolume(List<KcVolume> volumes) {
        if (CollectionUtils.isNotEmpty(volumes)) {
            return volumes.stream().map(v -> {
                Volume volume = new Volume();
                volume.setName(v.getName());
                KcVolume.KcVolumeType type = v.getType();
                try {
                    switch (type) {
                        case hostPath: {
                            KcVoHostPathParam params = new KcVoHostPathParam();
                            Map paramMap = (Map) v.getParams();
                            if (paramMap != null) {
                                params.setPath((String) paramMap.get("path"));
                                params.setType(KcVoHostPathParam.KcVoHostPathType.valueOf((String) paramMap.get("type")));
                            }
                            if (StringUtils.isEmpty(params.getPath())) {
                                throw new K8sClientException("hostPath类型存储卷的path不能为空");
                            }
                            HostPathVolumeSource hostPathVolumeSource = new HostPathVolumeSource();
                            hostPathVolumeSource.setPath(params.getPath());
                            hostPathVolumeSource.setType(KcVoHostPathParam.KcVoHostPathType.EmptyString == params.getType() ? "" :
                                    params.getType().toString());
                            volume.setHostPath(hostPathVolumeSource);
                            break;
                        }
                        case NFS: {
                            KcVoNfsParam params = new KcVoNfsParam();
                            Map paramMap = (Map) v.getParams();
                            if (paramMap != null) {
                                params = Convert.convert(KcVoNfsParam.class, paramMap);
                            }
                            if (StringUtils.isEmpty(params.getPath())) {
                                throw new K8sClientException("NFS类型存储卷的path不能为空");
                            }
                            NFSVolumeSource nfsVolumeSource = new NFSVolumeSource();
                            nfsVolumeSource.setPath(params.getPath());
                            nfsVolumeSource.setReadOnly(params.isReadOnly());
                            nfsVolumeSource.setServer(params.getServer());
                            volume.setNfs(nfsVolumeSource);
                            break;
                        }
                        case configMap: {
                            KcVoConfigMapParam params = new KcVoConfigMapParam();
                            Map paramMap = (Map) v.getParams();
                            if (paramMap != null) {
                                params = Convert.convert(KcVoConfigMapParam.class, paramMap);
                            }
                            if (StringUtils.isEmpty(params.getConfigmap())) {
                                throw new K8sClientException("configMap类型存储卷的configmap不能为空");
                            }
                            ConfigMapVolumeSource configMapVolumeSource = new ConfigMapVolumeSource();
                            configMapVolumeSource.setName(params.getConfigmap());
                            if (CollectionUtils.isNotEmpty(params.getKeyToPath())) {
                                List<KeyToPath> keyToPaths = new ArrayList<>();
                                for (Object keyToPathArrayObject : params.getKeyToPath()) {
                                    String []keyToPathArray;
                                    if (keyToPathArrayObject instanceof String[]) {
                                        keyToPathArray = (String[]) keyToPathArrayObject;
                                    } else {
                                        keyToPathArray = new String[2];
                                        keyToPathArray[0] = ((List<String>) keyToPathArrayObject).get(0);
                                        keyToPathArray[1] = ((List<String>) keyToPathArrayObject).get(1);
                                    }
                                    KeyToPath keyToPath = new KeyToPath();
                                    keyToPath.setKey(keyToPathArray[0]);
                                    keyToPath.setPath(keyToPathArray[1]);
                                    keyToPaths.add(keyToPath);
                                }
                                configMapVolumeSource.setItems(keyToPaths);
                            }

                            volume.setConfigMap(configMapVolumeSource);
                            break;
                        }
                        default: {
                            throw new K8sClientException("不支持的存储卷类型:" + type);
                        }
                    }
                } catch (K8sClientException e) {
                    throw new RuntimeException(e);
                }
                return volume;
            }).toList();
        }
        return new ArrayList<>();
    }

    public static void deleteDeployment(String namespace, String name) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        AppsAPIGroupDSL apps = kc.apps();
        if (apps != null) {
            NonNamespaceOperation<Deployment, DeploymentList, RollableScalableResource<Deployment>> deployResource = apps.deployments().inNamespace(namespace);
            if (deployResource != null) {
                RollableScalableResource<Deployment> rollableScalableResource = deployResource.withName(name);
                if (rollableScalableResource != null && rollableScalableResource.get() != null) {
                    rollableScalableResource.delete();
                }
            }
        }
    }

    /**
     * 获取deployment的信息
     * @since jdk1.8
     * @date 2019/12/9
     * @param namespace 命名空间
     * @throws K8sClientException K8S异常
     * @return java.util.List<com.iscas.common.k8s.tools.model.deployment.KcDeployment>
     */
    public static List<KcDeployment> getDeployments(String namespace) throws K8sClientException {

        List<KcDeployment> kcDeployments = null;
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        AppsAPIGroupDSL apps = kc.apps();
        if (apps != null) {
            NonNamespaceOperation<Deployment, DeploymentList, RollableScalableResource<Deployment>> deployments = apps.deployments().inNamespace(namespace);
            if (deployments != null) {
                DeploymentList deploymentList = deployments.list();
                if (deploymentList != null) {
                    List<Deployment> items = deploymentList.getItems();
                    if (CollectionUtils.isNotEmpty(items)) {
                        kcDeployments = new ArrayList<>();
                        for (Deployment item : items) {
                            String name = null;
                            Integer currentRepSum = null;
                            Integer planRepSum = null;
                            String runtimeStr = null;
                            List<KcRuntimeInfo> runtimeInfos;
                            KcDepBaseInfo baseInfo;

                            KcDeployment kcDeployment = new KcDeployment();

                            ObjectMeta metadata = item.getMetadata();
                            if (metadata != null) {
                                //获取name
                                name = metadata.getName();
                                //获取运行时间
                                runtimeStr = CommonUtils.getRuntimeStr(metadata);
                            }

                            DeploymentStatus status = item.getStatus();
                            if (status != null) {
                                planRepSum = status.getReplicas();
                                currentRepSum = status.getReadyReplicas();
                            }

                            //获取基本信息
                            baseInfo = setBaseInfo(item);

                            setVolumns(item, kcDeployment);

                            //获取运行时信息
                            runtimeInfos = setRuntimeInfo(item);

                            kcDeployment.setCurrentRepSum(currentRepSum)
                                    .setName(name)
                                    .setPlanRepSum(planRepSum)
                                    .setRuntimeStr(runtimeStr)
                                    .setBaseInfo(baseInfo)
                                    .setRuntimeInfos(runtimeInfos)
                                    .setDeploymentItem(item);
                            kcDeployments.add(kcDeployment);
                        }
                    }
                }
            }
        }
        return kcDeployments;
    }

    private static void setVolumns(Deployment deployment, KcDeployment kcDeployment) {
        DeploymentSpec spec = deployment.getSpec();
        if (spec != null) {
            PodTemplateSpec template = spec.getTemplate();
            if (template != null) {
                PodSpec templateSpec = template.getSpec();
                if (templateSpec != null) {
                    List<Volume> volumes = templateSpec.getVolumes();
                    if (CollectionUtils.isNotEmpty(volumes)) {
                        for (Volume volume : volumes) {
                            KcVolume kcVolume = setOneVolume(volume);
                            kcDeployment.getVolumes().add(kcVolume);
                        }
                    }
                }
            }
        }
    }

    public static KcVolume setOneVolume(Volume volume) {
        String name = volume.getName();
        KcVolume kcVolume = new KcVolume();
        kcVolume.setName(name);
        //处理hostPath类型
        HostPathVolumeSource hostPath = volume.getHostPath();
        if (hostPath != null) {
            kcVolume.setType(KcVolume.KcVolumeType.hostPath);
            KcVoHostPathParam kcVoHostPathParam = new KcVoHostPathParam();
            String hostPathType = hostPath.getType();
            if (StringUtils.isEmpty(hostPathType)) {
                hostPathType = "EmptyString";
            }
            kcVoHostPathParam.setPath(hostPath.getPath())
                    .setType(KcVoHostPathParam.KcVoHostPathType.valueOf(hostPathType));
            kcVolume.setParams(kcVoHostPathParam);
        }

        //处理NFS类型
        NFSVolumeSource nfs = volume.getNfs();
        if (nfs != null) {
            String server = nfs.getServer();
            String path = nfs.getPath();
            Boolean readOnly = nfs.getReadOnly();
            kcVolume.setType(KcVolume.KcVolumeType.NFS);
            KcVoNfsParam kcVoNfsParam = new KcVoNfsParam();
            kcVoNfsParam.setPath(path)
                    .setServer(server)
                    .setReadOnly(readOnly != null && readOnly);
            kcVolume.setParams(kcVoNfsParam);
        }

        //处理configmap类型
        ConfigMapVolumeSource configMap = volume.getConfigMap();
        if (configMap != null) {
            KcVoConfigMapParam kcVoConfigMapParam = new KcVoConfigMapParam();
            kcVoConfigMapParam.setConfigmap(configMap.getName());
            kcVolume.setType(KcVolume.KcVolumeType.configMap);
            List<KeyToPath> items = configMap.getItems();
            if (CollectionUtils.isNotEmpty(items)) {
                List<String[]> keyToPath = new ArrayList<>();
                for (KeyToPath item : items) {
                    keyToPath.add(new String[]{item.getKey(), item.getPath()});
                }
                kcVoConfigMapParam.setKeyToPath(keyToPath);
            }
            kcVolume.setParams(kcVoConfigMapParam);
        }
        return kcVolume;
    }

    /**
     * 重启服务
     * */
    public static void restartDeployment(String namespace, String name) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        kc.apps().deployments().inNamespace(namespace).withName(name)
                .rolling()
                .restart();
    }

    /**
     * 伸缩
     * */
    public static void scale(String namespace, String name, Integer maxReplicas) throws K8sClientException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        AppsAPIGroupDSL apps = kc.apps();

        if (apps != null) {
            MixedOperation<Deployment, DeploymentList, RollableScalableResource<Deployment>> deployments = apps.deployments();
            if (deployments != null) {
                NonNamespaceOperation<Deployment, DeploymentList, RollableScalableResource<Deployment>> nonNamespaceOperation = deployments.inNamespace(namespace);
                if (nonNamespaceOperation != null) {
                    RollableScalableResource<Deployment> rollableScalableResource = nonNamespaceOperation.withName(name);
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


    /**
     * 设置运行时信息
     * */
    @SuppressWarnings("DuplicatedCode")
    private static List<KcRuntimeInfo> setRuntimeInfo(Deployment deployment) throws K8sClientException {
        List<KcRuntimeInfo> kcConditions = null;
        DeploymentStatus depStatus = deployment.getStatus();
        if (depStatus != null) {
            List<DeploymentCondition> conditions = depStatus.getConditions();
            if (CollectionUtils.isNotEmpty(conditions)) {
                kcConditions  = new ArrayList<>();
                for (DeploymentCondition condition : conditions) {
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

                        lastUpdateTime = DateSafeUtils.parse(condition.getLastUpdateTime(), K8sConstants.TIME_PATTERN);
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


    public static Map<String, Object> setObjectMeta(ObjectMeta metadata) {
        Map<String, Object> result = new HashMap<>();
        List<String[]> annotations = new ArrayList<>();
        String name = null;
        String namespace = null;
        if (metadata != null) {
            //获取name
            name = metadata.getName();

            //获取labels
//            Map<String, String> metadataLabels = metadata.getLabels();
//            if (MapUtils.isNotEmpty(metadataLabels)) {
//                metadataLabels.forEach((key, val) -> {
//                    String[] label = new String[2];
//                    label[0] = key;
//                    label[1] = val;
//                    labels.add(label);
//                });
//            }
            //获取annotations
            Map<String, String> metadataAnnotations = metadata.getAnnotations();
            if (MapUtils.isNotEmpty(metadataAnnotations)) {
                metadataAnnotations.forEach((key, val) -> {
                    String[] annotation = new String[2];
                    annotation[0] = key;
                    annotation[1] = val;
                    annotations.add(annotation);
                });
            }

            //获取namespace
            namespace = metadata.getNamespace();
        }
        result.put("name", name);
        result.put("annotations", annotations);
        result.put("namespace", namespace);
        return result;
    }

    public static Map<String, Object> setSpec(LabelSelector selector,  PodTemplateSpec template) {
        Map<String, Object> result = new HashMap<>();
        List<String[]> matchLabels = new ArrayList<>();
        List<String[]> labels = new ArrayList<>();
        if (selector != null) {
            Map<String, String> mls = selector.getMatchLabels();
            if (MapUtils.isNotEmpty(mls)) {
                for (Map.Entry<String, String> entry : mls.entrySet()) {
                    String[] label = new String[2];
                    label[0] = entry.getKey();
                    label[1] = entry.getValue();
                    matchLabels.add(label);
                }
            }
        }

        if (template != null) {
            ObjectMeta metadata1 = template.getMetadata();
            if (metadata1 != null) {
                Map<String, String> metadataLabels = metadata1.getLabels();
                if (MapUtils.isNotEmpty(metadataLabels)) {
                    metadataLabels.forEach((key, val) -> {
                        String[] label = new String[2];
                        label[0] = key;
                        label[1] = val;
                        labels.add(label);
                    });
                }
            }
        }
        result.put("matchLabels", matchLabels);
        result.put("labels", labels);
        return result;
    }

    /**
     *  设置deployment的基本信息
     * */
    @SuppressWarnings({"ConstantConditions", "DuplicatedCode"})
    private static KcDepBaseInfo setBaseInfo(Deployment deployment) {
        KcDepBaseInfo baseInfo = new KcDepBaseInfo();
        String type = "deployment";
        String name;
        List<String[]> labels;
        List<String[]> annotations;
        String description = null;
        Integer currentRepSum = null;
        Integer planRepSum = null;
        String namespace;

        ObjectMeta metadata = deployment.getMetadata();

        Map<String, Object> metaDataResultMap = setObjectMeta(metadata);
        name = (String) metaDataResultMap.get("name");
        namespace = (String) metaDataResultMap.get("namespace");
        annotations = (List<String[]>) metaDataResultMap.get("annotations");

        DeploymentSpec spec = deployment.getSpec();
        List<String[]> matchLabels;
        LabelSelector selector = spec.getSelector();
        PodTemplateSpec template = spec.getTemplate();
        Map<String, Object> specResultMap = setSpec(selector, template);
        matchLabels = (List<String[]>) specResultMap.get("matchLabels");
        labels = (List<String[]>) specResultMap.get("labels");

        DeploymentStatus status = deployment.getStatus();
        if (status != null) {
            planRepSum = status.getReplicas();
            currentRepSum = status.getReadyReplicas();
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

    /**
     * 获取自动扩容信息
     * @since jdk1.8
     * @date 2021/1/22
     * @param namespace 命名空间
     * @param name deployment的名字
     * @throws ParseException ParseException
     * @return com.iscas.common.k8s.tools.model.autoscale.KcAutoscale
     */
    public static KcAutoscale getAutoScale(String namespace, String name) throws ParseException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        AutoscalingAPIGroupDSL autoscaling = kc.autoscaling();
        Resource<HorizontalPodAutoscaler> resource = autoscaling.v2beta2().horizontalPodAutoscalers()
                .inNamespace(namespace).withName(name);
        HorizontalPodAutoscaler horizontalPodAutoscaler = resource.get();
        if (horizontalPodAutoscaler == null) {
            return null;
        }
        HorizontalPodAutoscalerStatus status = horizontalPodAutoscaler.getStatus();
        ObjectMeta metadata = horizontalPodAutoscaler.getMetadata();
        HorizontalPodAutoscalerSpec spec = horizontalPodAutoscaler.getSpec();
        KcAutoscale kcAutoscale = new KcAutoscale();
        kcAutoscale.setNamespace(metadata.getNamespace())
                .setName(metadata.getName())
                .setMaxReplicas(spec.getMaxReplicas())
                .setMinReplicas(spec.getMinReplicas());

        //扩容条件
        List<MetricSpec> metrics = spec.getMetrics();
        if (CollectionUtils.isNotEmpty(metrics)) {
            for (MetricSpec metric : metrics) {
                String type = metric.getType();
                if (Objects.equals(type, "Resource")) {
                    ResourceMetricSource resourceMetricSource = metric.getResource();
                    String name1 = resourceMetricSource.getName();
                    if (StringRaiseUtils.multiEqualsOr(name1, "cpu", "memory")) {
                        MetricTarget target = resourceMetricSource.getTarget();
                        if (target != null) {
                            String type1 = target.getType();
                            if (Objects.equals("Utilization", type1)) {
                                Integer averageUtilization = target.getAverageUtilization();
                                KcAutoScaleResource kcAutoScaleResource = new KcAutoScaleResource();
                                kcAutoScaleResource.setAverageUtilization(averageUtilization)
                                        .setType("Utilization");
                                if (StringUtils.equals(name1, "cpu")) {
                                    kcAutoscale.setCpuResource(kcAutoScaleResource);
                                } else if (StringUtils.equals(name1, "memory")) {
                                    kcAutoscale.setMemoryResource(kcAutoScaleResource);
                                }
                            }
                        }
                    }
                }
            }
        }

        //设置当前的状态
        kcAutoscale.setCurrentReplicas(status.getCurrentReplicas());
        if (status.getLastScaleTime() != null) {
            Date lastScaleTime = DateSafeUtils.parse(status.getLastScaleTime(), K8sConstants.TIME_PATTERN);
            lastScaleTime = CommonUtils.timeOffset(lastScaleTime);
            kcAutoscale.setLastScaleTime(lastScaleTime);
        }

        List<MetricStatus> currentMetrics = status.getCurrentMetrics();
        if (CollectionUtils.isNotEmpty(currentMetrics)) {
            for (MetricStatus currentMetric : currentMetrics) {
                String metricsType = currentMetric.getType();
                if (Objects.equals(metricsType, "Resource")) {
                    ResourceMetricStatus statusResource = currentMetric.getResource();
                    String name1 = statusResource.getName();
                    if (StringRaiseUtils.multiEqualsOr(name1, "cpu", "memory")) {
                        MetricValueStatus current = statusResource.getCurrent();
                        Integer averageUtilization = current.getAverageUtilization();
                        Quantity averageValue = current.getAverageValue();
                        if (StringUtils.equals(name1, "cpu") && kcAutoscale.getCpuResource() != null) {
                            kcAutoscale.getCpuResource().setCurrentAverageUtilization(String.valueOf(averageUtilization));
                            kcAutoscale.getCpuResource().setCurrentAverageValue(String.valueOf(MathUtils.scale(Quantity.getAmountInBytes(averageValue).doubleValue() * 1000, 4)));
                        } else if (StringUtils.equals(name1, "memory") && kcAutoscale.getCpuResource() != null) {
                            kcAutoscale.getMemoryResource().setCurrentAverageUtilization(String.valueOf(averageUtilization));
                            kcAutoscale.getMemoryResource().setCurrentAverageValue(String.valueOf(Quantity.getAmountInBytes(averageValue).longValue() / 1024 / 1024));
                        }
                    }
                }
            }
        }
        return kcAutoscale;
    }

    public static void deleteAutoScale(String namespace, String name) throws BaseException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        NonNamespaceOperation<HorizontalPodAutoscaler, HorizontalPodAutoscalerList, Resource<HorizontalPodAutoscaler>> nonNamespaceOperation = kc.autoscaling().v2beta2().horizontalPodAutoscalers().inNamespace(namespace);
        if (nonNamespaceOperation != null) {
            Resource<HorizontalPodAutoscaler> horizontalPodAutoscalerResource = nonNamespaceOperation.withName(name);
            if (horizontalPodAutoscalerResource == null) {
                throw new BaseException("不存在的扩容信息");
            }
            horizontalPodAutoscalerResource.delete();
        } else {
            throw new BaseException("不存在的扩容信息");
        }
    }

    public static void autoScale(KcAutoscale autoscale, String kind) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        List<MetricSpec> metricSpecs = new ArrayList<>();
        KcAutoScaleResource memoryResource = autoscale.getMemoryResource();
        KcAutoScaleResource cpuResource = autoscale.getCpuResource();
        if (memoryResource != null &&memoryResource.getAverageUtilization() != 0) {
            int averageUtilization = memoryResource.getAverageUtilization();
            MetricSpec memoryMetricsSpec = new MetricSpecBuilder()
                    .withType("Resource")
                    .withNewResource()
                    .withName("memory")
                    .withNewTarget()
                    .withType("Utilization")
                    .withAverageUtilization(averageUtilization)
                    .endTarget()
                    .endResource()
                    .build();
            metricSpecs.add(memoryMetricsSpec);
        }
        if (cpuResource != null && cpuResource.getAverageUtilization() != 0) {
            int averageUtilization = cpuResource.getAverageUtilization();
            MetricSpec cpuMetricsSpec = new MetricSpecBuilder()
                    .withType("Resource")
                    .withNewResource()
                    .withName("cpu")
                    .withNewTarget()
                    .withType("Utilization")
                    .withAverageUtilization(averageUtilization)
                    .endTarget()
                    .endResource()
                    .build();
            metricSpecs.add(cpuMetricsSpec);
        }

        HorizontalPodAutoscaler horizontalPodAutoscaler = new HorizontalPodAutoscalerBuilder()
                .withNewMetadata().withName(autoscale.getName()).withNamespace(autoscale.getNamespace()).endMetadata()
                .withNewSpec()
                .withNewScaleTargetRef()
                .withApiVersion("apps/v1")
                .withKind(kind) //暂时只支持Deployment
                .withName(autoscale.getName())
                .endScaleTargetRef()
                .withMinReplicas(autoscale.getMinReplicas())
                .withMaxReplicas(autoscale.getMaxReplicas())
                .addToMetrics(metricSpecs.toArray(new MetricSpec[0]))
                .endSpec()
                .build();
        boolean edit = false;
        NonNamespaceOperation<HorizontalPodAutoscaler, HorizontalPodAutoscalerList, Resource<HorizontalPodAutoscaler>> noneName = kc.autoscaling().v2beta2().horizontalPodAutoscalers().inNamespace(autoscale.getNamespace());
        if (noneName != null) {
            Resource<HorizontalPodAutoscaler> horizonResource = noneName.withName(autoscale.getName());
            if (horizonResource.get() != null) {
                horizonResource.edit(n -> horizontalPodAutoscaler);
                edit = true;
            }
        }
        if (!edit) {
            kc.autoscaling().v2beta2().horizontalPodAutoscalers().inNamespace(autoscale.getNamespace()).create(horizontalPodAutoscaler);
        }
    }
}
