package com.iscas.base.biz.controller.common;

import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.config.StaticInfo;
import com.iscas.base.biz.autoconfigure.cors.CorsProps;
import com.iscas.base.biz.util.AccessLogUtils;
import com.iscas.base.biz.util.AuthContextHolder;
import com.iscas.base.biz.util.CorsUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.common.tools.assertion.AssertRuntimeException;
import com.iscas.common.tools.core.random.RandomStringUtils;
import com.iscas.common.tools.exception.ExceptionUtils;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings({"unused", "rawtypes", "unchecked"})
@RestControllerAdvice
@Component
@Slf4j
public class ExceptionAdivisor implements Constants, com.iscas.common.tools.constant.HttpStatus {
    @Autowired
    private CorsProps corsProps;
    @Value("${exception-stack-trace-max-size:500}")
    private int exceptionStackTraceMaxSize;


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity to400(MethodArgumentNotValidException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _400);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        String errorMsg = errors.stream().map(error -> StringUtils.isBlank(error.getDefaultMessage()) ?
                        error.getField() + "校验失败" : error.getDefaultMessage())
                .collect(Collectors.joining(";", REQUEST_PARAM_VALID_ERROR_PREFIX, ""));
        return res(HttpStatus.BAD_REQUEST.value(), errorMsg, e).setDesc(errorMsg);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity to400(ConstraintViolationException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _400);
        Set<ConstraintViolation<?>> cvs = e.getConstraintViolations();
        if (cvs != null) {
            String errorMsg = cvs.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(";", REQUEST_PARAM_VALID_ERROR_PREFIX, ""));
            return res(HttpStatus.BAD_REQUEST.value(), errorMsg, e).setDesc(errorMsg);
        }
        return res(HttpStatus.BAD_REQUEST.value(), REQUEST_PARAM_VALID_ERROR_PREFIX, e);
    }

    //将ValidDataException的HTTP状态码改为400
    @ExceptionHandler(value = ValidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity to400(ValidDataException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _400);
        return res(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e);
    }

    @ExceptionHandler(value = LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity loginException(LoginException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _401);
        HttpSession session = SpringUtils.getSession();
        String data = RandomStringUtils.randomStr(16);
        session.setAttribute(SESSION_LOGIN_KEY, data);
        ResponseEntity res = res(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), e);
        res.setValue(data);
        return res;
    }

    @ExceptionHandler(value = AuthenticationRuntimeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity loginRuntimeException(AuthenticationRuntimeException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _401);
        HttpSession session = SpringUtils.getSession();
        String data = RandomStringUtils.randomStr(16);
        session.setAttribute(SESSION_LOGIN_KEY, data);
        ResponseEntity res = res(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), e);
        res.setValue(data);
        return res;
    }

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity to403Exception(AuthorizationException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _403);
        return res(HttpStatus.FORBIDDEN.value(), e.getMessage(), e);
    }

    @ExceptionHandler(value = AuthorizationRuntimeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity to403Exception(AuthorizationRuntimeException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _403);
        return res(HttpStatus.FORBIDDEN.value(), e.getMessage(), e);
    }

    @ExceptionHandler(value = RequestTimeoutRuntimeException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ResponseEntity to408(BaseRuntimeException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _408);
        return res(HttpStatus.REQUEST_TIMEOUT.value(), e.getMessage(), e);
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity to500(BaseException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _500);
        if (e.getCause() instanceof BaseException || e.getCause() instanceof BaseRuntimeException) {
            // 防止baseException中套baseException的情况，检测一次
            return res(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e.getCause());
        }
        return res(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e);
    }

    @ExceptionHandler(value = BaseRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity to500(BaseRuntimeException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _500);
        return res(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e);
    }

    @ExceptionHandler(value = AssertRuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity to500(AssertRuntimeException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _500);
        return res(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), e);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity to500(MaxUploadSizeExceededException e) {
        AccessLogUtils.log(SpringUtils.getRequest(), _500);
        return res(HttpStatus.INTERNAL_SERVER_ERROR.value(), "上传文件大小超过限制", e);
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity to500(Throwable throwable) {
        AccessLogUtils.log(SpringUtils.getRequest(), _500);
        return res(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误", throwable);
    }

    /**
     * 生成异常
     */
    public ResponseEntity res(int status, String msg, Throwable e) {
        log.error("异常", e);
        ResponseEntity responseEntity = new ResponseEntity(status, msg)
                .setDesc(getDesc(e))
                .setStackTrace(ExceptionUtils.getExceptionStackTrace(e, exceptionStackTraceMaxSize));
        setResponseCros();
        setResponseInfo(responseEntity);
        AuthContextHolder.removeContext();
        return responseEntity;
    }

    private String getDesc(Throwable e) {
        if (e instanceof BaseException) {
            BaseException ex = (BaseException) e;
            return ex.getMsgDetail() != null ? ex.getMsgDetail() : getMessage(e);
        } if (e instanceof BaseRuntimeException) {
            BaseRuntimeException ex = (BaseRuntimeException) e;
            return ex.getMsgDetail() != null ? ex.getMsgDetail() : getMessage(e);
        }
        return getMessage(e);
    }

    /**
     * 设置跨域
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    private void setResponseCros() {
        HttpServletRequest request = SpringUtils.getRequest();
        HttpServletResponse response = SpringUtils.getResponse();
        if (response.getHeader(ACCESS_CONTROL_ALLOW_ORIGIN) == null) {
            String origin;
            try {
                origin = CorsUtils.checkOrigin(request, response, corsProps);
            } catch (IOException e) {
//                origin = corsProps.getOrigin();
                origin = corsProps.getOriginPattern();
            }
            response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        }
        if (response.getHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS) == null) {
            //服务器同意客户端发送cookies
            response.setHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS, corsProps.getCredentials());
        }
        if (response.getHeader(ACCESS_CONTROL_ALLOW_METHODS) == null) {
            response.setHeader(ACCESS_CONTROL_ALLOW_METHODS, corsProps.getMethods());
        }
        if (response.getHeader(ACCESS_CONTROL_ALLOW_HEADERS) == null) {
            response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, corsProps.getHeaders());
        }
    }


    /**
     * 递归获取异常的message
     */
    private String getMessage(Throwable throwable) {
        String message = null;
        if (throwable != null) {
            String message1 = throwable.getMessage();
            if (message1 != null) {
                return message1;
            } else {
                Throwable cause = throwable.getCause();
                return getMessage(cause);
            }
        }
        return null;
    }

    /**
     * 设置耗时等信息
     */
    private void setResponseInfo(ResponseEntity responseEntity) {
        HttpServletRequest request = SpringUtils.getRequest();
        Long start;
        try {
            start = StaticInfo.START_TIME_THREAD_LOCAL.get();
        } finally {
            StaticInfo.START_TIME_THREAD_LOCAL.remove();
        }
        if (Objects.nonNull(start)) {
            responseEntity.setTookInMillis(System.currentTimeMillis() - start);
        }
        if (request != null) {
            String requestUri = request.getRequestURI();
            responseEntity.setRequestURL(requestUri);
        }
    }

}
