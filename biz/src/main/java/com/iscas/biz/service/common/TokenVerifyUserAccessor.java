package com.iscas.biz.service.common;

import com.auth0.jwt.interfaces.Claim;
import com.iscas.base.biz.autoconfigure.auth.TokenProps;
import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.config.stomp.UserAccessor;
import com.iscas.base.biz.model.auth.User;
import com.iscas.base.biz.util.JWTUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.templet.exception.Exceptions;
import com.iscas.templet.exception.ValidTokenException;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

/**
 * 校验token
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/26 9:26
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
public class TokenVerifyUserAccessor implements UserAccessor {
    private static final String SSH = "ssh:";

    @Override
    public void accessor(Message<?> message, StompHeaderAccessor accessor) {
        Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
        if (raw instanceof Map rawMap) {
            //这里就是token
            Object name = rawMap.get(Constants.TOKEN_KEY);
            if (name instanceof List list) {
                // 设置当前访问器的认证用户
                String token = list.get(0).toString();
                //todo 暂时先这样通过前缀判断是否为ssh的websocket//todo 暂时先这样通过前缀判断是否为ssh的websocket
                if (token != null && token.startsWith(SSH)) {
                    User user = new User();
                    user.setUsername(token);
                    accessor.setUser(user);
                } else {
                    String username;
                    try {
                        TokenProps tokenProps = SpringUtils.getBean(TokenProps.class);
                        Map<String, Claim> claimMap = JWTUtils.verifyToken(token, tokenProps.getCreatorMode());
                        username = claimMap.get("username").asString();
                        if (username == null) {
                            throw Exceptions.runtimeException("websocket认证失败");
                        }
                    } catch (ValidTokenException | IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                        e.printStackTrace();
                        throw Exceptions.runtimeException("websocket认证失败", e);
                    }
                    User user = new User();
                    user.setUsername(username);
                    accessor.setUser(user);
                }
            }
        }
    }
}
