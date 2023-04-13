package com.iscas.base.biz.service.common;

import com.iscas.common.tools.core.string.StringRaiseUtils;
import com.iscas.templet.common.ResponseEntity;
import com.iscas.templet.exception.BaseRuntimeException;
import com.iscas.templet.exception.RequestTimeoutRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * 处理DeferredResult
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/9 21:29
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
@Service
public class DeferredResultService {
    private final Map<String, Consumer<ResponseEntity>> deferredResultMap = new ConcurrentHashMap<>();

    /**
     * 将请求标记与DeffredResult#setResult映射
     */
    public void process(String requestMark, DeferredResult<ResponseEntity> deferredResult) {
        //判断此requestMark对应的任务是否存在
        Optional.of(deferredResultMap)
                .filter(t -> !t.containsKey(requestMark))
                .orElseThrow(() -> new BaseRuntimeException(StringRaiseUtils.format("requestMark：{}对应的任务已存在", requestMark)));

        // 请求超时的回调函数
        deferredResult.onTimeout(() -> {
            //从等待处理的请求Map中移除
            deferredResultMap.remove(requestMark);
            deferredResult.setErrorResult(new RequestTimeoutRuntimeException("请求超时"));
        });

        //将setResult的消费者存入map
        deferredResultMap.putIfAbsent(requestMark, deferredResult::setResult);
    }


    /**
     * 设置处理结果
     */
    public void setResult(String requestMark, ResponseEntity responseEntity) {
        //如果deferredResultMap中存在这个requestMark，移除得到DeffredResult 并调用其setResult方法
        Optional.ofNullable(deferredResultMap.remove(requestMark))
                .ifPresent(c -> c.accept(responseEntity));
    }
}
