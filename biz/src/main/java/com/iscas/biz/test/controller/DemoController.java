package com.iscas.biz.test.controller;

import cn.hutool.core.io.IoUtil;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.common.tools.core.random.RandomStringUtils;
import com.iscas.common.web.tools.cookie.CookieUtils;
import com.iscas.common.web.tools.json.JsonObject;
import com.iscas.common.web.tools.json.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/7/3 8:25
 * @since jdk1.8
 */
@RestController
@Tag(name = "swagger示例")
public class DemoController {
    @Operation(summary = "[测试/李爽] 测试测试哈哈", description = "create by:朱全文 2020-02-21")
    @GetMapping("/tx")
    public String tx() {
        CookieUtils.setCookie(SpringUtils.getResponse(), "mykey", "myvalue", 10000);
        System.out.println("tx");
        return RandomStringUtils.randomStr(1024 * 1024);
    }

    @Operation(summary = "[测试/李爽] 测试测试哈哈", description = "create by:朱全文 2020-02-21")
    @Parameters(
            {
                    @Parameter(name = "map", description = "上传到数据", required = true)
            }
    )
    @PostMapping("/tx2")
    public String tx(@RequestBody Map<String, Object> map) {
        CookieUtils.setCookie(SpringUtils.getResponse(), "a", "aaa", 1000000);
        return "tx";
    }

    @PutMapping("/tx3")
    public String tx(@RequestParam String key, @RequestBody String data) {
        System.out.println(data);
        return "tx";
    }

    @DeleteMapping("/tx4")
    public String tx4(@RequestParam String key) {
        return "tx";
    }

    @PostMapping("/tx5")
    public String tx5(@RequestParam(required = false) String name, @RequestParam(required = false) String age) {
        System.out.println(name);
        System.out.println(age);
        return "tx";
    }

    @GetMapping("/tx6")
    public void tx6(HttpServletResponse response) throws IOException {
        File file = new File("e:/A2019003_1km_tsm.L3M.hdf");
        @Cleanup InputStream is = new FileInputStream(file);
        response.reset();
        response.setContentType("application/octet-stream;charset=utf-8"); // 改成输出excel文件
        response.setHeader(
                "Content-disposition",
                "attachment; filename="
                        + "source.hdf");
        IoUtil.copy(is, response.getOutputStream());
    }

    @PostMapping("/tx7")
    public String tx6(MultipartFile file) throws IOException {
        return "tx7";
    }

    @GetMapping("/tx8")
    public String tx8() throws IOException {
        SpringUtils.getSession().setAttribute("name", "张三");
        return "tx7";
    }

    @GetMapping(value = "/video", produces = {"video/mp4"})
    public void tx9() throws IOException {
        byte[] bytes = null;
        File file = new File("H:/ideaProjects/newframe-dev/biz/src/main/resources/static/三国之战神无双.mp4");
        @Cleanup RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        HttpServletRequest request = SpringUtils.getRequest();
        String range = request.getHeader("Range");
        int from = 0;
        int to = accessFile.length() - 1 > 10240 ? 10240 : (int) accessFile.length() - 1;
        if (range != null) {
            range = StringUtils.substringAfter(range, "bytes=");
            String fromStr = StringUtils.substringBefore(range, "-");
            String toStr = StringUtils.substringAfterLast(range, "-");
            if (StringUtils.isNotEmpty(fromStr)) {
                from = Integer.valueOf(fromStr);
            }
            if (from != 0) {
                to = accessFile.length() - 1 > from + to ? from + to : (int) accessFile.length() - 1;
            }
            if (StringUtils.isNotEmpty(toStr)) {
                to = Integer.valueOf(toStr);
            }
        }

        accessFile.seek(from);
        bytes = new byte[to - from + 1];
        accessFile.read(bytes);
        HttpServletResponse response = SpringUtils.getResponse();
        response.setHeader("Content-Type", "video/mp4");
        response.setHeader("Content-Range", "bytes " + from + "-" + to + "/" + accessFile.length());
        response.setStatus(206);
        response.getOutputStream().write(bytes);
    }

    @GetMapping("/health")
    public String health() {
        JsonObject jsonObject = JsonUtils.createJsonObject();
        jsonObject.set("health", 1)
                .set("version", "0.0.1")
                .set("cache_refresh", 1);
        return jsonObject.toJson();
    }

    @PostMapping("/test/form")
    public String testForm(TestBean testBean) {
        return "success";
    }

    @GetMapping("/tx9")
    public String tx9(TestForm testForm) throws IOException {
        SpringUtils.getSession().setAttribute("name", "张三");
        return "tx9";
    }

    @Data
    static class TestForm {
        private String key;
        private Integer value;
    }

    @Data
    static class TestBean {
        private String key;
        private MultipartFile file;
    }
}
