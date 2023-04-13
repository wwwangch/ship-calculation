package com.iscas.base.biz.controller.common;

import com.iscas.common.tools.core.runtime.RuntimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/4 10:34
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Controller
public class IndexController {
    @Value("${welcome.page.info}")
    private String welcomePageInfo;

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return welcomePageInfo + "，PID:" + RuntimeUtils.getCurrentPid();
    }

    @GetMapping("/help")
    public String help() {
        return "index";
    }
}
