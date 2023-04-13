//package com.iscas.biz.test.controller;
//
//import com.iscas.templet.common.BaseController;
//import com.iscas.templet.common.ResponseEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//
///**
// * 邮件发送测试
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/8/12 21:16
// * @since jdk1.8
// */
//@RestController
//@RequestMapping("/send/email/test")
//@Slf4j
//public class SendEmailController extends BaseController {
//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Autowired
//    private TemplateEngine templateEngine;
//
//    /**
//     * 测试发送普通文本邮件
//     * */
//    @GetMapping("/text")
//    public ResponseEntity testText() {
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        // 发件人地址
//        message.setFrom("461402005@qq.com");
//        // 收件人地址
//        message.setTo("76775081@qq.com");
//        // 邮件标题
//        message.setSubject("这是一个测试");
//        // 邮件正文
//        message.setText("这是测试正文");
//
//        javaMailSender.send(message);
//        log.debug("发送成功！");
//
//        return getResponse();
//    }
//
//    /**
//     * 测试发送HTML邮件
//     * */
//    @GetMapping("/html")
//    public ResponseEntity testHtml() throws MessagingException {
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        // 这里与发送文本邮件有所不同
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        // 发件人地址
//        helper.setFrom("461402005@qq.com");
//        // 收件人地址
//        helper.setTo("76775081@qq.com");
//        helper.setSubject("这是html测试");
//        // 发送HTML邮件
//        String html = "<html><body><h1>这是测试测试</h1></body></html>";
//        helper.setText(html, true);
//
//        javaMailSender.send(message);
//        log.debug("发送成功");
//
//        return getResponse();
//    }
//
//    /**
//     * 测试发送附件
//     * */
//    @GetMapping("/attachment")
//    public ResponseEntity testAttachment() throws MessagingException {
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        // 这里与发送文本邮件有所不同
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        // 发件人地址
//        helper.setFrom("461402005@qq.com");
//        // 收件人地址
//        helper.setTo("76775081@qq.com");
//        helper.setSubject("这是附件测试");
//        // 发送HTML
//        String html = "<html><body><h1>这是测试测试</h1></body></html>";
//        helper.setText(html, true);
//
//        //发送附件
//        FileSystemResource file = new FileSystemResource("E:/test/repo1/a.txt");
//        // 发送文件名
//        String fileName = file.getFilename();
//        helper.addAttachment(fileName, file);
//
//        javaMailSender.send(message);
//        log.debug("发送成功");
//
//        return getResponse();
//    }
//
//    /**
//     * 测试发送thymeleaf模板邮件
//     * */
//    @GetMapping("/template")
//    public ResponseEntity testTemplate() throws MessagingException {
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        // 这里与发送文本邮件有所不同
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        // 发件人地址
//        helper.setFrom("461402005@qq.com");
//        // 收件人地址
//        helper.setTo("76775081@qq.com");
//        helper.setSubject("这是模板测试");
//
//        //获取模板生成html
//        Context context = new Context();
//        // 这里的id与resources/templates下的模板文件中的${userid}必须对应
//        context.setVariable("userid", 1);
//        // 这里的"emailTemplate"与resources/templates下的模板文件一直
//        String html = templateEngine.process("emailTemplate", context);
//        helper.setText(html, true);
//
//        //发送附件
//        FileSystemResource file = new FileSystemResource("E:/test/repo1/a.txt");
//        // 发送文件名
//        String fileName = file.getFilename();
//        helper.addAttachment(fileName, file);
//
//        javaMailSender.send(message);
//        log.debug("发送成功");
//
//        return getResponse();
//    }
//}
