package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

/**
 * KcConfigmapUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>6月 10, 2021</pre>
 */
public class KcConfigmapUtilsTest {

    @Before
    public void before() throws Exception {
        //初始化
        String configYAML = String.join("\n", Files.readAllLines(new File("C:\\ideaProjects\\cpaas-manager\\kube-config").toPath()));
        io.fabric8.kubernetes.client.Config config = io.fabric8.kubernetes.client.Config.fromKubeconfig(configYAML);
        config.setTrustCerts(true);
        K8sClient.setFabric8Config(config);
    }

    /**
     * Method: getConfigMapData(String namespace, String name)
     */
    @Test
    public void testGetConfigMapData() throws Exception {
        Map<String, String> aDefault = KcConfigmapUtils.getConfigMapData("default", "rocketmq-config");
        System.out.println(aDefault);
    }


} 
