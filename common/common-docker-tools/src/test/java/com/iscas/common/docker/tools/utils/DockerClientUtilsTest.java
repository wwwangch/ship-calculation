package com.iscas.common.docker.tools.utils;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.AccessMode;
import com.github.dockerjava.api.model.Container;
import com.iscas.common.docker.tools.model.CreateContainerReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/14 16:25
 * @since jdk1.8
 */
public class DockerClientUtilsTest {
    private DockerClient dockerClient = null;

    @BeforeEach
    public void init() throws URISyntaxException {
        dockerClient = DockerClientUtils.connectDocker("tcp://172.16.10.163:2375");
    }

    /**
     * 测试获取连接
     */
    @Test
    public void testDockerClient() throws URISyntaxException {
        DockerClient dockerClient = DockerClientUtils.connectDocker("tcp://172.16.10.163:2375", "1.40");
        Assertions.assertNotNull(dockerClient);
    }

    /**
     * 测试创建容器
     */
    @Test
    public void testCreateDocker() {
        CreateContainerResponse containerRes = DockerClientUtils.createContainer(dockerClient,
                new CreateContainerReq()
                        .setImageName("nginx:latest")
                        .setPorts(Arrays.asList(new CreateContainerReq.Port().setBindPort(8080).setExposedPort(80)))
                        .setContainerName("nginx-test")
                        .setEnvs(Arrays.asList("key1=val1", "key2=val2"))
//                        .setEntrypoints(Arrays.asList("java -jar xxx.jar"))
                .setBindVolumes(Arrays.asList(new CreateContainerReq.BindVolume().setBind("/tmp").setVol("/tmp").setAccessMode(AccessMode.DEFAULT)))
        );
        System.out.println(containerRes);
    }

    /**
     * 测试启动容器
     */
    @Test
    public void testStartDocker() {
        DockerClientUtils.startContainer(dockerClient, "a1dddcde0097");
    }

    /**
     * 测试停止容器
     */
    @Test
    public void testStopDocker() {
        DockerClientUtils.stopContainer(dockerClient, "a1dddcde0097");
    }

    /**
     * 测试删除容器
     */
    @Test
    public void testRemoveDocker() {
        DockerClientUtils.removeContainer(dockerClient, "a1dddcde0097");
    }

    /**
     * 测试运行容器
     */
    @Test
    public void testRunDocker() {
        CreateContainerReq createContainerReq = new CreateContainerReq()
                .setImageName("nginx:latest")
                .setPorts(Arrays.asList(new CreateContainerReq.Port().setBindPort(8080).setExposedPort(80)))
                .setContainerName("nginx-test")
                .setEnvs(Arrays.asList("key1=val1", "key2=val2"))
//                        .setEntrypoints(Arrays.asList("java -jar xxx.jar"))
                .setBindVolumes(Arrays.asList(new CreateContainerReq.BindVolume().setBind("/tmp").setVol("/tmp").setAccessMode(AccessMode.DEFAULT)));
        DockerClientUtils.runContainer(dockerClient, createContainerReq );
    }

    /**
     * 测试查询容器信息
     */
    @Test
    public void testListDocker() {
        List<Container> containers = DockerClientUtils.listContainer(dockerClient);
        System.out.println(containers);
    }

    /**
     * 测试创建镜像
     * */
    @Test
    public void testBuildImage() {
        DockerClientUtils.buildImage(dockerClient, new File("D:/文档资料/_部署安装/离线安装k8s/17、部署cpaas-manager/前后台打镜像/20210402/frontend"),
                "test", "0.0.2");
    }

}