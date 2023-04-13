package com.iscas.base.biz.config;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.iscas.base.biz.util.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/25 10:07
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "AlibabaLowerCamelCaseVariableNaming"})
@Component
public class ServerRunner implements CommandLineRunner {
    @Autowired(required = false)
    private SocketIOServer socketIOServer;

    @Value("${socket.io.namespaces:null}")
    private String[] namespaces;

    @Override
    public void run(String... args) throws Exception {
        if (socketIOServer != null) {
            Optional.ofNullable(namespaces).ifPresent(nss ->
                    Arrays.stream(nss).forEach(ns -> {
                        //获取命名空间
                        SocketIONamespace socketIONamespace = socketIOServer.getNamespace(ns);
                        //获取期待的类名
                        String className = ns.substring(1) + "MessageEventHandler";
                        try {
                            Object bean = SpringUtils.getBean(className);
                            Optional.of(bean).ifPresent(socketIONamespace::addListeners);
                        } catch (Exception ignored) {

                        }

                    }));
            socketIOServer.start();
        }
    }
}
