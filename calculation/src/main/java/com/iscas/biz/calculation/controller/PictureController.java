package com.iscas.biz.calculation.controller;

import com.iscas.base.biz.util.SpringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @author yichuan@iscas.ac.cn
 * @version 1.0
 * @date 2023/8/14 15:44
 */
@RestController
@RequestMapping("/picture")
@Slf4j
@Tag(name = "预览图片")
public class PictureController {
    @Value("${file.server.path}")
    private String filePath;

    @GetMapping(value = "/preview/{fileName}")
    @Operation(summary = "预览图片,输入svg文件名，返回svg文件")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "输入图片名，返回图片，支持jpeg,png,svg",
            content = @Content(examples = @ExampleObject(value = "ceshi.svg")))
    public void getSvgImage(@PathVariable String fileName) throws IOException {
        try {
            // 获取图片文件
            File imageFile = ResourceUtils.getFile(filePath + fileName);
            if (imageFile.exists()) {
                // 如果图片存在，返回图片
                ServletOutputStream os = SpringUtils.getResponse().getOutputStream();
                if (fileName.endsWith(".svg")) {
                    SpringUtils.getResponse().setContentType("image/svg+xml");
                } else if (fileName.endsWith(".jpeg") || fileName.endsWith(".png")) {
                    SpringUtils.getResponse().setContentType("image/jpeg");
                }
                new FileInputStream(imageFile).transferTo(os);
            } else {
                // 如果图片不存在，返回文字提示
                PrintWriter writer = SpringUtils.getResponse().getWriter();
                SpringUtils.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
                writer.println("图片不存在");
            }
        } catch (Exception e) {
            // 处理其他异常
            PrintWriter writer = SpringUtils.getResponse().getWriter();
            SpringUtils.getResponse().setContentType(MediaType.APPLICATION_JSON_VALUE);
            writer.println("服务器错误");
        }
    }
}
