//package com.iscas.base.biz.test.controller;
//
//import com.iscas.templet.common.ResponseEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.messaging.simp.user.SimpUser;
//import org.springframework.messaging.simp.user.SimpUserRegistry;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.Principal;
//
///**
// * 如有要看例子，请打开注释
// *
// **/
//@RestController
//public class WebSoketDemoController {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    //spring提供的发送消息模板
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//
//    @Autowired
//    private SimpUserRegistry userRegistry;
//
//
//
//
//    /*点对点通信*/
//    @MessageMapping(value = "/P2P")
//    public void templateTest(Principal principal) {
//        logger.info("当前在线人数:" + userRegistry.getUserCount());
//        int i = 1;
//        for (SimpUser user : userRegistry.getUsers()) {
//            logger.info("用户" + i++ + "---" + user);
//        }
//        //发送消息给指定用户
//        messagingTemplate.convertAndSend("/topic/message", new Object());
//        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/message","服务器主动推的数据");
//    }
//
//    /*广播*/
//    @MessageMapping("/broadcast")
//    @SendTo("/topic/getResponse")
//    public ResponseEntity topic() throws Exception {
//        return new ResponseEntity(200,"success");
//    }
//
//}
