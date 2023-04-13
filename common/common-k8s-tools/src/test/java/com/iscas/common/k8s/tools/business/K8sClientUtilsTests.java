package com.iscas.common.k8s.tools.business;

import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.deployment.KcDeployment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/15 10:40
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class K8sClientUtilsTests {
    @Before
    public void before() {
        K8sClientUtils.initClient();
    }

    /**
     * 测试删除deployment
     * */
    @Test
    public void delete() {
        K8sClientUtils.stopServer("thisisatest");
    }

    /**
     * 测试创建deployment
     * */
    @Test
    public void start() throws K8sClientException {
        K8sClientUtils.startDeployment("thisisatest", "172.16.10.80:80/helloworld:0.0.4", 8080, 30, null);
    }

    /**
     * 测试获取deployment
     * */
    @Test
    public void get() throws K8sClientException {
        List<KcDeployment> servers = K8sClientUtils.getServers();
        System.out.println(servers);
    }
}
