package com.iscas.common.rpc.tools.sofa.server;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.iscas.common.rpc.tools.sofa.SofaRpcOptions;
import lombok.extern.slf4j.Slf4j;

/**
 * sofa-rpc服务端工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/20 11:29
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
@Slf4j
public class SofaRpcServerUtils {
    private SofaRpcServerUtils(){}

    /**
     * 发布一个服务
     *
     * 带注册中心的一个例子:
     *
     *         // 指定注册中心
     *         RegistryConfig registryConfig = new RegistryConfig()
     *                 .setProtocol("zookeeper")
     *                 .setAddress("127.0.0.1:2181");
     *         // 指定服务端协议和地址
     *         ServerConfig serverConfig = new ServerConfig()
     *                 .setProtocol("bolt")
     *                 .setPort(12345)
     *                 .setDaemon(false);
     *         //　发布一个服务
     *         ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
     *                 .setInterfaceId(HelloService.class.getName())
     *                 .setRef(new HelloServiceImpl())
     *                 .setRegistry(registryConfig)
     *                 .setServer(serverConfig);
     *         providerConfig.export();
     *
     *
     * @since jdk1.8
     * @date 2020/11/20
     * @param providerConfig 服务的定义
     */
    public static void export(ProviderConfig providerConfig) {
        providerConfig.export();
        String msg = String.format("sofa-rpc服务,ID:[%s]已发布", providerConfig.getInterfaceId());
        log.info(msg);
        if (!log.isInfoEnabled()) {
            System.out.println(msg);
        }
    }

    /**
     * 发布一个服务，不使用注册中心，使用bolt协议，其他参数都是默认参数
     * @since jdk1.8
     * @date 2020/11/20
     * @param interfaceClass 服务接口的Class
     * @param entity 服务接口的实现类
     * @param port 对外发布的端口
     */
    public static <T> void simpleExport(Class<T> interfaceClass, T entity, int port) {
        ServerConfig serverConfig = new ServerConfig()
                .setProtocol(SofaRpcOptions.DEFAULT_PROTOCOL)
                .setPort(port)
                .setDaemon(false);
        ProviderConfig<T> providerConfig = new ProviderConfig<T>()
                .setInterfaceId(interfaceClass.getName())
                .setRef(entity)
                .setServer(serverConfig);
        export(providerConfig);

    }
}
