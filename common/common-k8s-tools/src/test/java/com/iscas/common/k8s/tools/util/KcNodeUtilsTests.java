package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConfig;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.node.KcNode;
import com.iscas.common.k8s.tools.model.node.KcNodeBaseInfoTaint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 14:24
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcNodeUtilsTests {

    @Before
    public void before() {
        //初始化
        K8sConfig k8sConfig = new K8sConfig();
        k8sConfig.setApiServerPath("https://192.168.100.94:6443")
                .setCaPath("C:\\Users\\Administrator\\Desktop\\ca.crt")
                .setToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImNsdXN0ZXItYWRtaW4tZGFzaGJvYXJkLXNhLXRva2VuLXhzcWo4Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImNsdXN0ZXItYWRtaW4tZGFzaGJvYXJkLXNhIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiMTE1MzMzYTYtMjY2Mi00NmQyLTkxMDItMDI5N2Q2YmIyMzBlIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OmRlZmF1bHQ6Y2x1c3Rlci1hZG1pbi1kYXNoYm9hcmQtc2EifQ.syjMBQAQYVlR50m2cNKb-eOPIBZbop2bhw1fT4limqJv_JvIZgqwnH4kD1yFMGmUn39XIdwEfAY5r2PCky99YbJ-JkMdnAm-A_9nNoU1-muK2c28oks07UZI2KZf_lTjlwbNAm-6CEb3rgozSCLhHvqlQka_cxHUa4xqWH2D7BCWZj-n1uYQkjdEezi-jyM-OEiP5RKScElvEBb2UbQWQnLVcJMpryEqFKwCrPbTI3Cz0J2i67R4ffYMG22app4Ucl3QthVWlt8I89F2P-J_7PT0L1LvZUf3wAn34YE722Po_E328gC35mS9sHgC-AqwdfejcEq3I1srKWrKcbtGEQ");
        K8sClient.setConfig(k8sConfig);
    }

    /**
     *
     * 获取所有Nodes
     */
    @Test
    public void getNodes() throws K8sClientException, IOException {
        List<KcNode> nodes = KcNodeUtils.getNodes();
        System.out.println(nodes);
    }

    /**
     *
     * 编辑某个节点的污点
     */
    @Test
    public void editTaint() throws K8sClientException {
        KcNodeBaseInfoTaint kcTaint = new KcNodeBaseInfoTaint();
        kcTaint.setKey("node-role.kubernetes.io/master")
                .setValue(null)
                .setEffect("NoSchedule");
        KcNodeUtils.editTaint("master", kcTaint);
    }

    /**
     *
     * 在某个节点新增污点
     */
    @Test
    public void addTaint() throws K8sClientException {
        KcNodeBaseInfoTaint kcTaint = new KcNodeBaseInfoTaint();
        kcTaint.setKey("efg")
                .setValue("wegwe")
                .setEffect("NoSchedule");
        KcNodeUtils.addTaint("node3", kcTaint);
    }

    /**
     *
     * 在某个节点删除污点
     */
    @Test
    public void deleteTaint() throws K8sClientException {
        KcNodeBaseInfoTaint kcTaint = new KcNodeBaseInfoTaint();
        KcNodeUtils.deleteTaint("node3", "efg");
    }

    /**
     *
     * 判断某个节点是否存在
     */
    @Test
    public void exsistsTaint() throws K8sClientException {
        boolean master = KcNodeUtils.checkNodeExsits("master1");
        System.out.println(master);
    }

    /**
     *
     * 为某个节点新增label
     */
    @Test
    public void addLabel() throws K8sClientException {
        KcNodeUtils.addLabel("node3", new String[]{"aaa", "bbb"});
    }

    /**
     *
     * 为某个节点编辑label
     */
    @Test
    public void editLabel() throws K8sClientException {
        KcNodeUtils.editLabel("node3", new String[]{"aaa", "ccc"});
    }

    /**
     *
     * 为某个节点删除label
     */
    @Test
    public void deleteLabel() throws K8sClientException {
        KcNodeUtils.deleteLabel("node3", "aaa");
    }

    /**
     *
     * 为某个节点新增annotation
     */
    @Test
    public void addAnnotation() throws K8sClientException {
        KcNodeUtils.addAnnotation("node3", new String[]{"aaa", "bbb"});
    }

    /**
     *
     * 为某个节点编辑annotation
     */
    @Test
    public void editAnnotation() throws K8sClientException {
        KcNodeUtils.editAnnotation("node3", new String[]{"aaa", "ccc"});
    }

    /**
     *
     * 为某个节点删除annotation
     */
    @Test
    public void deleteAnnotation() throws K8sClientException {
        KcNodeUtils.deleteAnnotation("node3", "aaa");
    }
}
