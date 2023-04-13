package com.iscas.base.biz.config.stomp;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.socket.sockjs.SockJsService;
import org.springframework.web.socket.sockjs.support.SockJsHttpRequestHandler;
import org.springframework.web.socket.sockjs.transport.handler.DefaultSockJsService;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 升级springboot到2.4.0后websocket出现跨域问题处理，重写MyWebSocketHandlerMapping
 * <p>不需要此处理了，过期了，即将删除</>
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/11/25 13:59
 * @since jdk1.8
 */
@Deprecated
public class MyWebSocketHandlerMapping extends SimpleUrlHandlerMapping implements SmartLifecycle {

    private volatile boolean running;


    @Override
    protected void initServletContext(@NotNull ServletContext servletContext) {
        for (Object handler : getUrlMap().values()) {
            if (handler instanceof ServletContextAware) {
                ((ServletContextAware) handler).setServletContext(servletContext);
            }
        }
    }


    @Override
    public void start() {
        if (!isRunning()) {
            this.running = true;
            for (Object handler : getUrlMap().values()) {
                if (handler instanceof Lifecycle) {
                    ((Lifecycle) handler).start();
                }
            }
        }
    }

    @Override
    public void stop() {
        if (isRunning()) {
            this.running = false;
            for (Object handler : getUrlMap().values()) {
                if (handler instanceof Lifecycle) {
                    ((Lifecycle) handler).stop();
                }
            }
        }
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public CorsConfiguration getCorsConfiguration(@NotNull Object handler, @NotNull HttpServletRequest request) {
        Object resolvedHandler = handler;
        if (handler instanceof HandlerExecutionChain) {
            resolvedHandler = ((HandlerExecutionChain) handler).getHandler();
        }
        if (resolvedHandler instanceof CorsConfigurationSource) {
            if (resolvedHandler instanceof SockJsHttpRequestHandler)  {
                String origin = request.getHeader("Origin");
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(new ArrayList<>(List.of(origin)));
                config.addAllowedMethod("*");
                config.setAllowCredentials(true);
                config.setMaxAge(365 * 24 * 3600L);
                config.addAllowedHeader("*");

                //修改sockJs中CorsConfiguration的origin add 2021-01-06
                SockJsHttpRequestHandler sockJsHttpRequestHandler = (SockJsHttpRequestHandler) resolvedHandler;
                SockJsService sockJsService = sockJsHttpRequestHandler.getSockJsService();
                try {
                    ((DefaultSockJsService) sockJsService).setAllowedOrigins(Objects.requireNonNull(config.getAllowedOrigins()));
                } catch (Exception e) {
                    logger.warn("设置SockJs中corsConfiguration的属性allowedOrigins的值出错", e);
                    throw new StompRegistryException("设置SockJs中corsConfiguration的属性allowedOrigins的值出错", e);
                }
                return config;
            } else {
                return ((CorsConfigurationSource) resolvedHandler).getCorsConfiguration(request);
            }
        }
        return null;
    }

    @SuppressWarnings({"CommentedOutCode", "AlibabaRemoveCommentedCode", "unused"})
    protected boolean checkOrigin(ServerHttpRequest request, ServerHttpResponse response, HttpMethod... httpMethods) {

        if (WebUtils.isSameOrigin(request)) {
            return true;
        }

//        if (this.corsConfiguration.checkOrigin(request.getHeaders().getOrigin()) == null) {
//            if (logger.isWarnEnabled()) {
//                logger.warn("Origin header value '" + request.getHeaders().getOrigin() + "' not allowed.");
//            }
//            response.setStatusCode(HttpStatus.FORBIDDEN);
//            return false;
//        }

        return true;
    }
}
