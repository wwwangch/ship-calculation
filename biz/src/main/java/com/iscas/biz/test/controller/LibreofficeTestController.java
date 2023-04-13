package com.iscas.biz.test.controller;

import com.iscas.templet.common.BaseController;
import com.iscas.templet.common.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/22 17:36
 * @since jdk1.8
 */
@RestController
public class LibreofficeTestController extends BaseController {

    @GetMapping("/libreoffice/test")
    public ResponseEntity libreofficeTest() {
        ResponseEntity response = getResponse();
        String cmd = "libreoffice7.0 --convert-to pdf:writer_pdf_Export " + "/root/libreoffice/a.docx" + " --outdir " + "/root/libreoffice/";
        System.out.println(cmd);
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            // 获取返回状态
            int status = process.waitFor();
            // 销毁process
            process.destroy();
            process = null;
            System.out.println("status -> " + status);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
