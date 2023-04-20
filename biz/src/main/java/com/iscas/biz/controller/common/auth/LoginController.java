package com.iscas.biz.controller.common.auth;


import com.iscas.base.biz.aop.auth.SkipAuthentication;
import com.iscas.base.biz.autoconfigure.auth.TokenProps;
import com.iscas.base.biz.config.Constants;
import com.iscas.base.biz.service.AbstractAuthService;
import com.iscas.base.biz.service.common.AuthCacheService;
import com.iscas.base.biz.util.CacheUtils;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import com.iscas.biz.validator.anno.LoginConstraint;
import com.iscas.common.tools.core.random.RandomStringUtils;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * 登陆控制器
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/16 22:38
 * @since jdk1.8
 */
@SuppressWarnings({"rawtypes", "unused", "unchecked"})
@RestController
@Tag(name = "登陆控制器-LoginController")
@SkipAuthentication
@Validated
@ConditionalOnMybatis
@RequiredArgsConstructor
public class LoginController extends BaseController implements Constants {


    private final TokenProps tokenProps;

    private final AbstractAuthService authService;

    private final AuthCacheService authCacheService;


    @Operation(summary = "[登录控制器]用户登出", description = "create by:朱全文 2020-02-21")
    @GetMapping(value = "/logout", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity logout(HttpServletRequest request) {
        ResponseEntity responseEntity = new ResponseEntity(200, "注销成功");
        authService.invalidToken(request);
        responseEntity.setValue("注销成功");
        return responseEntity;
    }

    @Operation(summary = "[登录控制器]登录前置", description = "create by:朱全文 2020-02-21")
    @GetMapping(value = "/prelogin", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map> preLogin() {
        ResponseEntity<Map> responseEntity = new ResponseEntity<>();
        String secretKey = RandomStringUtils.randomStr(16);
        String uuid = UUID.randomUUID().toString();
        // 放入缓存
        CacheUtils.putCache(Constants.CAPTCHA_CACHE, uuid, secretKey);
        responseEntity.setValue(Map.of("key", uuid, "encryKey", secretKey));
        return responseEntity;
    }

    @Operation(summary = "[登录控制器]登录", description = "create by:朱全文 2020-02-21")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "用户名密码等信息",
            content = @Content(examples = @ExampleObject(value = "{\"username\":\"zhangsan\", \"password\":\"iscas123\", \"key\":\"dewetwet\"}")))
    @PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity login(HttpServletResponse response, @RequestBody @LoginConstraint Map<String, String> user) throws Exception {
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setMessage("登录成功");
        authService.loginHandler(response, user, responseEntity,
                ((Long) tokenProps.getExpire().toMinutes()).intValue(), tokenProps.getCookieExpire());
        return responseEntity;
    }
}
