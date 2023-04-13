package com.iscas.common.k8s.tools.util;

import cn.hutool.core.lang.Assert;
import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConfig;
import com.iscas.common.k8s.tools.model.KcVersion;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 14:24
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcCommonUtilsTests {

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
     * 获取k8s版本号
     */
    @Test
    public void testVersion() {
        String kcVersion = KcCommonUtils.getKcVersion();
        System.out.println(kcVersion);
        Assert.notNull(kcVersion);
    }

    /**
     *
     * 获取k8s版本详细信息
     */
    @Test
    public void testVersionInfo() {
        KcVersion kcVersion = KcCommonUtils.getKcVersionInfo();
        System.out.println(kcVersion);
        Assert.notNull(kcVersion);
    }


}
