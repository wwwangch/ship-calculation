//package com.iscas.biz.service;
//
//import com.iscas.base.biz.service.common.SendEmailService;
//import com.iscas.biz.BizApp;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.thymeleaf.context.Context;
//
//import javax.mail.MessagingException;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * 邮件发送测试
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/8/12 22:05
// * @since jdk1.8
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BizApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration
//public class SendEmailServiceTests {
//    @Autowired
//    private SendEmailService sendEmailService;
//    /**
//     * 测试发送普通文本邮件
//     * */
//    @Test
////    @Ignore
//    public void testText() {
//        sendEmailService.sendText("461402005@qq.com", "76775081@qq.com", "这是文本测试", "这是测试正文");
//    }
//
//    /**
//     * 测试发送HTML邮件
//     * */
//    @Test
////    @Ignore
//    public void testHtml() throws MessagingException {
//        String html = "<html><body><h1>这是测试测试</h1></body></html>";
//        sendEmailService.sendHtml("461402005@qq.com", "76775081@qq.com", "这是html测试", html);
//    }
//
//    /**
//     * 测试发送附件
//     * */
//    @Test
////    @Ignore
//    public void testAttachment() throws MessagingException, IOException {
//        InputStream is = new FileInputStream("E:\\test\\repo1\\a.txt");
//        String html = "<html><body><h1>这是测试测试</h1></body></html>";
//        sendEmailService.sendAttachment("461402005@qq.com", "76775081@qq.com", "这是html测试", html, is, "测试.txt");
//
//    }
//
//    /**
//     * 测试发送thymeleaf模板邮件
//     * */
//    @Test
////    @Ignore
//    public void testTemplate() throws MessagingException {
//        //获取模板生成html
//        Context context = new Context();
//        // 这里的id与resources/templates下的模板文件中的${userid}必须对应
//        context.setVariable("userid", 1);
//        // 这里的"emailTemplate"与resources/templates下的模板文件一直
//        sendEmailService.sendTemplate("461402005@qq.com", "76775081@qq.com", "这是模板测试", "emailTemplate", context);
//
//    }
//
//    /**
//     * 测试发送thymeleaf模板邮件,并携带附件
//     * */
//    @Test
////    @Ignore
//    public void testTemplateWithAttachment() throws MessagingException, IOException {
//        //获取模板生成html
//        Context context = new Context();
//        // 这里的id与resources/templates下的模板文件中的${userid}必须对应
//        context.setVariable("userid", 1);
//        // 这里的"emailTemplate"与resources/templates下的模板文件一致
//        InputStream is = new FileInputStream("E:\\test\\repo1\\a.txt");
//        sendEmailService.sendTemplateWithAttachment("461402005@qq.com", "76775081@qq.com", "这是模板测试", "emailTemplate", context, is, "测试测试测试.txt");
//
//    }
//}
