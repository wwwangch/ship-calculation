package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.util.SpringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/1 19:59
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/hmc")
public class HandlerMappingController {
    @GetMapping
    public String test() {

        RequestMappingHandlerMapping mapping = SpringUtils.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            HandlerMethod handlerMethod = handlerMethods.get(rmi);
            System.out.println(handlerMethod);
//            if (handlerMethod.hasMethodAnnotation(NoLogin.class)) {
//                PatternsRequestCondition prc = rmi.getPatternsCondition();
//                Set<String> patterns = prc.getPatterns();
//                noLoginUrlSet.addAll(patterns);
//            }
        }
        return "success";
    }

    @RequestMapping(value = {"/AAA", "/BBB"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String aaa() {
        return "success";
    }
}
