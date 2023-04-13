package com.iscas.biz.config;

import com.iscas.biz.annotation.response.NoneResponseEntity;
import com.iscas.common.web.tools.json.JsonUtils;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Optional;

import static com.iscas.base.biz.config.StaticInfo.START_TIME_THREAD_LOCAL;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2023/2/2 8:32
 */
@RestControllerAdvice(basePackages = "com.iscas")
public class ResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 如果返回值不是ResponseEntity 或者 有@NoneResponseEntity注解在方法或类上。
        boolean notResponseEntity = !ResponseEntity.class.isAssignableFrom(returnType.getParameterType());
        boolean annotationPresent = returnType.hasMethodAnnotation(NoneResponseEntity.class) ||
                returnType.getMethod().getDeclaringClass().isAnnotationPresent(NoneResponseEntity.class);
        boolean res = false;
        if (annotationPresent) {
            res = false;
        } else if (notResponseEntity) {
            res = true;
        }
        if (!res) {
            removeStartThreadLocal();
        }
        return res;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            Class<?> parameterType = returnType.getParameterType();
            if (parameterType == Void.class || parameterType == void.class) {
                // 无返回值的接口直接返回，不做处理
                return body;
            }
            ResponseEntity res = ResponseEntity.ok(body);
            // 耗时
            Optional.ofNullable(START_TIME_THREAD_LOCAL.get()).ifPresent(start -> res.setTookInMillis(System.currentTimeMillis() - start));
            // 请求URL
            res.setRequestURL(request.getURI().toString());
            if (body instanceof String) {
                // 设置响应头
                response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                return JsonUtils.toJson(res);
            }
            return res;
        } finally {
            removeStartThreadLocal();
        }
    }

    private void removeStartThreadLocal() {
        START_TIME_THREAD_LOCAL.remove();
    }

}
