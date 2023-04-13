package com.iscas.base.biz.config.stomp;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author zhuquanwen
 * @date 2018/6/12 17:29
 **/
@SuppressWarnings("unused")
public class SessionAuthHandshakeInterceptor implements HandshakeInterceptor {
    private final Logger logger = LoggerFactory.getLogger(SessionAuthHandshakeInterceptor.class);

    @SuppressWarnings({"CommentedOutCode", "AlibabaRemoveCommentedCode"})
    @Override
    public boolean beforeHandshake(@NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response, @NotNull WebSocketHandler wsHandler, Map<String, Object> attributes) {
        HttpSession session = getSession(request);
//        if(session == null || session.getAttribute(Constants.SESSION_USER) == null){
//            logger.error("websocket权限拒绝");
////            return false;
//            throw new CmiException("websocket权限拒绝");
//        }
        attributes.put("name", session.getAttribute("name"));
        return true;
    }

    @Override
    public void afterHandshake(@NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response, @NotNull WebSocketHandler wsHandler, Exception exception) {

    }

    private HttpSession getSession(ServerHttpRequest request) {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
            return serverRequest.getServletRequest().getSession(false);
        }
        return null;
    }
}
