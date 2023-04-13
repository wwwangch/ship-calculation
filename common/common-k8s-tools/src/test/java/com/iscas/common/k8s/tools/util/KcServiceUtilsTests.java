package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConfig;
import com.iscas.common.k8s.tools.exception.K8sClientException;
import com.iscas.common.k8s.tools.model.service.KcService;
import com.iscas.common.k8s.tools.model.service.KcServicePort;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * K8S service 测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/30 10:29
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcServiceUtilsTests {

    @Before
    public void before() {
        //初始化
        K8sConfig k8sConfig = new K8sConfig();
        k8sConfig.setApiServerPath("https://172.16.10.160:6443")
                .setCaPath("C:/ideaProjects/cpaas-manager/ca.crt")
                .setToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJrdWJvYXJkLXVzZXItdG9rZW4tZHZrbXgiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoia3Vib2FyZC11c2VyIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNDI5MDBhMTctNjFlYi00OWM3LWI5NmItMGZjMzBkNzg2Mzk3Iiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmUtc3lzdGVtOmt1Ym9hcmQtdXNlciJ9.XrRIE6ZHBLE1DOJiV9S_e-uWmxaDY0cExGk1YzxVGFGK6Wh6IJFwF2fM217Is0E3TThTQE9WW2mr71bxa5-ZbCshO5VbDFNtv4pq4Ve_7ijYdd2zPVPabH59vdDmnMt5xIVeeFwcSXN8TrrKzTcR3wnpqafGAnEYTeHcu0Z1GyTYN_y5b0PqhIdEgwMKpL-_PWClY7nta7nzwS0CDulboimpmwsIZldLkNWcLzkM90FJttvivYDDrpLkdURoHLWxnxlf_hlFnfA7LXt7v380sS--Yg8ULl6rE7Gwtk_6I77q8eKMuo55okiBq-9fKVmcDUD3SD27zgAL5-_bFN2z6w");
        K8sClient.setConfig(k8sConfig);
    }

    @Test
    public void testGet() throws K8sClientException, ParseException {
        List<KcService> kcServices = KcServiceUtils.getServices("default");
        kcServices.forEach(System.out::println);
    }


    /**
     * 测试删除一个service
     * */
    @Test
    public void testDeleteService() throws K8sClientException, ParseException {
        KcService kcService = new KcService();
        kcService.setName("consumer-test-service")
                .setNamespace("default");
        KcServiceUtils.deleteService(kcService);
    }

    /**
     * 测试创建一个service
     * */
    @Test
    public void testCreateService() throws K8sClientException, ParseException {
        KcService kcService = new KcService();
        List<String[]> selectors = new ArrayList<>();
        String[] selectArray = new String[2];
        selectArray[0] = "app";
        selectArray[1] = "consumer-test-service";
        selectors.add(selectArray);
        Set<KcServicePort> kcServicePorts = new HashSet<>();
        KcServicePort kcServicePort = new KcServicePort();
        kcServicePort.setPort(8080)
                .setNodePort(32148)
                .setProtocol("TCP")
                .setPort(8080);
        kcServicePorts.add(kcServicePort);
        kcService.setName("consumer-test-service")
                .setNamespace("default")
                .setType("NodePort")
                .setPorts(kcServicePorts)
                .setSelectors(selectors);
        KcServiceUtils.createService(kcService);
    }
}
