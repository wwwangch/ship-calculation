//package com.iscas.base.biz.test.controller;
//
//import com.iscas.templet.common.ResponseEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessageSendingOperations;
//import org.springframework.messaging.simp.user.SimpUserRegistry;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * 如有要看例子，请打开注释
// **/
//@RestController
//public class WebSoketRabbitmqDemoController {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    //spring提供的发送消息模板
//    @Autowired
//    private SimpMessageSendingOperations messageSendingOperations;
//
//
//    @Autowired
//    private SimpUserRegistry userRegistry;
//
//
//    @GetMapping("/test/rabbimq/broadcast")
//    public ResponseEntity testRabbitMqBroadCast() throws Exception {
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                messageSendingOperations.convertAndSend("/topic/message", "success");
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 10, 1000);
//
//        return new ResponseEntity();
//    }
//
//    @GetMapping("/test/rabbimq/p2p")
//    public ResponseEntity testRabbitMqP2p() throws Exception {
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                messageSendingOperations.convertAndSendToUser("lalala", "/queue/message", "success");
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 10, 1000);
//        return new ResponseEntity();
//    }
//
//}
