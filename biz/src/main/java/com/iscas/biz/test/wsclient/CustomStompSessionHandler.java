package com.iscas.biz.test.wsclient;

import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

public class CustomStompSessionHandler extends StompSessionHandlerAdapter {

	public CustomStompSessionHandler(){
	}

	@Override
	public void afterConnected(final StompSession session, StompHeaders connectedHeaders) {
		System.out.println("StompHeaders: " + connectedHeaders.toString());
		//订阅地址，发送端前面没有/user
		String destination = "/user/queue/message";
		//订阅消息
		session.subscribe(destination, new StompFrameHandler() {
			@Override
			public Type getPayloadType(StompHeaders headers) {
				return byte[].class;
			}
			@Override
			public void handleFrame(StompHeaders headers, Object payload) {
				//todo 只能接收到byte[]数组，没时间研究原因
				System.out.println(new String((byte[])payload));
			}
		}); 
	}

	@Override
	public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload,
								Throwable exception) {
		    System.out.println(exception.getMessage());
	}

	@Override
	public void handleTransportError(StompSession session, Throwable exception) {
		exception.printStackTrace();
		System.out.println("transport error.");
	}
}