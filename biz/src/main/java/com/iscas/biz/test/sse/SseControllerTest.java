package com.iscas.biz.test.sse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  测试SSE推送消息
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/11 21:01
 * @since jdk1.8
 */
@RestController
@RequestMapping(path = "sse/test")
public class SseControllerTest {
    private static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();
//    @GetMapping(path = "subscribe")
//    public SseEmitter push(String id) {
//        // 超时时间设置为1小时
//        SseEmitter sseEmitter = new SseEmitter(3600000L);
//        sseCache.put(id, sseEmitter);
//        sseEmitter.onTimeout(() -> sseCache.remove(id));
//        sseEmitter.onCompletion(() -> System.out.println("完成！！！"));
//        return sseEmitter;
//    }

    @GetMapping(path = "subscribe", produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter push(String id) throws IOException {
        // 超时时间设置为3s，用于演示客户端自动重连
        SseEmitter sseEmitter = new SseEmitter(30000L);
        // 设置前端的重试时间为1s
        sseEmitter.send(SseEmitter.event().reconnectTime(1000).data("连接成功"));
        sseCache.put(id, sseEmitter);
        System.out.println("add " + id);
        sseEmitter.onTimeout(() -> {
            System.out.println(id + "超时");
            sseCache.remove(id);
        });
        sseEmitter.onCompletion(() -> System.out.println("完成！！！"));
        return sseEmitter;
    }

    @GetMapping(path = "push")
    public String push(String id, String content) throws IOException {
        SseEmitter sseEmitter = sseCache.get(id);
        if (sseEmitter != null) {
            sseEmitter.send(SseEmitter.event().name("msg").data("后端发送消息：" + content));
        }
        return "over";
    }

    @GetMapping(path = "over")
    public String over(String id) {
        SseEmitter sseEmitter = sseCache.get(id);
        if (sseEmitter != null) {
            sseEmitter.complete();
            sseCache.remove(id);
        }
        return "over";
    }
}
