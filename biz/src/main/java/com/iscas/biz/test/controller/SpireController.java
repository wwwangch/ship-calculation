package com.iscas.biz.test.controller;

import com.iscas.base.biz.util.SpringUtils;
import com.iscas.common.tools.office.iceblue.ReadWord;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import com.iscas.templet.common.BaseController;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/12/17 17:30
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/spire")
public class SpireController extends BaseController {

    @GetMapping
    public String test() {
        return ReadWord.readWord();
    }

    @GetMapping("/t1")
    public void docx2Html() throws Exception {
        //实例化Document类的对象
        Document doc = new Document();

        //加载Word文档
//        doc.loadFromStream(new ClassPathResource("离线安装gitlab.docx").getInputStream(), FileFormat.Docx);
        doc.loadFromFile("/gitlab.docx");

        //保存为HTML格式
        doc.saveToFile("/test.html", FileFormat.Html);
        doc.dispose();
        doc.close();
        FileDownloadUtils.downFile(SpringUtils.getRequest(), SpringUtils.getResponse(), "/test.html", "test.html");
    }

}
