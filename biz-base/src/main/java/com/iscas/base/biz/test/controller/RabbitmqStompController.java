//package com.iscas.base.biz.test.controller;
//
//import com.iscas.base.biz.model.stomp.WsMessage;
//import com.iscas.common.web.tools.json.JsonUtils;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//
///**
// * rabbitmq-stomp测试
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/12/21 11:28
// * @since jdk1.8
// */
//@Controller
//public class RabbitmqStompController {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    /**
//     * 服务端推送给单人的接口
//     *
//     * @param uid
//     * @param content
//     */
//    @ResponseBody
//    @GetMapping("/sendToOne")
//    public void sendToOne(@RequestParam("uid") String uid, @RequestParam("content") String content) {
//
//        WsMessage chatMessage = new WsMessage();
//        chatMessage.setType(WsMessage.MessageType.P2P);
//        chatMessage.setContent(content);
//        chatMessage.setTo(uid);
//        chatMessage.setSender("系统消息");
//        rabbitTemplate.convertAndSend("topicWebSocketExchange", "topic.public", JsonUtils.toJson(chatMessage));
//
//    }
//
//
//    /**
//     * 接收 客户端传过来的消息 通过setSender和type 来判别时单发还是群发
//     *
//     * @param chatMessage
//     * @param principal
//     */
//    @MessageMapping("/chat.sendMessageTest")
//    public void sendMessageTest(@Payload WsMessage chatMessage, Principal principal) {
//        try {
//
//            String name = principal.getName();
//            chatMessage.setSender(name);
//            rabbitTemplate.convertAndSend("topicWebSocketExchange", "topic.public", JsonUtils.toJson(chatMessage));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//    /**
//     * 接收 客户端传过来的消息 上线消息
//     *
//     * @param chatMessage
//     */
//    @MessageMapping("/chat.addUser")
//    public void addUser(@Payload WsMessage chatMessage) {
//
//        System.out.println("有用户加入到了websocket 消息室" + chatMessage.getSender());
//        try {
//
//            System.out.println(chatMessage.toString());
//            rabbitTemplate.convertAndSend("topicWebSocketExchange", "topic.public", JsonUtils.toJson(chatMessage));
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//}
