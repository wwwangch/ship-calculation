package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConfig;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.ExecListener;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import okhttp3.Response;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 */
public class PodExecEquivalent {
    private static final CountDownLatch execLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            //初始化
            K8sConfig k8sConfig = new K8sConfig();
            k8sConfig.setApiServerPath("https://192.168.100.95:6443")
                    .setCaPath("C:/ideaProjects/cpaas-manager/ca.crt")
                    .setToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJrdWJvYXJkLXVzZXItdG9rZW4tZHZrbXgiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoia3Vib2FyZC11c2VyIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNDI5MDBhMTctNjFlYi00OWM3LWI5NmItMGZjMzBkNzg2Mzk3Iiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmUtc3lzdGVtOmt1Ym9hcmQtdXNlciJ9.XrRIE6ZHBLE1DOJiV9S_e-uWmxaDY0cExGk1YzxVGFGK6Wh6IJFwF2fM217Is0E3TThTQE9WW2mr71bxa5-ZbCshO5VbDFNtv4pq4Ve_7ijYdd2zPVPabH59vdDmnMt5xIVeeFwcSXN8TrrKzTcR3wnpqafGAnEYTeHcu0Z1GyTYN_y5b0PqhIdEgwMKpL-_PWClY7nta7nzwS0CDulboimpmwsIZldLkNWcLzkM90FJttvivYDDrpLkdURoHLWxnxlf_hlFnfA7LXt7v380sS--Yg8ULl6rE7Gwtk_6I77q8eKMuo55okiBq-9fKVmcDUD3SD27zgAL5-_bFN2z6w");
            K8sClient.setConfig(k8sConfig);
            KubernetesClient k8s = K8sClient.getInstance();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayOutputStream error = new ByteArrayOutputStream();

            ExecWatch execWatch = k8s.pods().inNamespace("default").withName("test2-test2-795565f459-7r6vk")
                    .writingOutput(out)
                    .writingError(error)
                    .usingListener(new MyPodExecListener())
                    .exec("cd", "/");

            boolean latchTerminationStatus = execLatch.await(5, TimeUnit.SECONDS);
            if (!latchTerminationStatus) {
                System.out.println("Latch could not terminate within specified time");
            }

            System.out.println("Exec Output: {} " + out);
            execWatch.close();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            ie.printStackTrace();
        }
    }

    private static class MyPodExecListener implements ExecListener {
        @Override
        public void onOpen(Response response) {
            System.out.println("Shell was opened");
        }

        @Override
        public void onFailure(Throwable throwable, Response response) {
            System.out.println("Some error encountered");
            execLatch.countDown();
        }

        @Override
        public void onClose(int i, String s) {
            System.out.println("Shell Closing");
            execLatch.countDown();
        }
    }
}