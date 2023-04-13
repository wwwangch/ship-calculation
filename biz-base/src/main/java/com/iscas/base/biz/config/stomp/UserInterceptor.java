package com.iscas.base.biz.config.stomp;

import com.iscas.base.biz.util.SpringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 *用户拦截器
 *
 * @author admin*/
public class UserInterceptor implements ChannelInterceptor {

  private volatile UserAccessor userAccessor = null;

    @SuppressWarnings({"AlibabaRemoveCommentedCode", "StatementWithEmptyBody"})
    @Override
    public Message<?> preSend(@NotNull Message<?> message, @NotNull MessageChannel channel) {
        if (userAccessor == null) {
            synchronized (UserInterceptor.class) {
                if (userAccessor == null) {
                    userAccessor = SpringUtils.getApplicationContext().getBean(UserAccessor.class);
                }
            }
        }
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        assert accessor != null;
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            userAccessor.accessor(message,accessor);
//            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
//            if (raw instanceof Map) {
//                //这里就是token
//                Object name = ((Map) raw).get(Constants.TOKEN_KEY);
//                if (name instanceof List) {
//                    // 设置当前访问器的认证用户
////                    String token = ((List) name).get(0).toString();
////                    String username = null;
////                    try {
////                        Map<String, Claim> claimMap = JWTUtils.verifyToken(token);
////                        username = claimMap.get("username").asString();
////                        if(username == null){
////                            throw new RuntimeException("websocket认证失败");
////                        }
////                    } catch (UnsupportedEncodingException e) {
////                        e.printStackTrace();
////                        throw new RuntimeException("websocket认证失败", e);
////                    } catch (ValidTokenException e) {
////                        e.printStackTrace();
////                        throw new RuntimeException("websocket认证失败", e);
////                    }
////                    User user = new User();
////                    user.setUsername(username);
////                    accessor.setUser(user);
//
//                    User user = new User();
//                    user.setUsername(((List) name).get(0).toString());
//                    accessor.setUser(user);
//
////                }
//            }
        } else if (StompCommand.SEND.equals(accessor.getCommand())) {
            //发送数据
//            long[] heartbeat = accessor.setH

        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {

        } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {

        }

        return message;
    }

    @Override
    public void postSend(@NotNull Message<?> message, @NotNull MessageChannel channel, boolean sent) {

    }

    @Override
    public void afterSendCompletion(@NotNull Message<?> message, @NotNull MessageChannel channel, boolean sent, Exception ex) {

    }

    @Override
    public boolean preReceive(@NotNull MessageChannel channel) {
        return false;
    }

    @Override
    public Message<?> postReceive(@NotNull Message<?> message, @NotNull MessageChannel channel) {
        return null;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, @NotNull MessageChannel channel, Exception ex) {

    }
}
