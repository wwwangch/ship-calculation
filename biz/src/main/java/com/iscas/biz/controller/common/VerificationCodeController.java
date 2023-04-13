package com.iscas.biz.controller.common;

import com.iscas.biz.service.common.VerificationCodeService;
import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/8/17 20:49
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@RestController
@Tag(name = "验证码控制器-VerificationCodeController")
@Slf4j
@ConditionalOnProperty(havingValue = "true", value = "kaptcha.enabled")
@Validated
@RequestMapping("/verification/code")
public class VerificationCodeController extends BaseController {
    @Autowired
    private VerificationCodeService verificationCodeService;

    /**
     * 获取验证码
     */
    @Operation(summary = "[验证码]获取验证码", description = "create by:朱全文 2020-02-21")
    @Parameters(
            {
                    @Parameter(name = "key", description = "加密码", required = true)
            }
    )
    @GetMapping
    public void getKaptchaImage(@NotBlank(message = "加密码不能为空") String key) throws Exception {
        verificationCodeService.verificationCode(key);
    }

    /**
     * 校验验证码
     */
    @Operation(summary = "[验证码] 校验验证码", description = "create by:朱全文 2020-02-21")
    @Parameters(
            {
                    @Parameter(name = "code", description = "验证码", required = true),
                    @Parameter(name = "key", description = "加密码", required = true)
            }
    )
    @GetMapping("/verify")
    public ResponseEntity verify(@NotBlank(message = "验证码不能为空") String code, @NotBlank(message = "加密码不能为空") String key) throws Exception {
        return verificationCodeService.verify(code, key);
    }

}
