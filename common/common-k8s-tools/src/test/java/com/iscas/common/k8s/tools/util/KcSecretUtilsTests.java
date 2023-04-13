package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.model.secret.KcSecret;
import io.fabric8.kubernetes.client.Config;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/18 16:06
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcSecretUtilsTests {
    @Before
    public void before() throws IOException {
        //初始化
        String configYAML = String.join("\n", Files.readAllLines(new File("C:\\ideaProjects\\cpaas-manager\\kube-config").toPath()));
        Config config = Config.fromKubeconfig(configYAML);
        config.setTrustCerts(true);
        K8sClient.setFabric8Config(config);
    }

    @Test
    public void testList() throws ParseException, UnsupportedEncodingException {
        List<KcSecret> kcSecrets = KcSecretUtils.getSecrets("default");
        kcSecrets.forEach(System.out::println);
    }
}
