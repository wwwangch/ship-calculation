package com.iscas.biz.test.controller;

import com.iscas.base.biz.util.SpringFileDownloadUtils;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.Map;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/23 20:52
 * @since jdk1.8
 */
@RestController
@RequestMapping("/down/file")
public class DownloadController {
    @GetMapping("/t1")
    public void down1() throws Exception {
        FileDownloadUtils.downByStream(SpringUtils.getRequest(), SpringUtils.getResponse(), new FileInputStream("D:/soft/test-quick-frame/newframe-tttt/my-checkstyle.xml"), "功能清单.txt");
    }

    @GetMapping("/t2")
    public ResponseEntity<InputStreamResource> down2() throws Exception {
        return SpringFileDownloadUtils.download("test.txt", "d:/test.csv");
    }

    @GetMapping("/t3")
    public ResponseEntity<ByteArrayResource> down3() throws Exception {
        byte[] bytes = Files.readAllBytes(new File("d:/test.csv").toPath());
        return SpringFileDownloadUtils.download("test.txt", bytes);
    }

    @PostMapping("/t4")
    public ResponseEntity<ByteArrayResource> down4(String fileName, @RequestBody Map data) throws Exception {
        System.out.println(data);
        byte[] bytes = Files.readAllBytes(new File("d:/test.csv").toPath());
        return SpringFileDownloadUtils.download(fileName, bytes);
    }

}
