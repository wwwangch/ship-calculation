//package com.iscas.base.biz.service.common;
//
//import cn.hutool.core.io.IoUtil;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//
///**
// * 发送邮件工具
// *
// * @author zhuquanwen
// * @version 1.0
// * @date 2020/8/12 21:52
// * @since jdk1.8
// */
//@SuppressWarnings("unused")
//@Service
//@Slf4j
//@ConditionalOnBean(value = {JavaMailSender.class, TemplateEngine.class})
//public class SendEmailService {
//    @Autowired
//    private JavaMailSender javaMailSender;
//    @Autowired
//    private TemplateEngine templateEngine;
//
//    /**
//     * 发送普通文本邮件
//     *
//     * @since jdk1.8
//     * @date 2020/8/12
//     * @param from 发送邮件地址
//     * @param to 接收邮件地址
//     * @param title 邮件主题
//     * @param content 邮件正文文本
//     * */
//    public void sendText(String from, String to, String title, String content) {
//
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        // 发件人地址
//        message.setFrom(from);
//        // 收件人地址
//        message.setTo(to);
//        // 邮件标题
//        message.setSubject(title);
//        // 邮件正文
//        message.setText(content);
//
//        javaMailSender.send(message);
//        log.debug("邮件发送成功！");
//    }
//
//    /**
//     * 发送HTML邮件
//     * @since jdk1.8
//     * @date 2020/8/12
//     * @param from 发送邮件地址
//     * @param to 接收邮件地址
//     * @param title 邮件主题
//     * @param html 邮件正文html
//     * */
//    public void sendHtml(String from, String to, String title, String html) throws MessagingException {
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        // 这里与发送文本邮件有所不同
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        // 发件人地址
//        helper.setFrom(from);
//        // 收件人地址
//        helper.setTo(to);
//        helper.setSubject(title);
//        // 发送HTML邮件
//        helper.setText(html, true);
//
//        javaMailSender.send(message);
//        log.debug("邮件发送成功");
//
//    }
//
//    /**
//     * 发送附件
//     *
//     *
//     * @since jdk1.8
//     * @date 2020/8/12
//     * @param from 发送邮件地址
//     * @param to 接收邮件地址
//     * @param title 邮件主题
//     * @param html 邮件正文html
//     * @param inputStream 附件输入流
//     * @param fileName 文件名称
//     *
//     * */
//    public void sendAttachment(String from, String to, String title, String html, InputStream inputStream, String fileName) throws MessagingException {
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        // 这里与发送文本邮件有所不同
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        // 发件人地址
//        helper.setFrom(from);
//        // 收件人地址
//        helper.setTo(to);
//        helper.setSubject(title);
//        // 发送HTML
//        helper.setText(html, true);
//
//        //发送附件
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        IoUtil.copy(inputStream, baos);
//
//        ByteArrayResource byteArrayResource = new ByteArrayResource(baos.toByteArray());
//        // 发送文件名
//        helper.addAttachment(fileName, byteArrayResource);
//
//        javaMailSender.send(message);
//        log.debug("发送成功");
//
//    }
//
//    /**
//     * 测试发送thymeleaf模板邮件
//     * templateName必须在resources/templates下
//     *
//     * @since jdk1.8
//     * @date 2020/8/12
//     * @param from 发送邮件地址
//     * @param to 接收邮件地址
//     * @param title 邮件主题
//     * @param templateName 模板名称，templateName必须在resources/templates下
//     * @param context 构建模板的上下文，构建方式参见单元测试
//     * */
//    @GetMapping("/template")
//    public void sendTemplate(String from, String to, String title, String templateName, Context context) throws MessagingException {
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        // 这里与发送文本邮件有所不同
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        // 发件人地址
//        helper.setFrom(from);
//        // 收件人地址
//        helper.setTo(to);
//        helper.setSubject(title);
//
//        //获取模板生成html
//        String html = templateEngine.process(templateName, context);
//        helper.setText(html, true);
//
//        javaMailSender.send(message);
//        log.debug("邮件发送成功");
//
//    }
//
//    /**
//     * 测试发送thymeleaf模板邮件,并携带附件
//     * templateName必须在resources/templates下
//     * @since jdk1.8
//     * @date 2020/8/12
//     * @param from 发送邮件地址
//     * @param to 接收邮件地址
//     * @param title 邮件主题
//     * @param templateName 模板名称，templateName必须在resources/templates下
//     * @param context 构建模板的上下文，构建方式参见单元测试
//     * @param inputStream 附件输入流
//     * @param fileName 文件名称
//     * */
//    public void sendTemplateWithAttachment(String from, String to, String title, String templateName, Context context, InputStream inputStream, String fileName) throws MessagingException {
//        //获取模板生成html
//        String html = templateEngine.process(templateName, context);
//        sendAttachment(from, to, title, html, inputStream, fileName);
//    }
//}
