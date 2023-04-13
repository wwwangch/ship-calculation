package com.iscas.common.k8s.tools;

import com.iscas.common.k8s.tools.cfg.K8sConfig;
import com.iscas.common.k8s.tools.exception.K8sCleintRuntimeException;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * kubernetes-client github地址 <a href="https://github.com/fabric8io/kubernetes-client">...</a>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 13:49
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class K8sClient {
    private K8sClient() {
    }
    @SuppressWarnings("unused")
    private static final KubernetesClient KC = null;
    private static K8sConfig k8sConfig = null;
    private static Config fabric8Config = null;

    public static void setConfig(K8sConfig k8sConfig) {
        K8sClient.k8sConfig = k8sConfig;
    }

    public static void setFabric8Config(Config config) {
        K8sClient.fabric8Config = config;
    }

    public static K8sConfig getConfig() {
        return k8sConfig;
    }

    public static Config getFabric8Config() {
        return fabric8Config;
    }

    public static KubernetesClient getInstance() {
        if (k8sConfig == null && fabric8Config == null) {
            throw new K8sCleintRuntimeException("k8s客户端配置为空");
        }
        //优先读取/root/.kube/config的配置
        if (fabric8Config != null) {
            return getInstanceByFaric8Config(fabric8Config);
        }
        return getInstance(k8sConfig);
    }

    /**
     * 暂时使用每次创建一个kubernetes-client的方式，使用单例模式，发现程序执行完线程不会退出
     * */
    public static KubernetesClient getInstance(K8sConfig k8sConfig) {

        synchronized (K8sClient.class) {
            System.setProperty("kubernetes.certs.ca.file", k8sConfig.getCaPath());
            Config config = new ConfigBuilder()
                    //這是k8s集群訪問的TOKEN
                    .withOauthToken(k8sConfig.getToken())
//                .withOauthToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImRlZmF1bHQtdG9rZW4tNWZrMjQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiZGVmYXVsdCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjNjOWVlMjI2LWE2YTQtNGE0Yi05YjdmLWVhODRiMzMwNGY4MiIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmRlZmF1bHQifQ.FGCvmtFrwahz2gLwRBT_rAFYccMZwn-uG7pRXxnCq_XyJ9ZcmS68JMzDLj3PqxY6s4UBLZenwTy0JjhQuhEKsKVLnqDYxc1nXbvCSFzisDVNpW732c8KqtNgqIH2DHXGYB67TcNxVr4dgY0H75k0_CW8p0hXjPW6tNhaVNg_HXR8kJDmKQ-jyYCZeAIVAy-FtcXmduMt5qMjt9F66mMn2YFrdyQyiUp46PLbvJegh2H9_o-RAim8O8anWnTYcSr_T8DBhnxuXD69fdvS0rGM8qK4woxp8tMyj_ixkj3z0K1nd4HOhZQNYulIsSlJz8AW1Wu52CqAaEDbPRcifFNknQ")
                    //固定用v1就行
                    .withApiVersion(k8sConfig.getApiVersion())
                    //這是k8s的master節點地址，端口如果沒改應該也是6443
                    .withMasterUrl(k8sConfig.getApiServerPath()).build();
            return new DefaultKubernetesClient(config);
        }

    }

    public static KubernetesClient getInstanceByFaric8Config(Config config) {
        synchronized (K8sClient.class) {
            return new DefaultKubernetesClient(config);
        }

    }
}
