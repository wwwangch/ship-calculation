package com.iscas.base.biz.test.controller;

import com.iscas.base.biz.test.model.TestE;
import com.iscas.base.biz.util.SpringUtils;
import com.iscas.common.tools.office.excel.ExcelUtils;
import com.iscas.common.web.tools.file.FileDownloadUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/9/6 20:35
 * @since jdk1.8
 */
@RestController
public class TestExcel {
    @GetMapping("/testExcelD")
    public void testExcelD() throws Exception {
        ExcelUtils.ExcelResult<TestE> result = new ExcelUtils.ExcelResult<>();
        result.setSheetName("111");

        LinkedHashMap map = new LinkedHashMap();
        map.put("a", "wgwe");
        map.put("b", "wewg");
        map.put("c", "mwmgw");
        result.setHeader(map);
        TestE testE = new TestE();
        testE.setA("gw");
        testE.setB(21);
        testE.setC(234);
        result.setContent(Arrays.asList(testE));
        FileDownloadUtils.setResponseHeader(SpringUtils.getRequest(), SpringUtils.getResponse(),"测试测试测试.xlsx");
        ExcelUtils.exportXLSXExcel(Arrays.asList(result), 17, SpringUtils.getResponse().getOutputStream());
    }
}
