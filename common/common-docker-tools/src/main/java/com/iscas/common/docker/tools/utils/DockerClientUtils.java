package com.iscas.common.docker.tools.utils;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.BuildImageResultCallback;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.PushImageResultCallback;
import com.github.dockerjava.jaxrs.JerseyDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.iscas.common.docker.tools.model.CreateContainerReq;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * <p>docker工具<p/>
 * <p>需要开启远程访问，可参见：<a href="https://blog.csdn.net/u011943534/article/details/112134818">...</a><p/>
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/14 15:58
 * @since jdk1.8
 */
@SuppressWarnings("deprecation")
@Slf4j
public class DockerClientUtils {
    /**
     * api的版本号，可以在远程docker服务上运行docker version看到
     */
    private static final String API_VERSION = "1.40";

    /**
     * docker httpclient连接信息
     */
    public static int READ_TIMEOUT = 1000;
    public static int CONNECT_TIMEOUT = 1000;
    public static int MAX_TOTAL_CONNECTIONS = 100;
    public static int MAX_PER_ROUTE_CONNECTIONS = 10;


    private DockerClientUtils() {
    }

    /**
     * 连接到远程的docker
     *
     * @param host       连接host信息，如tcp://172.16.10.163:2375
     * @param apiVersion 使用的API版本，可以在远程docker服务上运行docker version看到
     * @return com.github.dockerjava.api.DockerClient
     * @throws URISyntaxException uri异常
     * @date 2021/9/14
     * @since jdk1.8
     */
    public static DockerClient connectDocker(String host, String apiVersion) throws URISyntaxException {
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                /*.withDockerHost(host)*/.withApiVersion(apiVersion).build();
        DockerHttpClient httpClient = new JerseyDockerHttpClient.Builder()
                .readTimeout(READ_TIMEOUT)
                .connectTimeout(CONNECT_TIMEOUT)
                .maxPerRouteConnections(MAX_PER_ROUTE_CONNECTIONS)
                .maxTotalConnections(MAX_TOTAL_CONNECTIONS)
                .dockerHost(new URI(host)).build();
        DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);
        Info info = dockerClient.infoCmd().exec();
        LogUtils.debug(log, "docker信息:-->" + info.toString());
        return dockerClient;
    }

    /**
     * 连接到远程的docker,apiVersion使用类上的配置
     *
     * @param host 连接host信息，如tcp://172.16.10.163:2375
     * @return com.github.dockerjava.api.DockerClient
     * @throws URISyntaxException uri异常
     * @date 2021/9/14
     * @since jdk1.8
     */
    public static DockerClient connectDocker(String host) throws URISyntaxException {
        return connectDocker(host, API_VERSION);
    }

    /**
     * 创建容器
     *
     * @param client             容器客户端
     * @param createContainerReq 创建容器请求
     * @return com.github.dockerjava.api.command.CreateContainerResponse
     * @date 2021/9/15
     * @since jdk1.8
     */
    public static CreateContainerResponse createContainer(DockerClient client, CreateContainerReq createContainerReq) {
        CreateContainerCmd containerCmd = client.createContainerCmd(createContainerReq.getImageName());
        //设置名字
        Optional.ofNullable(createContainerReq.getContainerName()).ifPresent(containerCmd::withName);

        //设置端口绑定
        if (createContainerReq.getPorts() != null) {
            List<PortBinding> portBindings = new ArrayList<>();
            for (CreateContainerReq.Port port : createContainerReq.getPorts()) {
                Integer exposedPort = port.getExposedPort();
                Integer bindPort = port.getBindPort();
                Ports.Binding binding = bindPort == null ? Ports.Binding.empty() : Ports.Binding.bindPort(bindPort);
                PortBinding portBinding = new PortBinding(binding, new ExposedPort(exposedPort));
                portBindings.add(portBinding);
            }
            Objects.requireNonNull(containerCmd.getHostConfig()).withPortBindings(portBindings);
        }

        //设置环境变量
        Optional.ofNullable(createContainerReq.getEnvs()).ifPresent(containerCmd::withEnv);

        //设置entrypiont
        Optional.ofNullable(createContainerReq.getEntrypoints()).ifPresent(containerCmd::withEntrypoint);

        //设置挂载
        if (createContainerReq.getBindVolumes() != null) {
            List<Bind> binds = new ArrayList<>();
            for (CreateContainerReq.BindVolume bindVolume : createContainerReq.getBindVolumes()) {
                binds.add(new Bind(bindVolume.getBind(), new Volume(bindVolume.getVol()), bindVolume.getAccessMode()));
            }
            Objects.requireNonNull(containerCmd.getHostConfig()).withBinds(binds);
        }

        //创建
        return containerCmd.exec();
    }

    /**
     * 启动容器
     *
     * @param client docker客户端
     * @param containerId 容器ID
     */
    public static void startContainer(DockerClient client, String containerId) {
        client.startContainerCmd(containerId).exec();
    }

    /**
     * 运行容器(创建并启动容器)
     *
     * @param client docker 客户端
     * @param createContainerReq 创建容器的信息
     */
    public static void runContainer(DockerClient client, CreateContainerReq createContainerReq) {
        CreateContainerResponse containerRes = createContainer(client, createContainerReq);
        Optional.ofNullable(containerRes).ifPresent(ccr -> startContainer(client, ccr.getId()));
    }

    /**
     * 停止容器
     *
     * @param client docker客户端
     * @param containerId 容器ID
     */
    public static void stopContainer(DockerClient client, String containerId) {
        client.stopContainerCmd(containerId).exec();
    }

    /**
     * 删除容器
     *
     * @param client docker客户端
     * @param containerId 容器ID
     */
    public static void removeContainer(DockerClient client, String containerId) {
        client.removeContainerCmd(containerId).exec();
    }

    /**
     * 构建docker镜像
     */
    public static String buildImage(DockerClient client, File dockerFileOrFolder, String imageName, String imageTag) {
        String imageId = client.buildImageCmd(dockerFileOrFolder)
                .withTags(new HashSet<String>() {{
                    add(imageName + ":" + imageTag);
                }})
                .exec(new BuildImageResultCallback() {
                    @Override
                    public void onNext(BuildResponseItem item) {
                        log.debug(item.getStream());
                        super.onNext(item);
                    }
                })
                .awaitImageId();
        log.debug("build success,imageId:" + imageId);
        return imageId;
    }

    /**
     * 为镜像打标签
     */
    public static void tagImage(DockerClient client, String imageId, String imageName, String imageTag) {
        client.tagImageCmd(imageId, imageName, imageTag).exec();
    }

    /**
     * 推送镜像
     */
    public static void pushImage(DockerClient client, String imageAndTag, String registryAddress,
                                 String registryUserName, String registryPassword) {
        AuthConfig authConfig = new AuthConfig()
                .withUsername(registryUserName)
                .withPassword(registryPassword)
                .withRegistryAddress(registryAddress);

        PushImageResultCallback pushImageResultCallback = new PushImageResultCallback() {
            @Override
            public void onNext(PushResponseItem item) {
                log.debug("id:" + item.getId() + " status: " + item.getStatus());
                super.onNext(item);
            }

            @Override
            public void onComplete() {
                log.debug("Image pushed completed!");
                super.onComplete();
            }
        };
        client.pushImageCmd(imageAndTag)
                .withAuthConfig(authConfig)
                .exec(pushImageResultCallback)
                .awaitSuccess();
    }

    /**
     * 删除镜像
     */
    public static void deleteImage(DockerClient client, String imageId) {
        client.removeImageCmd(imageId).withForce(true).exec();
    }

    public static List<Container> listContainer(DockerClient client) {
        return client.listContainersCmd().exec();
    }

}
