package com.iscas.biz.socketio;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/3/25 10:15
 * @since jdk1.8
 */

@SuppressWarnings({"unused", "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc", "rawtypes", "MismatchedQueryAndUpdateOfCollection", "unchecked"})
public interface IEventHandler {
    void onConnect(SocketIOClient client);
    void onDisConnect(SocketIOClient client);


    default void connect(SocketIOClient client) {
        String token = client.getHandshakeData().getSingleUrlParam("Authorization");
        if (token == null) {
            System.err.println("客户端" + client.getSessionId() + "建立websocket连接失败，Authorization不能为null");
            client.disconnect();
            return;
        }

        Map header = new HashMap<>(2);
        header.put("Authorization", token);

        System.out.println("客户端" + client.getSessionId() + "建立websocket连接成功");
        //将用户名和clientId对应 方便推送时候使用
        SocketIOStaticInfo.userClientIdMap.put(token, client.getSessionId());

    }

    default void disconnect(SocketIOClient client) {
        System.out.println("客户端" + client.getSessionId() + "断开websocket连接成功");
        //移除
        for (Map.Entry<String, UUID> entry : SocketIOStaticInfo.userClientIdMap.entrySet()) {
            if (Objects.equals(entry.getValue(), client.getSessionId())) {
                SocketIOStaticInfo.userClientIdMap.remove(entry.getKey());
            }
        }
    }

}
