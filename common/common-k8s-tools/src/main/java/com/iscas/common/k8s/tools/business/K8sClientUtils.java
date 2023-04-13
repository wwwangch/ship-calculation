package com.iscas.common.k8s.tools.business;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConfig;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.KcContainer;
import com.iscas.common.k8s.tools.model.KcContainerPort;
import com.iscas.common.k8s.tools.model.deployment.KcDepBaseInfo;
import com.iscas.common.k8s.tools.model.deployment.KcDeployment;
import com.iscas.common.k8s.tools.model.health.KcHealthTcpParam;
import com.iscas.common.k8s.tools.model.health.KcLivenessProbe;
import com.iscas.common.k8s.tools.model.health.KcReadinessProbe;
import com.iscas.common.k8s.tools.util.KcDeploymentUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 业务处理工具类，将之前封装的k8s处理工具，做二次封装，
 * 将可屏蔽的参数，写成默认参数，适应于小卫星项目-规则解析器管理
 * 使用的例子见test包下的单元测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/13 21:32
 * @since jdk1.8
 */
public class K8sClientUtils {

    /**K8s apiserver地址*/
    private static final String APISERVER_PATH = "https://172.16.10.80:6443";
    /**k8s CA证书下载到本地，存放的地址*/
    private static final String CA_PATH = "H:\\ideaProjects\\newframe-dev\\common\\common-k8s-tools\\ca.crt";
    /**K8S集群有权限的TOKEN*/
    private static final String TOKEN = "eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImNsdXN0ZXItYWRtaW4tZGFzaGJvYXJkLXRva2VuLXo0N3I4Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImNsdXN0ZXItYWRtaW4tZGFzaGJvYXJkIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNWQyNDUwMTMtNTU3My00Nzg0LThmYTMtOGM3NmZiZGZhOTQ0Iiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OmRlZmF1bHQ6Y2x1c3Rlci1hZG1pbi1kYXNoYm9hcmQifQ.AN2fmgrGLP1Dqdt9PQTDHz0vJs92HLkb0D4fCmsdr_Oz0KFyxlO5AuVImzuLysLmpYe4iiOuIyeGpMjAJ8EHZ-EvOn3MROANy8RVRGM3npnLOj_COfi8iXAOPt4N55SU7maOkhM-7hnjZtcuYSJJU8rAmq4-u4liJ6xvbtkBbnoi4-HDszOKtG3Wi7pVPCDZhV-SBgCtqouvHuir1BMKY_KB14se__9g8jxpj-iEBcV9GkmeCDLax782DNY116JCtlzjVNkkEoU8nizhPdYbVkY251G5r2-z37Ph3t0r3CUxL3QI_1CFZ055DAvVlsbdI_JLVNpWjPg8q7l5du1YQw";

    public static void initClient() {
        K8sConfig k8sConfig = new K8sConfig();
        k8sConfig.setApiServerPath(APISERVER_PATH)
                .setCaPath(CA_PATH)
                .setToken(TOKEN);
        K8sClient.setConfig(k8sConfig);
    }

    /**
     * 删除一个deployment
     * @since jdk1.8
     * @date 2019/12/15
     * @param name deployment的name，也就是启动时传入的pattern {@link K8sClientUtils#startDeployment}
     */
    public static void stopServer(String name) {
        KcDeploymentUtils.deleteDeployment("default", name);
    }

    /**
     * 启动一个Deployment,这里一个deployment这里对应一个docker,对应一个规则解析器
     * kubernetes的命名空间使用默认的“default”
     * 就绪检查使用默认的参数，可见函数代码内的值
     * 存活检查使用默认的参数，可见函数代码内的值
     * 服务重启规则为只要服务挂了就会重启
     *
     * @since jdk1.8
     * @date 2019/12/13
     * @param pattern 启动的当前的规则解析器的标记，会放入环境变量内key为“pattern”，解析器内部可获取，
     *                也作为deploment的name和label.
     * @param image 镜像地址，暂时不支持带用户名、密码的镜像私服（后面也可以修改为支持的，需要传入secret的key）
     * @param port 容器内的服务使用的端口，很重要，还会作为健康检测的端口
     * @param startTime 预计规则服务启动所需的时间，尽量准确，会影响存活检测的延迟时间，预估过少会导致
     *                  服务启动过程中，无限循环重启，预估过大会导致服务虽然已启动，但还是要等startTime秒后才对外
     *                  提供服务
     * @param imagePullSecret 镜像拉取秘钥，如果镜像私服没有用户密码，可以为空，建议直接用docker搭建一个无用户密码的私服用，简单。
     *                        如果私服需要有用户密码，需要在k8s master节点生成一个秘钥，这里的参数并使用它作为参数
     *                        下面是步骤：
     *                        【
     *                          1、给当前的镜像打tag
     *                              docker tag helloworld:0.0.4 172.16.10.190:8008/helloworld:0.0.4
     *                          2、登陆镜像私服
     *                              docker login --username=admin 172.16.10.190:8008
     *                              输入密码登陆
     *                              docker push 172.16.10.190:8008/helloworld:0.0.4
     *                          3、配置secret
     *                              kubectl create secret docker-registry myregistrykey --docker-server=172.16.10.190:8008
     *                                --docker-username=admin --docker-password=admin123 --docker-email=admin@example.org
     *                          4、第三步使用的名字“myregistrykey” 就是imagePullSecret参数
     *                        】
     *
     *
     * @throws K8sClientException k8s客户端处理异常
     */
    @SuppressWarnings("unused")
    public static void startDeployment(String pattern, String image, int port, int startTime, String imagePullSecret) throws K8sClientException {

        KcDeployment deployment = new KcDeployment();
        deployment.setName(pattern);
        deployment.setPlanRepSum(1);
        deployment.setRestartStrategy("Always");
        KcDepBaseInfo baseInfo = new KcDepBaseInfo();
        List<String[]> labels = new ArrayList<>();
        labels.add(new String[] {"app", pattern});
        baseInfo.setNamespace("default")
                .setPlanRepSum(1)
                .setType("Deployment")
                .setName(pattern)
                .setLabels(labels);
        deployment.setBaseInfo(baseInfo);

        KcContainer kcContainer = new KcContainer();
        LinkedHashMap<String, String> env = new LinkedHashMap<>();
        env.put("pattern", pattern);
        kcContainer.setEnv(env);
        kcContainer.setImage(image);
        kcContainer.setImagePullPolicy("IfNotPresent");
        kcContainer.setName(pattern);
        KcContainerPort containerPort = new KcContainerPort();
        containerPort.setContainerPort(port);
        kcContainer.setPorts(Collections.singletonList(containerPort));
        KcReadinessProbe readinessProbe = new KcReadinessProbe();
        readinessProbe.setType("TCP");
        KcHealthTcpParam kcHealthTcpParam = new KcHealthTcpParam();
        kcHealthTcpParam.setPort(port);
        kcHealthTcpParam.setTimeout(30);
        kcHealthTcpParam.setInitialDelaySeconds(startTime + 10);
        readinessProbe.setHealthTcpParam(kcHealthTcpParam);
        kcContainer.setReadinessProbe(readinessProbe);

        KcLivenessProbe livenessProbe = new KcLivenessProbe();
        livenessProbe.setType("TCP");
        KcHealthTcpParam kcHealthTcpParam2 = new KcHealthTcpParam();
        kcHealthTcpParam2.setPort(port);
        kcHealthTcpParam2.setTimeout(30);
        kcHealthTcpParam2.setPeriodSeconds(15);
        kcHealthTcpParam2.setInitialDelaySeconds(startTime + 10);
        livenessProbe.setHealthTcpParam( kcHealthTcpParam2);

        kcContainer.setLivenessProbe(livenessProbe);
        kcContainer.setReadinessProbe(readinessProbe);


        deployment.setContainers(Collections.singletonList(kcContainer));

        KcDeploymentUtils.createDeployment(deployment);
    }


    /**
     *
     * 获取所有deployment，默认从default空间中获取
     * */
    public static List<KcDeployment> getServers() throws K8sClientException {
        return KcDeploymentUtils.getDeployments("default");
    }

}
