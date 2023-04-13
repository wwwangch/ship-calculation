package com.iscas.common.tools.captcha;

import lombok.Cleanup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码生成工具类测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/21 9:29
 * @since jdk1.8
 */
public class CaptchaTests {
    @Test
    public void test() throws IOException {
        System.out.println("-------CaptchaUtils生成验证码begin---------");
        File file = File.createTempFile("captcha", ".png");
        file.deleteOnExit();
        System.out.println(file.getAbsolutePath());
        @Cleanup OutputStream os = new FileOutputStream(file);
        String captcha = CaptchaUtils.createCaptcha(os);
        System.out.println(captcha);
        Assertions.assertNotNull(captcha);
        os.close();
        file.delete();
        System.out.println("-------CaptchaUtils生成验证码end---------");
    }
}
