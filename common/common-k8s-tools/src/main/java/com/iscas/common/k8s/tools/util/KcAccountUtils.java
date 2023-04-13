package com.iscas.common.k8s.tools.util;

import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.model.account.KcServiceAccount;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ServiceAccount;
import io.fabric8.kubernetes.api.model.ServiceAccountList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户相关工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/1 19:25
 * @since jdk1.8
 */
public class KcAccountUtils {
    private KcAccountUtils() {}

    /**
     * 获取用户
     * */
    public static List<KcServiceAccount> getAccounts() {
        List<KcServiceAccount> accounts = new ArrayList<>();
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        MixedOperation<ServiceAccount, ServiceAccountList, Resource<ServiceAccount>> serviceAccounts = kc.serviceAccounts();
        if (serviceAccounts != null) {
            ServiceAccountList serviceAccountList = serviceAccounts.list();
            if (serviceAccountList != null) {
                List<ServiceAccount> items = serviceAccountList.getItems();
                if (CollectionUtils.isNotEmpty(items)) {
                    for (ServiceAccount item : items) {
                        ObjectMeta metadata = item.getMetadata();
                        String name = metadata.getName();
                        String namespace = metadata.getNamespace();
                        boolean flag = false;
                        for (KcServiceAccount account : accounts) {
                            String username = account.getUsername();
                            if (Objects.equals(name, username)) {
                                account.getNamespace().add(namespace);
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            KcServiceAccount account = new KcServiceAccount();
                            account.setUsername(name);
                            account.getNamespace().add(namespace);
                            accounts.add(account);
                        }

                    }
                }
            }
        }

        return accounts;
    }
}
