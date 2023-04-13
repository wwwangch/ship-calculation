package com.iscas.common.tools.office.word;

import cn.hutool.core.io.resource.ClassPathResource;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试将FTL模板转化为Word
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/5/22 15:55
 * @since jdk1.8
 */
public class Template2DocUtilsTests {

    private Map<String, Object> crateMap() throws IOException {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("name", "段然涛");
        data.put("sex", "男");
        data.put("birthday", "1994-03-14");
        data.put("phone", "17737138812");
        data.put("address", "河南省许昌市");
        data.put("school", "江西科技师范大学");
        List<Map<String, String>> educations = new ArrayList<Map<String, String>>();
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("school", "禹州一高");
        paramsMap.put("startTime", "2008-09");
        paramsMap.put("endTime", "2012-06");
        paramsMap.put("witness", "李磊");
        Map<String, String> paramsMap2 = new HashMap<String, String>();
        paramsMap2.put("school", "江西科技师范大学");
        paramsMap2.put("startTime", "2012-09");
        paramsMap2.put("endTime", "2016-07");
        paramsMap2.put("witness", "李杰");
        educations.add(paramsMap);
        educations.add(paramsMap2);
        data.put("educations", educations);
        List<String> images = new ArrayList<String>();
        ClassPathResource classPathResource = new ClassPathResource("templates/word/aaa.jpg");
        images.add(Template2DocUtils.getImageBase(classPathResource.getStream()));
//            images.add(getImageBase("C:/Users/Administrator/Desktop/图片/timg11.jpg"));
        data.put("images", images);
        return data;
    }

    /**
    * 通过resources下的方式生成word
    * */
    @Test
    @Disabled
    public void test1() throws IOException, TemplateException {
        Map<String, Object> data = crateMap();
        Template2DocUtils.crateDocFromResources(data, "/templates/word/demo.ftl", "C:/Users/Administrator/Desktop/文档/aaa.doc");
    }

    /**
     * 通过绝对路径的方式生成word
     * */
    @Test
    @Disabled
    public void test2() throws IOException, TemplateException {
        Map<String, Object> data = crateMap();
        Template2DocUtils.crateDocFromDir(data, "H:\\ideaProjects\\newframe-dev\\common\\common-tools\\src\\test\\resources\\templates\\word\\demo.ftl", "C:/Users/Administrator/Desktop/文档/aaa.doc");
    }
}
