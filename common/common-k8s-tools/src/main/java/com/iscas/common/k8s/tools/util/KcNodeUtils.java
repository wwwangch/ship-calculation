package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConstants;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.node.*;
import com.iscas.common.tools.core.date.DateSafeUtils;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetrics;
import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetricsList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 节点信息操作工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 16:04
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class KcNodeUtils {
    /**下层模块要调用上层的函数，暂时先这么处理把*/
    public static Object storeService;

    public static final ThreadLocal<Boolean> METRICS_TIMEOUT = new ThreadLocal<>();
    public static Long METRICS_RESTART_TIME = 0L;

    private KcNodeUtils() {
    }

    private static KcNode getOneNode(Node node, KubernetesClient kc) throws K8sClientException {
        KcNode kcNode = new KcNode();

        String name = null;
        String apiVersion;
        String address = null;
        String status = "ready";
        String runTimeStr = null;
        Date createTime;

        //注入值
        apiVersion = node.getApiVersion();
        ObjectMeta metadata = node.getMetadata();
        if (metadata != null) {
            name = metadata.getName();
            String creationTimestamp = metadata.getCreationTimestamp();
            try {
                createTime = DateSafeUtils.parse(creationTimestamp, K8sConstants.TIME_PATTERN);
                createTime = CommonUtils.timeOffset(createTime);
            } catch (ParseException e) {
                throw new K8sClientException("创建时间类型转换出错", e);
            }
            runTimeStr = CommonUtils.getTimeDistance(createTime);
        }
        NodeStatus nodeStatus = node.getStatus();
        if (nodeStatus != null) {
            List<NodeAddress> addresses = nodeStatus.getAddresses();
            if (CollectionUtils.isNotEmpty(addresses)) {
                address = addresses.get(0).getAddress();
            }
            List<NodeCondition> conditions = nodeStatus.getConditions();
            if (CollectionUtils.isNotEmpty(conditions)) {
                boolean notReady = conditions.stream().noneMatch(nodeCondition ->
                        Objects.equals(nodeCondition.getType(), "Ready") && Objects.equals(nodeCondition.getStatus(), "True"));
                if (notReady) {
                    status = "notReady";
                }
            }
        }

        //获取基本信息
        setNodeBaseInfo(kcNode, node);

        //获取容器组信息
        PodList podList = kc.pods().list();
        setNodePods(kcNode, podList, name);

        //获取镜像信息
        setNodeImages(kcNode, node);

        //判断是否为master节点
        NodeSpec spec = node.getSpec();
        if (spec != null) {
            List<Taint> taints = spec.getTaints();
            if (CollectionUtils.isNotEmpty(taints)) {
                for (Taint taint : taints) {
                    String key = taint.getKey();
                    if (Objects.equals("node-role.kubernetes.io/master", key)) {
                        kcNode.setMaster(true);
                        break;
                    }
                }
            }
        }
        //如果没有污点，再判断一次label
        if (!kcNode.isMaster()) {
            Map<String, String> labels = node.getMetadata().getLabels();
            if (labels != null) {
                for (String s : labels.keySet()) {
                    if (StringUtils.equalsAny(s, "node-role.kubernetes.io/control-plane",
                            "node-role.kubernetes.io/master")) {
                        kcNode.setMaster(true);
                        break;
                    }
                }
            }
        }

        kcNode.setApiVersion(apiVersion)
                .setAddress(address)
                .setName(name)
                .setRunTimeStr(runTimeStr)
                .setStatus(status);

        return kcNode;
    }

    /**
     * 获取节点的镜像信息
     */
    private static void setNodeImages(KcNode kcNode, Node node) {
        NodeStatus nodeStatus = node.getStatus();
        if (nodeStatus != null) {
            List<ContainerImage> containerImages = nodeStatus.getImages();
            if (CollectionUtils.isNotEmpty(containerImages)) {
                List<KcNodeImage> images = new ArrayList<>();
                for (ContainerImage containerImage : containerImages) {
                    //镜像名字
                    List<String> names;
                    //大小
                    Long size;
                    KcNodeImage nodeImage = new KcNodeImage();

                    names = containerImage.getNames();
                    size = containerImage.getSizeBytes();

                    nodeImage.setNames(names)
                            .setSize(size);
                    images.add(nodeImage);
                }
                kcNode.setImages(images);
            }
        }
    }

    /**
     * 获取容器组信息
     */
    private static void setNodePods(KcNode kcNode, PodList podList, String nodeName) throws K8sClientException {
        if (podList != null) {
            List<Pod> podItems = podList.getItems();
            if (CollectionUtils.isNotEmpty(podItems)) {
                for (Pod podItem : podItems) {
                    PodSpec spec = podItem.getSpec();
                    if (spec != null) {
                        if (Objects.equals(spec.getNodeName(), nodeName)) {
                            //此pod为当前节点的，获取其他属性
                            List<KcNodePod> pods = kcNode.getPods();
                            if (pods == null) {
                                pods = new ArrayList<>();
                                kcNode.setPods(pods);
                            }

                            //命名空间
                            String namespace = null;
                            //名称
                            String name = null;
                            //状态
                            String status = null;
                            //容器组IP
                            String innerIp = null;
                            //创建时间
                            Date createTime = null;
                            //运行时间
                            String runTimeStr = null;

                            ObjectMeta metadata = podItem.getMetadata();
                            if (metadata != null) {
                                //命名空间
                                namespace = metadata.getNamespace();
                                //名称
                                name = metadata.getName();
                            }

                            PodStatus podStatus = podItem.getStatus();
                            if (podStatus != null) {
                                //状态
                                status = podStatus.getPhase();
                                //内部IP
                                innerIp = podStatus.getPodIP();

                                String startTime = podStatus.getStartTime();
                                if (startTime == null) {
                                    runTimeStr = "未知";
                                } else {
                                    try {
                                        createTime = DateSafeUtils.parse(startTime, K8sConstants.TIME_PATTERN);
                                        createTime = CommonUtils.timeOffset(createTime);
                                        runTimeStr = CommonUtils.getTimeDistance(createTime);
                                    } catch (Exception e) {
                                        throw new K8sClientException("创建时间类型转换出错", e);
                                    }
                                }
                            }

                            KcNodePod nodePod = new KcNodePod();
                            nodePod.setCreateTime(createTime)
                                    .setInnerIp(innerIp)
                                    .setName(name)
                                    .setNamespace(namespace)
                                    .setRunTimeStr(runTimeStr)
                                    .setStatus(status);
                            pods.add(nodePod);
                        }
                    }
                }
            }
        }
    }

    /**
     * 为节点设置基本信息
     */
    @SuppressWarnings("ConstantConditions")
    private static void setNodeBaseInfo(KcNode kcNode, Node node) throws K8sClientException {
        KcNodeBaseInfo baseInfo = new KcNodeBaseInfo();

        String name;
        Date createTime = null;
        String runTimeStr;
        List<String[]> labels = null;
        List<String[]> annotations = null;
        List<KcNodeBaseInfoTaint> taints = null;
        double cpu = 0;
        long transientStorage = 0L;
        long memory = 0L;
        Integer maxPods = null;
        String cidr = null;

        //todo 未找到方式获取ipTunnelAddr
        String ipTunnelAddr = null;
        @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming") String internalIP = null;
        String hostname = null;
        String os = null;
        String architecture = null;
        String osImage = null;
        String osKernel = null;
        String containerEngine = null;
        String kubeletVersion = null;
        String kubeProxyVersion = null;

        name = kcNode.getName();
        runTimeStr = kcNode.getRunTimeStr();

        ObjectMeta metadata = node.getMetadata();
        if (metadata != null) {
            name = metadata.getName();
            String creationTimestamp = metadata.getCreationTimestamp();
            try {
                createTime = DateSafeUtils.parse(creationTimestamp, K8sConstants.TIME_PATTERN);
            } catch (ParseException e) {
                throw new K8sClientException("创建时间类型转换出错", e);
            }
            runTimeStr = CommonUtils.getTimeDistance(createTime);

            //获取labels
            Map<String, String> metaLabels = metadata.getLabels();
            labels = getLabelsOrAnnotations(metaLabels);

            //获取annotations
            Map<String, String> metaAnnotations = metadata.getAnnotations();
            annotations = getLabelsOrAnnotations(metaAnnotations);
        }

        NodeSpec spec = node.getSpec();
        if (spec != null) {
            //获取taints
            List<Taint> specTaints = spec.getTaints();
            if (CollectionUtils.isNotEmpty(specTaints)) {
                taints = new ArrayList<>();
                for (Taint specTaint : specTaints) {
                    KcNodeBaseInfoTaint kcTaint = new KcNodeBaseInfoTaint();
                    kcTaint.setKey(specTaint.getKey());
                    kcTaint.setValue(specTaint.getValue());
                    kcTaint.setEffect(specTaint.getEffect());
                    taints.add(kcTaint);
                }
            }

            //获取cidr
            cidr = spec.getPodCIDR();

        }

        NodeStatus nodeStatus = node.getStatus();
        if (nodeStatus != null) {
            Map<String, Quantity> allocatable = nodeStatus.getAllocatable();
            if (allocatable != null) {
                //获取cpu
                Quantity cpuQuantity = allocatable.get("cpu");
                if (cpuQuantity != null) {
                    cpu = (Quantity.getAmountInBytes(cpuQuantity)).doubleValue();
                }

                //获取transientStorage
                Quantity storageQuantity = allocatable.get("ephemeral-storage");
                if (storageQuantity != null) {
                    transientStorage = (Quantity.getAmountInBytes(storageQuantity)).longValue() / 1024;
                }

                //获取maxPods
                Quantity podsQuantity = allocatable.get("pods");
                if (podsQuantity != null) {
                    maxPods = (Quantity.getAmountInBytes(podsQuantity)).intValue();
                }

                //获取memory
                Quantity memoryQuantity = allocatable.get("memory");
                if (memoryQuantity != null) {
                    String amount = memoryQuantity.getAmount();
                    if (amount != null) {
                        memory = (Quantity.getAmountInBytes(memoryQuantity)).longValue() / 1024;
                    }
                }

            }

            List<NodeAddress> addresses = nodeStatus.getAddresses();
            if (CollectionUtils.isNotEmpty(addresses)) {
                for (NodeAddress address : addresses) {
                    String type = address.getType();
                    if (Objects.equals("InternalIP", type)) {
                        //获取internalIP
                        internalIP = address.getAddress();
                    } else if (Objects.equals("Hostname", type)) {
                        //获取hostname
                        hostname = address.getAddress();
                    }
                }
            }

            NodeSystemInfo nodeInfo = nodeStatus.getNodeInfo();
            if (nodeInfo != null) {
                //获取os
                os = nodeInfo.getOperatingSystem();
                //获取architecture
                architecture = nodeInfo.getArchitecture();
                //获取osImage
                osImage = nodeInfo.getOsImage();
                //获取osKernel
                osKernel = nodeInfo.getKernelVersion();
                //获取containerEngine
                containerEngine = nodeInfo.getContainerRuntimeVersion();
                //获取kubeletVersion
                kubeletVersion = nodeInfo.getKubeletVersion();
                //获取kubeProxyVersion
                kubeProxyVersion = nodeInfo.getKubeProxyVersion();
            }

        }
        baseInfo.setName(name)
                .setCreateTime(createTime)
                .setRunTimeStr(runTimeStr)
                .setLabels(labels)
                .setAnnotations(annotations)
                .setTaints(taints)
                .setCpu(cpu)
                .setTransientStorage(transientStorage)
                .setMaxPods(maxPods)
                .setMemory(memory)
                .setCidr(cidr)
                .setIpTunnelAddr(ipTunnelAddr)
                .setInternalIP(internalIP)
                .setHostname(hostname)
                .setOs(os)
                .setArchitecture(architecture)
                .setOsImage(osImage)
                .setOsKernel(osKernel)
                .setContainerEngine(containerEngine)
                .setKubeletVersion(kubeletVersion)
                .setKubeProxyVersion(kubeProxyVersion);

        //注入condition
        setNodeBaseInfoCondition(baseInfo, node);

        kcNode.setBaseInfo(baseInfo);
    }

    private static List<String[]> getLabelsOrAnnotations(Map<String, String> metaLabels) {
        if (MapUtils.isNotEmpty(metaLabels)) {
            List<String[]> labels = new ArrayList<>();
            for (Map.Entry<String, String> entry : metaLabels.entrySet()) {
                String[] labelArray = new String[2];
                labelArray[0] = entry.getKey();
                labelArray[1] = entry.getValue();
                labels.add(labelArray);
            }
            return labels;
        }
        return null;
    }

    private static void setNodeBaseInfoCondition(KcNodeBaseInfo baseInfo, Node node) throws K8sClientException {
        NodeStatus nodeStatus = node.getStatus();
        if (nodeStatus != null) {
            List<NodeCondition> conditions = nodeStatus.getConditions();
            if (CollectionUtils.isNotEmpty(conditions)) {
                List<KcNodeRuntimeInfo> kcConditions = new ArrayList<>();
                for (NodeCondition condition : conditions) {
                    KcNodeRuntimeInfo kcCondtion = new KcNodeRuntimeInfo();

                    String type;
                    String status;
                    Date lastHeartbeatTime;
                    Date lastTransitionTime;
                    String reason;
                    String message;

                    type = condition.getType();
                    status = condition.getStatus();
                    try {
                        lastHeartbeatTime = DateSafeUtils.parse(condition.getLastHeartbeatTime(), K8sConstants.TIME_PATTERN);
                        lastTransitionTime = DateSafeUtils.parse(condition.getLastTransitionTime(), K8sConstants.TIME_PATTERN);
                    } catch (ParseException e) {
                        throw new K8sClientException("时间类型转换出错", e);
                    }
                    reason = condition.getReason();
                    message = condition.getMessage();
                    kcCondtion.setType(type)
                            .setLastHeartbeatTime(lastHeartbeatTime)
                            .setLastTransitionTime(lastTransitionTime)
                            .setMessage(message)
                            .setReason(reason)
                            .setStatus(status);
                    kcConditions.add(kcCondtion);
                }
                baseInfo.setConditions(kcConditions);
            }
        }
    }

    /**
     * 查看某个节点存在如否
     */
    public static boolean checkNodeExsits(String nodeName) {
        @Cleanup KubernetesClient kcClient = K8sClient.getInstance();
        NonNamespaceOperation<Node, NodeList, Resource<Node>> nodes = kcClient.nodes();
        Resource<Node> resource = nodes.withName(nodeName);
        if (resource == null) {
            return false;
        }

        Node node = resource.get();
        return node != null;
    }

    /**
     * 获取节点信息，不使用metricsServer,不获取资源使用情况
     */
    public static List<KcNode> getNodes() throws K8sClientException {
        return getNodes(false);
    }

    /**
     * 获取节点信息，使用metricsServer,获取资源使用情况
     */
    public static List<KcNode> getNodes(boolean useMetricsServer) throws K8sClientException {
        List<KcNode> kcNodes = null;
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        NonNamespaceOperation<Node, NodeList, Resource<Node>> nodes = kc.nodes();
        if (nodes != null) {
            NodeList nodeList = nodes.list();
            if (nodeList != null) {
                List<Node> items = nodeList.getItems();
                if (CollectionUtils.isNotEmpty(items)) {
                    kcNodes = new ArrayList<>();
                    for (Node item : items) {
                        KcNode kcNode = getOneNode(item, kc);
                        kcNodes.add(kcNode);
                    }
                }
            }
        }

        //通过metric-server获取资源情况
        if (useMetricsServer) {
            getMetricsServerInfo(kcNodes);
        }
        return kcNodes;
    }

    private static void reflectSetMetricsTimeout(boolean timeoutFlag) {
        try {
            Method put = storeService.getClass().getDeclaredMethod("put", String.class, Object.class);
            put.invoke(storeService, "CACHE_KEY_METRICS_TIMEOUT", timeoutFlag);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static void getMetricsServerInfo(List<KcNode> kcNodes) {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        NodeMetricsList metrics;
        try {
            metrics = kc.top().nodes().metrics();
            reflectSetMetricsTimeout(false);
        } catch (Exception e) {
            System.err.println("调用metrics-server出错：" + e.getMessage());
            e.printStackTrace();
            reflectSetMetricsTimeout(true);
            return;
        }

        Map<String, KcNode> kcNodeMap = kcNodes.stream().collect(Collectors.toMap(KcNode::getName, kcNode -> kcNode));
        for (NodeMetrics nodeMetrics : metrics.getItems()) {
            String name = nodeMetrics.getMetadata().getName();
            Quantity cpu = nodeMetrics.getUsage().get("cpu");
            Quantity memory = nodeMetrics.getUsage().get("memory");
            if (kcNodeMap.containsKey(name)) {
                KcNode kcNode = kcNodeMap.get(name);
                KcNodeBaseInfo baseInfo = kcNode.getBaseInfo();
                double usedCpu = (Quantity.getAmountInBytes(cpu)).doubleValue();
                long usedMemory = (Quantity.getAmountInBytes(memory)).longValue() / 1024;

                baseInfo.setUsedCpu(usedCpu)
                        .setUsedMemory(usedMemory)
                        .setUsedPods(kcNode.getPods() == null ? 0 : kcNode.getPods().size());
            }
        }
    }

    private static int getIndex(List<Taint> taints, KcNodeBaseInfoTaint kcTaint) {
        if (CollectionUtils.isNotEmpty(taints)) {
            for (int i = 0; i < taints.size(); i++) {
                if (Objects.equals(taints.get(i).getKey(), kcTaint.getKey())) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 编辑污点
     *
     * @param nodeName 节点名称
     * @param kcTaint  编辑的污点
     * @throws K8sClientException K8S异常
     * @date 2019/12/4
     * @since jdk1.8
     */
    public static void editTaint(String nodeName, KcNodeBaseInfoTaint kcTaint) throws K8sClientException {
        Pair<Resource<Node>, Node> resourceAndNode = getResourceAndNode(nodeName);
        NodeSpec spec = resourceAndNode.getRight().getSpec();
        if (spec == null) {
            throw new K8sClientException(String.format("未找到对应的污点\"%s\",无法编辑", kcTaint.getKey()));
        }
        List<Taint> taints = spec.getTaints();
        int index = getIndex(taints, kcTaint);
        if (index == -1) {
            throw new K8sClientException(String.format("未找到对应的污点\"%s\",无法编辑", kcTaint.getKey()));
        }
        Taint taint = taints.get(index);
        taint.setEffect(kcTaint.getEffect());
        taint.setKey(kcTaint.getKey());
        taint.setValue(kcTaint.getValue());
        resourceAndNode.getLeft().edit(n -> new NodeBuilder(n).editSpec().withTaints(taints)
                .endSpec().build());

    }


    /**
     * 新增污点
     *
     * @param nodeName 节点名称
     * @param kcTaint  新增的污点
     * @throws K8sClientException k8s异常
     * @date 2019/12/4
     * @since jdk1.8
     */
    public static void addTaint(String nodeName, KcNodeBaseInfoTaint kcTaint) throws K8sClientException {
        Pair<Resource<Node>, Node> resourceAndNode = getResourceAndNode(nodeName);
        NodeSpec spec = resourceAndNode.getRight().getSpec();
        if (spec == null) {
            throw new K8sClientException("未找到对应节点的spec");
        }
        List<Taint> taints = spec.getTaints();
        int index = getIndex(taints, kcTaint);
        if (index != -1) {
            throw new K8sClientException(String.format("找到对应的污点\"%s\",不可再新增同样的污点", kcTaint.getKey()));
        }
        resourceAndNode.getLeft().edit(n -> new NodeBuilder(n).editSpec()
                .addNewTaint()
                .withKey(kcTaint.getKey())
                .withNewEffect(kcTaint.getEffect())
                //todo 不知道为啥为Null会报错，为Null时将它置为""
                .withNewValue(kcTaint.getValue() == null ? "" : kcTaint.getValue())
                .endTaint()
                .endSpec().build());
    }

    /**
     * 新增污点
     *
     * @param nodeName 节点名称
     * @param taintKey 污点的key
     * @throws K8sClientException K8S异常
     * @date 2019/12/4
     * @since jdk1.8
     */
    public static void deleteTaint(String nodeName, String taintKey) throws K8sClientException {
        Pair<Resource<Node>, Node> resourceAndNode = getResourceAndNode(nodeName);
        Taint taint = null;
        NodeSpec spec = resourceAndNode.getRight().getSpec();
        if (spec == null) {
            throw new K8sClientException("未找到对应的spec");
        }
        List<Taint> taints = spec.getTaints();
        if (CollectionUtils.isNotEmpty(taints)) {
            for (int i = taints.size() - 1; i >= 0; i--) {
                if (Objects.equals(taints.get(i).getKey(), taintKey)) {
                    taint = taints.remove(i);
                    break;
                }
            }
        }
        if (taint == null) {
            throw new K8sClientException(String.format("未找到对应的污点\"%s\",无需删除", taintKey));
        }
        resourceAndNode.getLeft().edit(n -> new NodeBuilder(n).editSpec().withTaints(taints)
                .endSpec().build());
    }

    /**
     * 新增label
     *
     * @param nodeName 节点名称
     * @param label    新增的label
     * @throws K8sClientException K8S异常
     * @date 2019/12/4
     * @since jdk1.8
     */
    public static void addLabel(String nodeName, String[] label) throws K8sClientException {
        Triple<Resource<Node>, Node, Map<String, String>> resourceNodeLabels = getResourceNodeLabels(nodeName);
        Map<String, String> labels = resourceNodeLabels.getRight();
        if (MapUtils.isNotEmpty(labels)) {
            if (labels.containsKey(label[0])) {
                throw new K8sClientException(String.format("找到对应的label\"%s\",不可再新增同样的label", label[0]));
            }
        }
        resourceNodeLabels.getLeft().edit(n -> new NodeBuilder(n).editMetadata()
                .addToLabels(label[0], label[1])
                .endMetadata().build());
    }


    /**
     * 编辑label
     *
     * @param nodeName 节点名称
     * @param label    编辑的label
     * @throws K8sClientException K8S异常
     * @date 2019/12/4
     * @since jdk1.8
     */
    public static void editLabel(String nodeName, String[] label) throws K8sClientException {
        Triple<Resource<Node>, Node, Map<String, String>> resourceNodeLabels = getResourceNodeLabels(nodeName);
        Map<String, String> labels = resourceNodeLabels.getRight();
        if (MapUtils.isEmpty(labels)) {
            throw new K8sClientException(String.format("找不到对应的label\"%s\",不可编辑", label[0]));
        }
        if (!labels.containsKey(label[0])) {
            throw new K8sClientException(String.format("找不到对应的label\"%s\",不可编辑", label[0]));
        }
        resourceNodeLabels.getLeft().edit(n -> new NodeBuilder(n).editMetadata()
                .addToLabels(label[0], label[1])
                .endMetadata()
                .build());

    }

    /**
     * 删除label
     *
     * @param nodeName 节点名称
     * @param labelKey label的key
     * @throws K8sClientException K8S异常
     * @date 2019/12/4
     * @since jdk1.8
     */
    public static void deleteLabel(String nodeName, String labelKey) throws K8sClientException {
        Triple<Resource<Node>, Node, Map<String, String>> resourceNodeLabels = getResourceNodeLabels(nodeName);
        Map<String, String> labels = resourceNodeLabels.getRight();
        if (MapUtils.isEmpty(labels)) {
            throw new K8sClientException(String.format("找不到对应的label\"%s\",无需删除", labelKey));
        }
        if (!labels.containsKey(labelKey)) {
            throw new K8sClientException(String.format("找不到对应的label\"%s\",无需删除", labelKey));
        }

        resourceNodeLabels.getLeft().edit(n -> new NodeBuilder(n).editMetadata()
                .removeFromLabels(labelKey)
                .endMetadata()
                .build());
    }

    /**
     * 新增annotations
     *
     * @param nodeName   节点名称
     * @param annotation 新增的annotation
     * @throws K8sClientException K8S异常
     * @date 2019/12/4
     * @since jdk1.8
     */
    public static void addAnnotation(String nodeName, String[] annotation) throws K8sClientException {
        Triple<Resource<Node>, Node, Map<String, String>> resourceNodeAnnotations = getResourceNodeAnnotations(nodeName);
        Map<String, String> annotationMap = resourceNodeAnnotations.getRight();
        if (MapUtils.isNotEmpty(annotationMap)) {
            if (annotationMap.containsKey(annotation[0])) {
                throw new K8sClientException(String.format("找到对应的annotation\"%s\",不可再新增同样的annotation", annotation[0]));
            }
        }
        resourceNodeAnnotations.getLeft().edit(n -> new NodeBuilder(n).editMetadata()
                .addToAnnotations(annotation[0], annotation[1])
                .endMetadata()
                .build());
    }

    /**
     * 编辑annotation
     *
     * @param nodeName    节点名称
     * @param annotations 编辑的annotations
     * @throws K8sClientException K8S异常
     * @date 2019/12/4
     * @since jdk1.8
     */
    public static void editAnnotation(String nodeName, String[] annotations) throws K8sClientException {
        Triple<Resource<Node>, Node, Map<String, String>> resourceNodeAnnotations = getResourceNodeAnnotations(nodeName);
        Map<String, String> anno = resourceNodeAnnotations.getRight();
        if (MapUtils.isEmpty(anno)) {
            throw new K8sClientException(String.format("找不到对应的annotation\"%s\",不可编辑", annotations[0]));
        }
        if (!anno.containsKey(annotations[0])) {
            throw new K8sClientException(String.format("找不到对应的annotation\"%s\",不可编辑", annotations[0]));
        }

        resourceNodeAnnotations.getLeft().edit(n -> new NodeBuilder(n).editMetadata()
                .addToAnnotations(annotations[0], annotations[1])
                .endMetadata()
                .build());

    }


    /**
     * 删除annotation
     *
     * @param nodeName      节点名称
     * @param annotationKey annotation的key
     * @throws K8sClientException K8S异常
     * @date 2019/12/4
     * @since jdk1.8
     */
    public static void deleteAnnotation(String nodeName, String annotationKey) throws K8sClientException {
        Triple<Resource<Node>, Node, Map<String, String>> resourceNodeAnnotations = getResourceNodeAnnotations(nodeName);
        Map<String, String> anno = resourceNodeAnnotations.getRight();
        if (MapUtils.isEmpty(anno)) {
            throw new K8sClientException(String.format("找不到对应的label\"%s\",无需删除", annotationKey));
        }
        if (!anno.containsKey(annotationKey)) {
            throw new K8sClientException(String.format("找不到对应的label\"%s\",无需删除", annotationKey));
        }

        resourceNodeAnnotations.getLeft().edit(n -> new NodeBuilder(n).editMetadata()
                .removeFromAnnotations(annotationKey)
                .endMetadata()
                .build());
    }

    private static Pair<Resource<Node>, Node> getResourceAndNode(String nodeName) throws K8sClientException {
        @Cleanup KubernetesClient kcClient = K8sClient.getInstance();
        NonNamespaceOperation<Node, NodeList, Resource<Node>> nodes = kcClient.nodes();
        Resource<Node> resource = nodes.withName(nodeName);
        if (resource == null) {
            throw new K8sClientException(String.format("节点名称\"%s\"不存在", nodeName));
        }

        Node node = resource.get();
        if (node == null) {
            throw new K8sClientException(String.format("节点名称\"%s\"不存在", nodeName));
        }
        return Pair.of(resource, node);
    }

    private static Triple<Resource<Node>, Node, Map<String, String>> getResourceNodeLabels(String nodeName) throws K8sClientException {
        Pair<Resource<Node>, Node> resourceAndNode = getResourceAndNode(nodeName);
        ObjectMeta metadata = resourceAndNode.getRight().getMetadata();
        if (metadata == null) {
            throw new K8sClientException("未找到对应节点的spec");
        }
        return Triple.of(resourceAndNode.getLeft(), resourceAndNode.getRight(), metadata.getLabels());
    }

    private static Triple<Resource<Node>, Node, Map<String, String>> getResourceNodeAnnotations(String nodeName) throws K8sClientException {
        Pair<Resource<Node>, Node> resourceAndNode = getResourceAndNode(nodeName);
        ObjectMeta metadata = resourceAndNode.getRight().getMetadata();
        if (metadata == null) {
            throw new K8sClientException("未找到对应节点的spec");
        }
        return Triple.of(resourceAndNode.getLeft(), resourceAndNode.getRight(), metadata.getAnnotations());
    }
}
