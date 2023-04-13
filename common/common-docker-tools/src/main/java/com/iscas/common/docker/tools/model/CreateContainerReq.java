package com.iscas.common.docker.tools.model;

import com.github.dockerjava.api.model.AccessMode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 创建容器的信息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/14 16:45
 * @since jdk1.8
 */
@Data
@Accessors(chain = true)
public class CreateContainerReq {
    /**镜像名*/
    private String imageName;

    /**容器映射信息*/
    private List<Port> ports;

    /**容器名*/
    private String containerName;

    /**环境变量 key=val*/
    private List<String> envs;

    /**entrypiont*/
    private List<String> entrypoints;

    /**
     * 挂载
     * */
    private List<BindVolume> bindVolumes;

    @Data
    @Accessors(chain = true)
    public static class Port {
        /**容器内部暴漏端口*/
        private Integer exposedPort;

        /**宿主机绑定端口*/
        private Integer bindPort;
    }

    @Data
    @Accessors(chain = true)
    public static class BindVolume {
        /**宿主机路径*/
        private String bind;

        /**容器内路径*/
        private String vol;

        /**读写模式*/
        private AccessMode accessMode = AccessMode.DEFAULT;

    }

}
