package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConfig;
import com.iscas.common.k8s.tools.model.pod.KcPod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Pod工具
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/31 17:56
 * @since jdk1.8
 */
@RunWith(JUnit4.class)
public class KcPodUtilsTests {

    @Before
    public void before() {
        //初始化
        K8sConfig k8sConfig = new K8sConfig();
        k8sConfig.setApiServerPath("https://192.168.100.95:6443")
                .setCaPath("C:/ideaProjects/cpaas-manager/ca.crt")
                .setToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJrdWJvYXJkLXVzZXItdG9rZW4tZHZrbXgiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoia3Vib2FyZC11c2VyIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNDI5MDBhMTctNjFlYi00OWM3LWI5NmItMGZjMzBkNzg2Mzk3Iiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmUtc3lzdGVtOmt1Ym9hcmQtdXNlciJ9.XrRIE6ZHBLE1DOJiV9S_e-uWmxaDY0cExGk1YzxVGFGK6Wh6IJFwF2fM217Is0E3TThTQE9WW2mr71bxa5-ZbCshO5VbDFNtv4pq4Ve_7ijYdd2zPVPabH59vdDmnMt5xIVeeFwcSXN8TrrKzTcR3wnpqafGAnEYTeHcu0Z1GyTYN_y5b0PqhIdEgwMKpL-_PWClY7nta7nzwS0CDulboimpmwsIZldLkNWcLzkM90FJttvivYDDrpLkdURoHLWxnxlf_hlFnfA7LXt7v380sS--Yg8ULl6rE7Gwtk_6I77q8eKMuo55okiBq-9fKVmcDUD3SD27zgAL5-_bFN2z6w");
        K8sClient.setConfig(k8sConfig);
    }

    @Test
    public void test() throws ParseException, IOException {
        List<KcPod> kcPods = KcPodUtils.getPods("default", true);
        kcPods.forEach(System.out::println);
    }
}
