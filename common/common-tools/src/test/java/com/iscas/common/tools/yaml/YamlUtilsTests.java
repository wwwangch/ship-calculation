package com.iscas.common.tools.yaml;


import org.junit.jupiter.api.Test;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/15 16:55
 * @since jdk1.8
 */
public class YamlUtilsTests {
    @Test
    public void testFormatYaml() {
        String jsonStr = "metadata:\n  annotations:\n    kubectl.kubernetes.io/last-applied-configuration: |\n" +
                "      {\"apiVersion\":\"storage.k8s.io/v1\",\"kind\":\"StorageClass\",\"metadata\":{\"annotations\":{}," +
                "\"name\":\"course-nfs-storage\"},\"provisioner\":\"fuseim.pri/ifs\"}\n    " +
                "storageclass.kubernetes.io/is-default-class: 'true'\n  creationTimestamp: " +
                "'2021-02-20T10:14:35.000+08:00'\n  managedFields:\n  - apiVersion: storage.k8s.io/v1\n    " +
                "manager: kubectl-client-side-apply\n    operation: Update\n    time: '2021-02-20T10:14:35.000+08:00'\n  " +
                "- apiVersion: storage.k8s.io/v1\n    manager: kubectl-patch\n    operation: Update\n    time: " +
                "'2021-02-20T10:16:16.000+08:00'\n  name: course-nfs-storage\n  resourceVersion: '950533'\n  " +
                "uid: 4afa9cb8-cf6e-4310-843b-61d55613c132\nprovisioner: fuseim.pri/ifs\nreclaimPolicy: Delete\n" +
                "volumeBindingMode: Immediate\n";
        String s = YamlUtils.formatToYaml(jsonStr);
        System.out.println(s);
    }
}
