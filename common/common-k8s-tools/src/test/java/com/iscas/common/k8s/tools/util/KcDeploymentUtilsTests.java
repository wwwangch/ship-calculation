package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConfig;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.KcContainer;
import com.iscas.common.k8s.tools.model.KcContainerPort;
import com.iscas.common.k8s.tools.model.autoscale.KcAutoscale;
import com.iscas.common.k8s.tools.model.deployment.KcDepBaseInfo;
import com.iscas.common.k8s.tools.model.deployment.KcDeployment;
import com.iscas.common.k8s.tools.model.health.KcHealthTcpParam;
import com.iscas.common.k8s.tools.model.health.KcReadinessProbe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 14:24
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcDeploymentUtilsTests {

    @Before
    public void before() {
        //初始化
        K8sConfig k8sConfig = new K8sConfig();
        k8sConfig.setApiServerPath("https://192.168.100.95:6443")
                .setCaPath("C:/ideaProjects/cpaas-manager/ca.crt")
                .setToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJrdWJvYXJkLXVzZXItdG9rZW4tZHZrbXgiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoia3Vib2FyZC11c2VyIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNDI5MDBhMTctNjFlYi00OWM3LWI5NmItMGZjMzBkNzg2Mzk3Iiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmUtc3lzdGVtOmt1Ym9hcmQtdXNlciJ9.XrRIE6ZHBLE1DOJiV9S_e-uWmxaDY0cExGk1YzxVGFGK6Wh6IJFwF2fM217Is0E3TThTQE9WW2mr71bxa5-ZbCshO5VbDFNtv4pq4Ve_7ijYdd2zPVPabH59vdDmnMt5xIVeeFwcSXN8TrrKzTcR3wnpqafGAnEYTeHcu0Z1GyTYN_y5b0PqhIdEgwMKpL-_PWClY7nta7nzwS0CDulboimpmwsIZldLkNWcLzkM90FJttvivYDDrpLkdURoHLWxnxlf_hlFnfA7LXt7v380sS--Yg8ULl6rE7Gwtk_6I77q8eKMuo55okiBq-9fKVmcDUD3SD27zgAL5-_bFN2z6w");
        K8sClient.setConfig(k8sConfig);
    }

    /**
    * 删除一个deployment
    * */
    @Test
    public void deleteDep() {
        KcDeploymentUtils.deleteDeployment("kube-ops", "helloworld-kc1");
    }

    /**
     *
     * 获取所有Deployment
     */
    @Test
    public void getDeployments() throws K8sClientException {
        List<KcDeployment> kcDeployments = KcDeploymentUtils.getDeployments("kube-ops");
        System.out.println(kcDeployments);
    }

    /**
     *
     * 创建Deployment
     */
    @Test
    public void createDeployment() throws K8sClientException {
        KcDeployment deployment = new KcDeployment();
        deployment.setName("helloworld-kc1");
        deployment.setPlanRepSum(2);
        deployment.setRestartStrategy("Always");
        KcDepBaseInfo baseInfo = new KcDepBaseInfo();
        List<String[]> labels = new ArrayList<>();
        labels.add(new String[] {"app", "web-kc1"});
        baseInfo.setNamespace("kube-ops")
                .setPlanRepSum(2)
                .setType("Deployment")
                .setName("helloworld-kc1")
                .setLabels(labels);
        deployment.setBaseInfo(baseInfo);

        KcContainer kcContainer = new KcContainer();
        LinkedHashMap<String, String> env = new LinkedHashMap<>();
        env.put("host", "192.168.100.88:3306");
        kcContainer.setEnv(env);
        kcContainer.setImage("192.168.100.94:80/helloworld:0.0.4");
        kcContainer.setImagePullPolicy("IfNotPresent");
        kcContainer.setName("helloworldkc1");
        KcContainerPort containerPort = new KcContainerPort();
        containerPort.setContainerPort(8080);
        kcContainer.setPorts(Arrays.asList(containerPort));
        KcReadinessProbe readinessProbe = new KcReadinessProbe();
        readinessProbe.setType("TCP");
        KcHealthTcpParam kcHealthTcpParam = new KcHealthTcpParam();
        kcHealthTcpParam.setPort(8080);
        kcHealthTcpParam.setTimeout(30);
        kcHealthTcpParam.setInitialDelaySeconds(10);
//        readinessProbe.setHealthParam(kcHealthTcpParam);
        kcContainer.setReadinessProbe(readinessProbe);
        deployment.setContainers(Arrays.asList(kcContainer));

        KcDeploymentUtils.createDeployment(deployment);
    }

//    /**
//     *
//     * 编辑某个节点的污点
//     */
//    @Test
//    public void editTaint() throws K8sClientException {
//        KcNodeBaseInfoTaint kcTaint = new KcNodeBaseInfoTaint();
//        kcTaint.setKey("node-role.kubernetes.io/master")
//                .setValue(null)
//                .setEffect("NoSchedule");
//        KcNodeUtils.editTaint("master", kcTaint);
//    }
//
//    /**
//     *
//     * 在某个节点新增污点
//     */
//    @Test
//    public void addTaint() throws K8sClientException {
//        KcNodeBaseInfoTaint kcTaint = new KcNodeBaseInfoTaint();
//        kcTaint.setKey("efg")
//                .setValue("wegwe")
//                .setEffect("NoSchedule");
//        KcNodeUtils.addTaint("node3", kcTaint);
//    }
//
//    /**
//     *
//     * 在某个节点删除污点
//     */
//    @Test
//    public void deleteTaint() throws K8sClientException {
//        KcNodeBaseInfoTaint kcTaint = new KcNodeBaseInfoTaint();
//        KcNodeUtils.deleteTaint("node3", "efg");
//    }
//
//    /**
//     *
//     * 判断某个节点是否存在
//     */
//    @Test
//    public void exsistsTaint() throws K8sClientException {
//        boolean master = KcNodeUtils.checkNodeExsits("master1");
//        System.out.println(master);
//    }
//
//    /**
//     *
//     * 为某个节点新增label
//     */
//    @Test
//    public void addLabel() throws K8sClientException {
//        KcNodeUtils.addLabel("node3", new String[]{"aaa", "bbb"});
//    }
//
//    /**
//     *
//     * 为某个节点编辑label
//     */
//    @Test
//    public void editLabel() throws K8sClientException {
//        KcNodeUtils.editLabel("node3", new String[]{"aaa", "ccc"});
//    }
//
//    /**
//     *
//     * 为某个节点删除label
//     */
//    @Test
//    public void deleteLabel() throws K8sClientException {
//        KcNodeUtils.deleteLabel("node3", "aaa");
//    }
//
//    /**
//     *
//     * 为某个节点新增annotation
//     */
//    @Test
//    public void addAnnotation() throws K8sClientException {
//        KcNodeUtils.addAnnotation("node3", new String[]{"aaa", "bbb"});
//    }
//
//    /**
//     *
//     * 为某个节点编辑annotation
//     */
//    @Test
//    public void editAnnotation() throws K8sClientException {
//        KcNodeUtils.editAnnotation("node3", new String[]{"aaa", "ccc"});
//    }
//
//    /**
//     *
//     * 为某个节点删除annotation
//     */
//    @Test
//    public void deleteAnnotation() throws K8sClientException {
//        KcNodeUtils.deleteAnnotation("node3", "aaa");
//    }

    @Test
    public void scaleTest() throws K8sClientException {
        KcDeploymentUtils.scale("default", "test2-test2", 2);
    }


    /**
     * 测试重启服务
     * */
    @Test
    public void restartTest() throws K8sClientException {
        KcDeploymentUtils.restartDeployment("default", "test2-test2");
    }

    /**
     * 测试自动扩容
     * */
    @Test
    public void testGetAutoScale() throws ParseException {
        KcAutoscale autoScale = KcDeploymentUtils.getAutoScale("default", "1-test-2");
        System.out.println(autoScale);
    }

}
