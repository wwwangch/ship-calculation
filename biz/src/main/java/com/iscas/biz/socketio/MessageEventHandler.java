//package com.iscas.base.biz.config.socketio;
//
//import com.corundumstudio.socketio.AckRequest;
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.annotation.OnConnect;
//import com.corundumstudio.socketio.annotation.OnDisconnect;
//import com.corundumstudio.socketio.annotation.OnEvent;
//import com.iscas.base.biz.config.socketio.IEventHandler;
//import com.iscas.base.biz.config.socketio.SocketIOStaticInfo;
//import com.iscas.common.tools.core.random.RandomStringUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author zhuquanwen
// * @version 1.0
// * @date 2018/7/22 18:32
// * @since jdk1.8
// */
//@Component
//@ConditionalOnClass(SocketIOServer.class)
//@Slf4j
//public class MessageEventHandler implements IEventHandler {
//    private final SocketIOServer socketIOServer;
//    private static int testPushCount = 0;
//
//
//    public static ArrayList<UUID> listClient = new ArrayList<>();
//
//    public MessageEventHandler(SocketIOServer socketIOServer) {
//        this.socketIOServer = socketIOServer;
//    }
//
//    @Override
//    @OnConnect
//    public void onConnect(SocketIOClient client) {
//
//        String token = client.getHandshakeData().getSingleUrlParam("Authorization");
//        if (token == null) {
//            log.error("客户端" + client.getSessionId() + "建立websocket连接失败，Authorization不能为null");
//            client.disconnect();
//            return;
//        }
//
//        Map header = new HashMap<>();
//        header.put("Authorization", token);
//
//        String username = null;
////        try {
////            Map<String, Claim> claimMap = JWTUtils.verifyToken(token);
////            username = claimMap.get("username").asString();
////            if (username == null) {
////                throw new RuntimeException("websocket认证失败");
////            }
////        } catch (UnsupportedEncodingException e) {
////            e.printStackTrace();
////            throw new RuntimeException("websocket认证失败", e);
////        } catch (ValidTokenException e) {
////            e.printStackTrace();
////            throw new RuntimeException("websocket认证失败", e);
////        }
//        username = token;
//        if (username != null) {
//            log.info("客户端" + client.getSessionId() + "建立websocket连接成功");
//            listClient.add(client.getSessionId());
//            //将用户名和clientId对应 方便推送时候使用
//            SocketIOStaticInfo.userClientIdMap.put(username, client.getSessionId());
//        } else {
//            log.error("客户端" + client.getSessionId() + "建立websocket连接失败");
//            client.disconnect();
//        }
//
//    }
//
//
//    @Override
//    @OnDisconnect
//    public void onDisConnect(SocketIOClient client) {
//        String token = client.getHandshakeData().getSingleUrlParam("Authorization");
//        //TODO 断开连接
//        log.info("客户端" + client.getSessionId() + "断开websocket连接成功");
//        listClient.remove(client.getSessionId());
//
//        //移除
//        for (Map.Entry<String, UUID> entry : SocketIOStaticInfo.userClientIdMap.entrySet()) {
//            if (Objects.equals(entry.getValue(), client.getSessionId())) {
//                SocketIOStaticInfo.userClientIdMap.remove(entry.getKey());
//            }
//        }
//    }
//
//    @OnEvent(value = "aaaa")
//    public void onEvent(SocketIOClient client, AckRequest request, String data) {
//        log.debug("发来消息：" + data);
//        UUID sessionId = client.getSessionId();
//        socketIOServer.getClient(sessionId).sendEvent("bbbb", "点对点消息的返回" + Math.random());
//    }
//
//    /**
//     * 测试无限推送
//     * */
//    @OnEvent(value = "testPush")
//    public void onTestPushEvent(SocketIOClient client, AckRequest request, String data) {
//        UUID sessionId = client.getSessionId();
//        Runnable runnable = () -> {
//            testPushCount++;
//            int thisTestPushCount = testPushCount;
//            for (; ; ) {
//                if (thisTestPushCount < testPushCount) {
//                    break;
//                }
//                socketIOServer.getClient(sessionId).sendEvent("testPush", RandomStringUtils.randomStr(1024 * 200));
//                try {
//                    TimeUnit.MILLISECONDS.sleep(900);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        new Thread(runnable).start();
//    }
//
//    /**
//     * 测试加入房间
//     * */
//    @OnEvent(value = "joinRoom")
//    public void onTestJoinRoomEvent(SocketIOClient client, AckRequest request, String data) {
//        client.leaveRoom(data);
//        client.joinRoom(data);
//    }
//
//    /**
//     * 测试房间发送信息(类似于订阅式广播消息)
//     * */
//    @OnEvent(value = "testRoom")
//    public void onTestRoomEvent(SocketIOClient client, AckRequest request, String data) {
//        socketIOServer.getRoomOperations("room1").sendEvent("testRoom", "房间里的消息" + Math.random());
//    }
//
//    /**
//     * 测试发送广播消息
//     * */
//    @OnEvent(value = "testBroadcast")
//    public void onTestBroadcastEvent(SocketIOClient client, AckRequest request, String data) {
//        socketIOServer.getBroadcastOperations().sendEvent("testBroadcast", "广播的消息" + Math.random());
//    }
//
//    /**
//     * 测试按照命名空间发送消息
//     * */
//    @OnEvent(value = "testNamespace")
//    public void onTestCreateNs(SocketIOClient client, AckRequest request, String data) {
//        client.getNamespace().getBroadcastOperations().sendEvent("testNamespace",
//                "命名空间'" + client.getNamespace().getName() + "'的广播消息");
//    }
//
//}
