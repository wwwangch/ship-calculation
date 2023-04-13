//package com.iscas.biz.controller.common;
//
//import com.iscas.base.biz.autoconfigure.auth.TokenProps;
//import com.iscas.base.biz.config.cas.ConditionalOnCustomCasClient;
//import com.iscas.base.biz.util.SpringUtils;
//import com.iscas.biz.service.common.AuthServiceImpl;
//import com.iscas.templet.common.BaseController;
//import com.iscas.templet.common.ResponseEntity;
//import com.iscas.templet.exception.LoginException;
//import org.jasig.cas.client.util.AssertionHolder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2021/7/3 17:41
// * @since jdk1.8
// */
//@SuppressWarnings({"unused", "rawtypes"})
//@RestController
//@RequestMapping("/cas")
//@ConditionalOnCustomCasClient
//public class CasClientController extends BaseController {
//    @Autowired
//    private TokenProps tokenProps;
//
//    @Autowired
//    private AuthServiceImpl authService;
//    @GetMapping("/valid")
//    public ResponseEntity valid() throws LoginException {
//        //获取CAS登录的用户名
//        String username = AssertionHolder.getAssertion().getPrincipal().getName();
//        ResponseEntity response = getResponse();
//        authService.createToken(SpringUtils.getResponse(), -1, username, response, ((Long) tokenProps.getExpire().toMinutes()).intValue(), tokenProps.getCookieExpire(), "", "");
//        return response;
//    }
//}
