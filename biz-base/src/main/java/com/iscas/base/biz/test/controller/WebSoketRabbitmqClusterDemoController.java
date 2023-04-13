//package com.iscas.base.biz.test.controller;
//
//import com.iscas.templet.common.ResponseEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * 如有要看例子，请打开注释
// *
// **/
//@RestController
//public class WebSoketRabbitmqClusterDemoController {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//
//    @Autowired
//    private RabbitMessagingTemplate rabbitMessagingTemplate;
//
//
//
//    @GetMapping("/cluster/test/rabbimq/broadcast")
//    public ResponseEntity testClusterRabbitMqBroadCast() throws Exception {
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
////                messageSendingOperations.convertAndSend("/topic/message", "success");
////                messageSendingOperations.convertAndSend("/topic/message", "success");
////                rabbitMessagingTemplate.convertAndSend("/topic/message", "success");
//                rabbitMessagingTemplate.convertAndSend("amq.topic", "message", "2wegw");
////                rabbitMessagingTemplate.convertAndSend("/queue/stomp-subscription-CHo_0rxGwjmmC0vPbOM_fA", "success");
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 10, 1000);
//
//        return new ResponseEntity();
//    }
//
//    @GetMapping("/cluster/test/rabbimq/p2p")
//    public ResponseEntity testClusterRabbitMqP2p() throws Exception {
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
////                messageSendingOperations.convertAndSendToUser("lalala", "/queue/message", "success");
//                rabbitMessagingTemplate.convertAndSend("amq.topic", "message.zhangsan", "2wegw");
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 10, 1000);
//        return new ResponseEntity();
//    }
//
//}
