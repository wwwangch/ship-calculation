package com.iscas.common.k8s.tools.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.iscas.common.k8s.tools.K8sClient;
import com.iscas.common.k8s.tools.cfg.K8sConstants;
import com.iscas.common.k8s.tools.model.secret.*;
import com.iscas.common.tools.core.date.DateSafeUtils;
import com.iscas.templet.exception.BaseException;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.SecretList;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import lombok.Cleanup;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;

/**
 * secret工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/18 13:37
 * @since jdk1.8
 */
public class KcSecretUtils {
    private KcSecretUtils() {
    }

    /**
     * 新增secret
     *
     * @param namespace 命名空间
     * @param kcSecret  secret对象，用父类来表示指向子类的引用
     * @throws UnsupportedEncodingException 不支持的编码格式
     * @throws BaseException                基础异常
     * @date 2021/2/19
     * @since jdk1.8
     */
    public static void createOrEditSecret(String namespace, KcSecret kcSecret, boolean edit) throws UnsupportedEncodingException, BaseException {

        String type = kcSecret.getType();
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        switch (type) {
            case "dockerConfig": {
                DockerKcSecret dockerKcSecret = (DockerKcSecret) kcSecret;
                String server = dockerKcSecret.getServer();
                String password = dockerKcSecret.getPassword();
                String username = dockerKcSecret.getUsername();

                JSONObject configJson = new JSONObject();
                JSONObject auths = new JSONObject();
                JSONObject serverJson = new JSONObject();
                Objects.requireNonNull(Objects.requireNonNull(serverJson.put("username", username))
                                .put("password", password))
                        .put("auth", Base64.getEncoder().encodeToString(String.join(":", username, password).getBytes(StandardCharsets.UTF_8)));
                auths.put(server, serverJson);
                configJson.put("auths", auths);
                String dockerconfigjson = Base64.getEncoder().encodeToString(configJson.toString().getBytes(StandardCharsets.UTF_8));
                Map<String, String> data = new HashMap<>(2);
                data.put(".dockerconfigjson", dockerconfigjson);
                if (edit) {
                    edit("kubernetes.io/dockerconfigjson", data, kcSecret, namespace, kc);
                } else {
                    create("kubernetes.io/dockerconfigjson", data, kcSecret, namespace, kc);
                }
                break;
            }
            case "tls": {
                TlsKcSecret tlsKcSecret = (TlsKcSecret) kcSecret;
                String tlsCrt = tlsKcSecret.getTlsCrt();
                String tlsKey = tlsKcSecret.getTlsKey();
                Map<String, String> data = new HashMap<>(2);
                data.put("tls.key", Base64.getEncoder().encodeToString(tlsKey.getBytes(StandardCharsets.UTF_8)));
                data.put("tls.crt", Base64.getEncoder().encodeToString(tlsCrt.getBytes(StandardCharsets.UTF_8)));
                if (edit) {
                    edit("kubernetes.io/tls", data, kcSecret, namespace, kc);
                } else {
                    create("kubernetes.io/tls", data, kcSecret, namespace, kc);
                }
                break;
            }
            case "opaque": {
                OpaqueKcSecret opaqueKcSecret = (OpaqueKcSecret) kcSecret;
                Map<String, String> kcData = opaqueKcSecret.getData();
                Map<String, String> data = new HashMap<>(4);
                if (MapUtils.isNotEmpty(kcData)) {
                    for (Map.Entry<String, String> entry : kcData.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (key == null || value == null) {
                            throw new BaseException("key或value不能为空");
                        }
                        data.put(key, Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8)));
                    }
                }
                if (edit) {
                    edit("Opaque", data, kcSecret, namespace, kc);
                } else {
                    create("Opaque", data, kcSecret, namespace, kc);
                }
                break;
            }
            case "serviceAccount ":
            case "bootstrap": {
                throw new BaseException("此类型secret不允许新增");
            }
            default: {
                throw new BaseException("暂不支持的secret类型");
            }
        }
    }

    /**
     * 获取所有的secret,不区分命名空间和类型
     *
     * @return java.util.List<io.fabric8.kubernetes.api.model.Secret>
     * @date 2021/2/20
     * @since jdk1.8
     */
    public static List<Secret> getSecrets() {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        NonNamespaceOperation<Secret, SecretList, Resource<Secret>> nonNamespaceOperation = kc.secrets();
        if (nonNamespaceOperation != null) {
            SecretList secretList = nonNamespaceOperation.list();
            if (secretList != null) {
                return secretList.getItems();
            }
        }
        return null;
    }

    /**
     * 获取secret
     *
     * @param namespace 命名空间
     * @return java.util.List<com.iscas.common.k8s.tools.model.secret.KcSecret>
     * @throws ParseException               ParseException
     * @throws UnsupportedEncodingException 不支持的编码格式
     * @date 2021/2/18
     * @since jdk1.8
     */
    public static List<KcSecret> getSecrets(String namespace) throws ParseException, UnsupportedEncodingException {
        List<KcSecret> kcSecrets = new ArrayList<>();
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        NonNamespaceOperation<Secret, SecretList, Resource<Secret>> nonNamespaceOperation = kc.secrets().inNamespace(namespace);
        if (nonNamespaceOperation != null) {
            SecretList secretList = nonNamespaceOperation.list();
            if (secretList != null) {
                List<Secret> items = secretList.getItems();
                if (CollectionUtils.isNotEmpty(items)) {
                    for (Secret secret : items) {
                        ObjectMeta metadata = secret.getMetadata();
                        KcSecret kcSecret;
                        Date createTime = DateSafeUtils.parse(metadata.getCreationTimestamp(), K8sConstants.TIME_PATTERN);
                        createTime = CommonUtils.timeOffset(createTime);
                        String type;
                        switch (secret.getType()) {
                            case "kubernetes.io/service-account-token": {
                                kcSecret = new ServiceAccountKcSecret();
                                buildServiceAccount(secret, kcSecret);
                                type = "serviceAccount";
                                break;
                            }
                            case "kubernetes.io/dockerconfigjson": {
                                kcSecret = new DockerKcSecret();
                                buildDockerConfigInfo(secret, kcSecret);
                                type = "dockerConfig";
                                break;
                            }
                            case "Opaque": {
                                kcSecret = new OpaqueKcSecret();
                                buildOpaque(secret, kcSecret);
                                type = "opaque";
                                break;
                            }
                            case "kubernetes.io/tls": {
                                kcSecret = new TlsKcSecret();
                                buildTls(secret, kcSecret);
                                type = "tls";
                                break;
                            }
                            case "bootstrap": {
                                kcSecret = new BootStrapKcSecret();
                                type = "bootstrap";
                                break;
                            }
                            default: {
                                kcSecret = new KcSecret();
                                type = "unknown";
                            }
                        }

                        kcSecret.setNamespace(metadata.getNamespace())
                                .setName(metadata.getName())
                                .setCreateTime(createTime)
                                .setType(type)
                                .setCanEdit(StringUtils.equalsAny(type, "dockerConfig", "opaque", "tls"));
                        kcSecrets.add(kcSecret);
                    }
                }
            }
        }
        return kcSecrets;
    }

    /**
     * 构建tls得信息
     */
    private static void buildTls(Secret secret, KcSecret kcSecret) {
        Map<String, String> data = secret.getData();
        if (MapUtils.isNotEmpty(data)) {
            String tlsCrtBase64 = data.get("tls.crt");
            String tlsKeyBase64 = data.get("tls.key");
            ((TlsKcSecret) kcSecret).setTlsCrt(new String(Base64.getDecoder().decode(tlsCrtBase64), StandardCharsets.UTF_8))
                    .setTlsKey(new String(Base64.getDecoder().decode(tlsKeyBase64), StandardCharsets.UTF_8));
        }
    }

    /**
     * 构建opaque得信息
     */
    private static void buildOpaque(Secret secret, KcSecret kcSecret) {
        Map<String, String> data = secret.getData();
        if (MapUtils.isNotEmpty(data)) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                ((OpaqueKcSecret) kcSecret).getData().put(entry.getKey(), new String(Base64.getDecoder().decode(entry.getValue()), StandardCharsets.UTF_8));
            }
        }
    }

    /**
     * 构建service account得信息
     */
    private static void buildServiceAccount(Secret secret, KcSecret kcSecret) {
        Map<String, String> data = secret.getData();
        if (MapUtils.isNotEmpty(data)) {
            ((ServiceAccountKcSecret) kcSecret).setCrt(data.get("ca.crt"))
                    .setToken(data.get("token"));
        }
    }

    /**
     * 构建docker仓库得信息
     */
    private static void buildDockerConfigInfo(Secret secret, KcSecret kcSecret) {
        Map<String, String> data = secret.getData();
        if (MapUtils.isNotEmpty(data)) {
            String dockerConfigJson = data.get(".dockerconfigjson");
            if (StringUtils.isNotEmpty(dockerConfigJson)) {
                byte[] bytes = Base64.getDecoder().decode(dockerConfigJson);
                String dockerJson = new String(bytes, StandardCharsets.UTF_8);
                if (StringUtils.isNotEmpty(dockerJson)) {
                    JSONObject auths = JSONUtil.parseObj(dockerJson);
                    @SuppressWarnings("AlibabaLowerCamelCaseVariableNaming")
                    JSONObject authsJSONObject = auths.getJSONObject("auths");
                    if (authsJSONObject != null) {
                        Set<String> keys = authsJSONObject.keySet();
                        if (CollectionUtils.isNotEmpty(keys)) {
                            String server = keys.iterator().next();
                            ((DockerKcSecret) kcSecret).setServer(server);
                            JSONObject usrPwdJsonObject = authsJSONObject.getJSONObject(server);
                            if (usrPwdJsonObject != null) {
                                String username = usrPwdJsonObject.getStr("username");
                                String password = usrPwdJsonObject.getStr("password");
                                ((DockerKcSecret) kcSecret).setUsername(username)
                                        .setPassword(password);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void create(String type, Map<String, String> data, KcSecret kcSecret, String namespace, KubernetesClient kc) {
        Secret secret = new Secret();
        secret.setType(type);
        secret.setData(data);
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(kcSecret.getName());
        secret.setMetadata(objectMeta);
        kc.secrets().inNamespace(namespace).create(secret);
    }


    private static void edit(String type, Map<String, String> data, KcSecret kcSecret, String namespace, KubernetesClient kc) throws BaseException {
        Secret secret = new Secret();
        secret.setType(type);
        secret.setData(data);
        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(kcSecret.getName());
        secret.setMetadata(objectMeta);
        Resource<Secret> secretResource = kc.secrets().inNamespace(namespace).withName(kcSecret.getName());
        if (secretResource == null || secretResource.get() == null) {
            throw new BaseException("此secret不存在");
        }
        secretResource.edit(n -> secret);
    }

    public static void delSecret(String namespace, String secretName) throws BaseException {
        @Cleanup KubernetesClient kc = K8sClient.getInstance();
        Resource<Secret> secretResource = kc.secrets().inNamespace(namespace).withName(secretName);
        if (secretResource == null || secretResource.get() == null) {
            throw new BaseException("此secret不存在");
        }
        secretResource.delete();
    }
}
