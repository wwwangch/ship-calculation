package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.statefulset.KcStatefulset;
import io.fabric8.kubernetes.client.Config;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/03/22 14:24
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcStatefulsetUtilsTests {

    @Before
    public void before() throws IOException {
        //初始化
        String configYAML = String.join("\n", Files.readAllLines(new File("D:\\ideaProjects\\cpaas-manager\\kube-config").toPath()));
        Config config = Config.fromKubeconfig(configYAML);
        config.setTrustCerts(true);
        K8sClient.setFabric8Config(config);
    }

    /**
     *
     * 获取所有Statefulset
     */
    @Test
    public void getStatefulsets() throws K8sClientException {
        List<KcStatefulset> kcStatefulsets = KcStatefulsetUtils.getStatefulset("default");
        System.out.println(kcStatefulsets);
    }

}
