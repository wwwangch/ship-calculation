package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.util.Config;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * storageclassUtils 测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/15 11:09
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcStorageClassUtilsTests {
    private ApiClient apiClient;

    @Before
    public void apiClient() {
        ApiClient kubeApiClient = null;
        try {
            kubeApiClient = Config.fromConfig("C:\\ideaProjects\\cpaas-manager\\kube-config");
        } catch (IOException e) {
            e.printStackTrace();
        }
        kubeApiClient.setConnectTimeout(1800000);
        kubeApiClient.setReadTimeout(1800000);
        kubeApiClient.setWriteTimeout(1800000);
        io.kubernetes.client.openapi.Configuration.setDefaultApiClient(kubeApiClient);
        apiClient = kubeApiClient;
    }

    @Before
    public void before() throws IOException {
        //初始化
        String configYAML = String.join("\n", Files.readAllLines(new File("C:\\ideaProjects\\cpaas-manager\\kube-config").toPath()));
        io.fabric8.kubernetes.client.Config config = io.fabric8.kubernetes.client.Config.fromKubeconfig(configYAML);
        config.setTrustCerts(true);
        K8sClient.setFabric8Config(config);
    }


//    @Test
//    public void testListAll() throws ApiException {
//        KcStorageClassUtils.listAll(apiClient);
//    }

    @Test
    public void testListAll() throws ApiException {
        KcStorageClassUtils.listAll();
    }


}
