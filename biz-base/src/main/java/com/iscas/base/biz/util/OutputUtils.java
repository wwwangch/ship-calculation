package com.iscas.base.biz.util;

import com.google.gson.Gson;
import com.iscas.templet.common.ResponseEntity;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 输出工具类
 *
 * 废弃 使用抛异常的方式
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/17 17:33
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes"})
@Deprecated
public class OutputUtils {
    private static final Gson GSON = new Gson();
    private OutputUtils(){}

    public static void output(HttpServletResponse response, int status, String msg, String desc) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        ServletOutputStream pw = response.getOutputStream();
        ResponseEntity responseEntity = new ResponseEntity(status,msg);
        responseEntity.setDesc(desc);
        pw.write(GSON.toJson(responseEntity).getBytes(StandardCharsets.UTF_8));
        pw.flush();
//        pw.close();
    }

    public static void output(HttpServletResponse response, int status, ResponseEntity responseEntity) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        ServletOutputStream pw = response.getOutputStream();
        pw.write(GSON.toJson(responseEntity).getBytes(StandardCharsets.UTF_8));
        pw.flush();
//        pw.close();
    }
}
