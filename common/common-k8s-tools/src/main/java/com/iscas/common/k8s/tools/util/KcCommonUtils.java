package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.model.KcVersion;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.VersionInfo;
import lombok.Cleanup;

/**
 * 通用工具
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/12/2 14:12
 * @since jdk1.8
 */
public class KcCommonUtils {
    private KcCommonUtils() {}

    /**
     *  获取当前kubernetes集群的版本号
     * */
    public static String getKcVersion() {
        KcVersion kcVersion = getKcVersionInfo();
        if (kcVersion != null) {
            return kcVersion.getGitVersion();
        }
        return null;
    }

    /**
     *  获取当前kubernetes集群的版本号详细信息
     * */
    public static KcVersion getKcVersionInfo() {
        KcVersion kcVersion = null;
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        VersionInfo versionInfo = kc.getVersion();
        if (versionInfo != null) {
            kcVersion = new KcVersion();
            kcVersion.setCompiler(versionInfo.getCompiler())
                .setGitCommit(versionInfo.getGitCommit())
                .setGitVersion(versionInfo.getGitVersion())
                .setGoVersion(versionInfo.getGoVersion())
                .setPlatform(versionInfo.getPlatform())
                .setBuildDate(versionInfo.getBuildDate());
        }
        return kcVersion;
    }



}
