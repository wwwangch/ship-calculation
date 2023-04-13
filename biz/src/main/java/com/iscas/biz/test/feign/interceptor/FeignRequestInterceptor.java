package com.iscas.biz.test.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Collection;
import java.util.Map;

/**
 * feign的请求拦截器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/8/31 15:28
 * @since jdk1.8
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        Map<String, Collection<String>> headers = template.headers();
        System.out.println(headers);
        //增加一个header
        template.header("custom-header", "lalala");
        //增加一个参数
        template.query("add-parame", "heiheihei");
    }
}
