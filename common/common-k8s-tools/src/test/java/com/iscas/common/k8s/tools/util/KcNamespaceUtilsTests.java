package com.iscas.common.k8s.tools.util;

import cn.hutool.core.lang.Assert;
import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConfig;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.KcNamespace;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 16:00
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcNamespaceUtilsTests {

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
     * 获取所有命名空间
     */
    @Test
    public void testGetNamespaces() throws K8sClientException {
        List<KcNamespace> namespaces = KcNamespaceUtils.getNamespaces();
        System.out.println(namespaces);
        Assert.notNull(namespaces);
    }

    /**
     *
     * 按照命名空间名字获取命名空间
     */
    @Test
    public void testGetNamespace() throws K8sClientException {
        KcNamespace kcNs = KcNamespaceUtils.getNamespace("default");
        System.out.println(kcNs);
        Assert.notNull(kcNs);

    }

    /**
     *
     * 测试创建一个命名空间
     */
    @Test
    public void createNamespace() throws K8sClientException {
        KcNamespaceUtils.createNamespace("test-ns");
        List<KcNamespace> namespaces = KcNamespaceUtils.getNamespaces();
    }

    /**
     *
     * 测试删除一个命名空间
     */
    @Test
    public void deleteNamespace() throws K8sClientException {
        KcNamespaceUtils.deleteNamespace("test-ns");
        List<KcNamespace> namespaces = KcNamespaceUtils.getNamespaces();
        System.out.println(namespaces);
    }
}
