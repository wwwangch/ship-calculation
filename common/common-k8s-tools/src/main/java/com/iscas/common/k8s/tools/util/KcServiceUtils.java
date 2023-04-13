package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConstants;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.service.KcService;
import com.iscas.common.k8s.tools.model.service.KcServicePort;
import com.iscas.common.tools.core.date.DateSafeUtils;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.ServiceResource;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;

import java.text.ParseException;
import java.util.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/30 17:30
 * @since jdk1.8
 */
public class KcServiceUtils {
    private KcServiceUtils() {
    }


    /**
     * 删除一个service
     *
     * @param kcService 这里主要使用内部的两个属性：namespace,name
     * @throws K8sClientException K8S异常
     * @date 2021/1/12
     * @since jdk1.8
     */
    public static void deleteService(KcService kcService) throws K8sClientException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        MixedOperation<Service, ServiceList, ServiceResource<Service>> mixedOperation = kc.services();
        if (mixedOperation != null) {
            NonNamespaceOperation<Service, ServiceList, ServiceResource<Service>> nonNamespaceOperation = mixedOperation.inNamespace(kcService.getNamespace());
            if (nonNamespaceOperation != null) {
                ServiceResource<Service> serviceResource = nonNamespaceOperation.withName(kcService.getName());
                if (serviceResource != null && serviceResource.get() != null) {
                    //如果已存在此service,删除之
                    serviceResource.delete();
                }
            } else {
                throw new K8sClientException(String.format("无法从k8s的命名空间:[%s]获取services", kcService.getNamespace()));
            }
        } else {
            throw new K8sClientException("无法从k8s获取services");
        }
    }


    @SuppressWarnings("unused")
    private static boolean serviceExists(String namespace, String name) throws K8sClientException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        MixedOperation<Service, ServiceList, ServiceResource<Service>> mixedOperation = kc.services();
        if (mixedOperation != null) {
            NonNamespaceOperation<Service, ServiceList, ServiceResource<Service>> nonNamespaceOperation = mixedOperation.inNamespace(namespace);
            if (nonNamespaceOperation != null) {
                ServiceResource<Service> serviceResource = nonNamespaceOperation.withName(name);
                Service service = serviceResource.get();
                return service != null;
            } else {
                throw new K8sClientException(String.format("无法从k8s的命名空间:[%s]获取services", namespace));
            }
        } else {
            throw new K8sClientException("无法从k8s获取services");
        }
    }

    /**
     * 创建一个service,当前策略是,如果有这个service,直接删除，重新创建
     *
     * @param kcService service对应的一些属性，如端口，selector等
     * @throws K8sClientException K8S异常
     * @date 2021/1/12
     * @since jdk1.8
     */
    public static void createService(KcService kcService) throws K8sClientException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        MixedOperation<Service, ServiceList, ServiceResource<Service>> mixedOperation = kc.services();
        if (mixedOperation != null) {
            NonNamespaceOperation<Service, ServiceList, ServiceResource<Service>> nonNamespaceOperation = mixedOperation.inNamespace(kcService.getNamespace());
            if (nonNamespaceOperation != null) {
                //创建新的service
                List<String[]> selectors = kcService.getSelectors();
                //构建选择器，可以关联到deployment
                Map<String, String> selectorMap = new HashMap<>(4);
                if (CollectionUtils.isNotEmpty(selectors)) {
                    for (String[] selector : selectors) {
                        selectorMap.put(selector[0], selector[1]);
                    }
                }

                //label暂时构建一个与name一致的label
                Map<String, String> labels = new HashMap<>(2);
                labels.put("name", kcService.getName());

                //port
                Set<KcServicePort> ports = kcService.getPorts();
                List<ServicePort> servicePorts = new ArrayList<>();
                if (CollectionUtils.isNotEmpty(ports)) {
                    for (KcServicePort port : ports) {
                        ServicePort servicePort = new ServicePort();
                        if (port.getPort() != null) {
                            servicePort.setPort(port.getPort());
                        }
                        if (port.getTargetPort() != null) {
                            servicePort.setTargetPort(new IntOrString(port.getTargetPort()));
                        }
                        if (port.getNodePort() != null) {
                            servicePort.setNodePort(port.getNodePort());
                        }
                        if (port.getPortName() != null) {
                            servicePort.setName(port.getPortName());
                        }
                        if (port.getProtocol() != null) {
                            servicePort.setProtocol(port.getProtocol());
                        }
                        servicePorts.add(servicePort);
                    }
                }
                ServiceBuilder serviceBuilder = new ServiceBuilder()
                        .withNewMetadata()
                        .withName(kcService.getName())
                        .addToLabels(labels)
                        .endMetadata()
                        .withNewSpec()
                        .withSelector(selectorMap)
                        .withType(kcService.getType())
                        .withPorts(servicePorts)
                        .endSpec();

                Service service = serviceBuilder.build();
                ServiceResource<Service> serviceServiceResource = nonNamespaceOperation.withName(kcService.getName());
                if (serviceServiceResource.get() != null) {
                    serviceServiceResource.edit(n -> service);
                } else {
                    //新增
                    nonNamespaceOperation.create(service);
                }
            } else {
                throw new K8sClientException(String.format("无法从k8s的命名空间:[%s]获取services", kcService.getNamespace()));
            }
        } else {
            throw new K8sClientException("无法从k8s获取services");
        }
    }

    /**
     * 获取service的信息
     *
     * @param namespace 命名空间
     * @return java.util.List<com.iscas.common.k8s.tools.model.deployment.KcDeployment>
     * @throws ParseException 异常
     * @date 2020/12/30
     * @since jdk1.8
     */
    public static List<KcService> getServices(String namespace) throws ParseException {

        List<KcService> kcServices = null;
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        NonNamespaceOperation<Service, ServiceList, ServiceResource<Service>> servicesInNamespace = kc.services().inNamespace(namespace);
        if (servicesInNamespace != null) {
            ServiceList serviceList = servicesInNamespace.list();
            if (serviceList != null) {
                List<Service> items = serviceList.getItems();
                if (CollectionUtils.isNotEmpty(items)) {
                    kcServices = new ArrayList<>();
                    for (Service service : items) {
                        KcService kcService = getOneService(service);
                        kcServices.add(kcService);
                    }
                }
            }
        }

        return kcServices;
    }

    private static KcService getOneService(Service service) throws ParseException {
        KcService kcService = new KcService();
        //版本号
        kcService.setApiVersion(service.getApiVersion());
        ObjectMeta metadata = service.getMetadata();
        //名称
        kcService.setName(metadata.getName());
        //命名空间
        kcService.setNamespace(metadata.getNamespace());
        //运行时间
        String creationTimestamp = metadata.getCreationTimestamp();
        Date createTime = DateSafeUtils.parse(creationTimestamp, K8sConstants.TIME_PATTERN);
        createTime = CommonUtils.timeOffset(createTime);
        kcService.setRunTimeStr(CommonUtils.getTimeDistance(createTime));

        ServiceSpec spec = service.getSpec();
        //clusterIp
        kcService.setClusterIp(spec.getClusterIP());
        //type
        kcService.setType(spec.getType());
        //设置端口
        setPorts(kcService, spec);
        //设置selector
        setSelector(kcService, spec);

        return kcService;
    }

    private static void setSelector(KcService kcService, ServiceSpec spec) {
        Map<String, String> selector = spec.getSelector();
        if (selector != null) {
            for (Map.Entry<String, String> entry : selector.entrySet()) {
                String[] selectorStrs = {entry.getKey(), entry.getValue()};
                kcService.getSelectors().add(selectorStrs);
            }
        }
    }

    private static void setPorts(KcService kcService, ServiceSpec spec) {
        List<ServicePort> ports = spec.getPorts();
        if (CollectionUtils.isNotEmpty(ports)) {
            for (ServicePort port : ports) {
                KcServicePort kcServicePort = new KcServicePort();
                kcServicePort.setPortName(port.getName())
                        .setPort(port.getPort())
                        .setNodePort(port.getNodePort())
                        .setProtocol(port.getProtocol())
                        .setTargetPort(port.getTargetPort() == null ? null : port.getTargetPort().getIntVal());
                kcService.getPorts().add(kcServicePort);
            }
        }
    }


}
