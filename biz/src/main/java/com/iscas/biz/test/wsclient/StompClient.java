package com.iscas.biz.test.wsclient;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class StompClient {
	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(1);
		List<Transport> transports = new ArrayList<>(1);
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));
		SockJsClient transport = new SockJsClient(transports);
		transport.setMessageCodec(new Jackson2SockJsMessageCodec());
		WebSocketStompClient stompClient = new WebSocketStompClient(transport);
		//接收大小限制
		stompClient.setInboundMessageSizeLimit(1024 * 1024);
		//处理心跳
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.afterPropertiesSet();
		//for heartbeats
		stompClient.setTaskScheduler(taskScheduler);
		StompSessionHandler customHandler = new CustomStompSessionHandler();
		//可以发送请求头
		StompHeaders stompHeaders = new StompHeaders();
		stompHeaders.add("Authorization", "admin");
		URI uri = URI.create("http://192.168.100.88:14545/demo/webSocketServer");
		stompClient.connect(uri, null, stompHeaders, customHandler);
		//阻塞
		try {
			latch.await(31536000, TimeUnit.SECONDS);
			//latch.await(3, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}