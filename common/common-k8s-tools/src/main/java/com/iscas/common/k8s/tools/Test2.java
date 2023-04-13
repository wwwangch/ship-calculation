package com.iscas.common.k8s.tools;//package com.iscas.common.k8s.tools;
//
//import com.iscas.common.tools.core.date.DateSafeUtils;
//import io.fabric8.kubernetes.api.model.*;
//import io.fabric8.kubernetes.api.model.apps.*;
//import io.fabric8.kubernetes.client.Config;
//import io.fabric8.kubernetes.client.ConfigBuilder;
//import io.fabric8.kubernetes.client.DefaultKubernetesClient;
//import io.fabric8.kubernetes.client.KubernetesClient;
//import io.fabric8.kubernetes.client.dsl.AppsAPIGroupDSL;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.collections4.MapUtils;
//
//import java.text.ParseException;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * 參考 https://github.com/fabric8io/kubernetes-client
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2019/10/30 15:55
// * @since jdk1.8
// */
//
//public class Test2 {
//    public static void main(String[] args) throws ParseException {
//
//        //第二個參數是證書
//        //從他們集群裡拷貝出來，我這的集群是在/etc/kubernetes/pki/ca.crt
////        System.setProperty("kubernetes.certs.ca.file", "C:\\Program Files\\Java\\jdk-11.0.5\\lib\\security\\ca.crt");
//        System.setProperty("kubernetes.certs.ca.file", "C:\\Users\\Administrator\\Desktop\\ca.crt");
//        Config config = new ConfigBuilder()
//                //這是k8s集群訪問的TOKEN
//                .withOauthToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImNsdXN0ZXItYWRtaW4tZGFzaGJvYXJkLXNhLXRva2VuLXhzcWo4Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImNsdXN0ZXItYWRtaW4tZGFzaGJvYXJkLXNhIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiMTE1MzMzYTYtMjY2Mi00NmQyLTkxMDItMDI5N2Q2YmIyMzBlIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OmRlZmF1bHQ6Y2x1c3Rlci1hZG1pbi1kYXNoYm9hcmQtc2EifQ.syjMBQAQYVlR50m2cNKb-eOPIBZbop2bhw1fT4limqJv_JvIZgqwnH4kD1yFMGmUn39XIdwEfAY5r2PCky99YbJ-JkMdnAm-A_9nNoU1-muK2c28oks07UZI2KZf_lTjlwbNAm-6CEb3rgozSCLhHvqlQka_cxHUa4xqWH2D7BCWZj-n1uYQkjdEezi-jyM-OEiP5RKScElvEBb2UbQWQnLVcJMpryEqFKwCrPbTI3Cz0J2i67R4ffYMG22app4Ucl3QthVWlt8I89F2P-J_7PT0L1LvZUf3wAn34YE722Po_E328gC35mS9sHgC-AqwdfejcEq3I1srKWrKcbtGEQ")
////                .withOauthToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImRlZmF1bHQtdG9rZW4tNWZrMjQiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiZGVmYXVsdCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjNjOWVlMjI2LWE2YTQtNGE0Yi05YjdmLWVhODRiMzMwNGY4MiIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmRlZmF1bHQifQ.FGCvmtFrwahz2gLwRBT_rAFYccMZwn-uG7pRXxnCq_XyJ9ZcmS68JMzDLj3PqxY6s4UBLZenwTy0JjhQuhEKsKVLnqDYxc1nXbvCSFzisDVNpW732c8KqtNgqIH2DHXGYB67TcNxVr4dgY0H75k0_CW8p0hXjPW6tNhaVNg_HXR8kJDmKQ-jyYCZeAIVAy-FtcXmduMt5qMjt9F66mMn2YFrdyQyiUp46PLbvJegh2H9_o-RAim8O8anWnTYcSr_T8DBhnxuXD69fdvS0rGM8qK4woxp8tMyj_ixkj3z0K1nd4HOhZQNYulIsSlJz8AW1Wu52CqAaEDbPRcifFNknQ")
//                //固定用v1就行
//                .withApiVersion("v1")
//                //這是k8s的master節點地址，端口如果沒改應該也是6443
//                .withMasterUrl("https://192.168.100.94:6443").build();
//        KubernetesClient client = new DefaultKubernetesClient(config);
//        AppsAPIGroupDSL apps = client.apps();
//
//        PodList list = client.pods().inNamespace("kube-ops").list();
//
//        //命名空間，根據你發給我的截圖，他們應該用的是default
//        DeploymentList deploymentList = apps.deployments().inNamespace("kube-ops").list();
//
//
//        //獲取deployments
//        if (deploymentList != null) {
//            List<Deployment> items = deploymentList.getItems();
//            if (CollectionUtils.isNotEmpty(items)) {
//                for (Deployment deployment : items) {
//
//                    //Name
//                    String name = "";
//                    //Labels
//                    String labels = "";
//                    String images = "";
//                    String pods = "";
//                    //age 毫秒
//                    long age = 0;
//
//                    //錯誤
//                    String reason = "";
//
//                    ObjectMeta metadata = deployment.getMetadata();
//                    name = metadata.getName();
//                    Map<String, String> labelMap = metadata.getLabels();
//                    if (MapUtils.isNotEmpty(labelMap)) {
//                        for (Map.Entry<String, String> entry: labelMap.entrySet()) {
//                            String key = entry.getKey();
//                            String value = entry.getValue();
//                            labels += key.concat(":").concat(value).concat(";");
//                        }
//                        labels = labels.substring(0, labels.length() - 1);
//                    }
//                    DeploymentSpec spec = deployment.getSpec();
//                    if (spec != null) {
//                        PodTemplateSpec template = spec.getTemplate();
//                        if (template != null) {
//                            PodSpec spec1 = template.getSpec();
//                            if (spec1 != null) {
//                                List<Container> containers = spec1.getContainers();
//                                if (CollectionUtils.isNotEmpty(containers)) {
//                                    for (Container container : containers) {
//                                        String image = container.getImage();
//                                        images += image.concat(";");
//                                    }
//                                }
//                                images = images.substring(0, images.length() - 1);
//                            }
//                        }
//                    }
//                    DeploymentStatus status = deployment.getStatus();
//
//                    if (status != null) {
//                        List<DeploymentCondition> conditions = status.getConditions();
//                        Integer replicas = status.getReplicas();
//                        Integer readyReplicas = status.getReadyReplicas();
//                        pods = readyReplicas + "/" + replicas;
//                        if (!Objects.equals(readyReplicas, replicas)) {
//                            if (CollectionUtils.isNotEmpty(conditions)) {
//                                reason = status.getConditions().get(0).getReason();
//                            }
//
//                        }
//                        if (CollectionUtils.isNotEmpty(conditions)) {
//                            String lastTransitionTime = conditions.get(0).getLastTransitionTime();
//                            lastTransitionTime = lastTransitionTime.substring(0, 10) + " " + lastTransitionTime.substring(11, 19);
//                            Date date = DateSafeUtils.parse(lastTransitionTime, "yyyy-MM-dd HH:mm:ss");
//                            long time = date.getTime();
//                            age = (System.currentTimeMillis() - time);
//                        }
//                    }
//
//                    System.out.println("name:::" + name + ";;;;labels:::" + labels + ";;;;images:::"
//                            + images + ";;;;;pods:::" + pods  + ";;;;;:::age:::" + age + ";;;;reason:::" + reason);
//                }
//            }
//        }
//
//
//    }
//}
