//package com.iscas.biz.test.controller;
//
//import com.iscas.common.ssh.tools.SshClientUtils;
//import com.iscas.common.ssh.tools.model.SshClientDto;
//import com.iscas.templet.common.ResponseEntity;
//import com.jcraft.jsch.JSchException;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/12/9 18:05
// * @since jdk1.8
// */
//@RestController
//public class TestSshController {
//    Map<String, SshClientDto> storeMap = new ConcurrentHashMap<>();
//    ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//    @GetMapping("/test/ssh")
//    public ResponseEntity createSession() throws IOException, JSchException {
//        ResponseEntity<Object> res = new ResponseEntity<>();
//        SshClientDto sshClientDto = SshClientUtils.openSshSession("192.168.100.96", 22, "root", "root", 6000);
//        //按照一个标记把客户端存起来
//        storeMap.put("xxxx", sshClientDto);
//        executorService.submit(() -> {
//            String line = null;
//            while (true) {
//                try {
//                    line = sshClientDto.getBr().readLine();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(line);
//                //这里给前端推送
//            }
//        });
//        return res;
//    }
//
//    @GetMapping("/test/ssh/input")
//    public ResponseEntity input() {
//        ResponseEntity<Object> res = new ResponseEntity<>();
//        //按照标记把这个客户端信息拿出来
//        SshClientDto sshClientDto = storeMap.get("xxxx");
//        PrintWriter pw = sshClientDto.getPw();
//        pw.println("ls");
//        pw.flush();
//        return res;
//    }
//
//}
