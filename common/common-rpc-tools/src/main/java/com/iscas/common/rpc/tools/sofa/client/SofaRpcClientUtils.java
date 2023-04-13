package com.iscas.common.rpc.tools.sofa.client;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.iscas.common.rpc.tools.sofa.SofaRpcOptions;

/**
 * sofa-rpc客户端工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/20 11:44
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class SofaRpcClientUtils {
    private SofaRpcClientUtils(){}

    /**
     * 获取服务端接口的实现
     *
     * 使用注册中心的一个例子：
     *         // 指定注册中心
     *         RegistryConfig registryConfig = new RegistryConfig()
     *                 .setProtocol("zookeeper")
     *                 .setAddress("127.0.0.1:2181");
     *         // 引用一个服务
     *         ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
     *                 .setInterfaceId(HelloService.class.getName())
     *                 .setProtocol("bolt")
     *                 .setRegistry(registryConfig);
     *         // 拿到代理类
     *         HelloService service = consumerConfig.refer();
     *
     * @since jdk1.8
     * @date 2020/11/20
     * @param consumerConfig 消费者定义
     * @return T
     */
    public static <T> T getInstance(ConsumerConfig<T> consumerConfig) {
        return consumerConfig.refer();
    }

    public static <T> T getSimpleInstance(Class<T> interfaceClass, String ip, int port) {
        ConsumerConfig<T> consumerConfig = new ConsumerConfig<T>()
                .setInterfaceId(interfaceClass.getName())
                .setProtocol(SofaRpcOptions.DEFAULT_PROTOCOL)
                .setDirectUrl(SofaRpcOptions.DEFAULT_PROTOCOL + "://" + ip + ":" + port);
        // Generate the proxy class
        return consumerConfig.refer();
    }

}
