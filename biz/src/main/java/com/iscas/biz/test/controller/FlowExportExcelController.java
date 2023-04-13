package com.iscas.biz.test.controller;

import com.iscas.base.biz.util.SpringFileDownloadUtils;
import com.iscas.common.tools.office.excel.ExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/2 23:11
 * @since jdk1.8
 */
@RestController
@RequestMapping("/test/flow/export/excel")
public class FlowExportExcelController {
    @GetMapping
    public void exportExcel() throws Throwable {
        LinkedHashMap<String, String> header = new LinkedHashMap<>();
        header.put("a", "列1");
        header.put("b", "列2");
        header.put("c", "列3");
        SpringFileDownloadUtils.createAndDownloadExcel("test.xlsx",
                Arrays.asList("sheet页-one", "sheet页-two"), (times, sheetName) -> {
                    if (Objects.equals("sheet页-one", sheetName)) {
                        if (times > 10) {
                            //假设10次就结束了
                            return null;
                        }
                        List<Model> models = new ArrayList<>();
                        for (int i = 0; i < 100; i++) {
                            Model model = new Model("0.000000000521", "0.00000000000000145" + i, "2020-11-12");
                            models.add(model);
                        }
                        ExcelUtils.ExcelResult<Model> excelResult = new ExcelUtils.ExcelResult<>();
                        excelResult.setHeader(header);
                        excelResult.setContent(models);
                        excelResult.setSheetName(sheetName);
                        return excelResult;
                    } else if (Objects.equals("sheet页-two", sheetName)) {
                        if (times > 20) {
                            //假设20次就结束了
                            return null;
                        }
                        List<Model> models = new ArrayList<>();
                        for (int i = 0; i < 100; i++) {
                            Model model = new Model("页2-a" + i, "b" + i, "2020-11-12");
                            models.add(model);
                        }
                        ExcelUtils.ExcelResult<Model> excelResult = new ExcelUtils.ExcelResult<>();
                        excelResult.setContent(models);
                        excelResult.setHeader(header);
                        excelResult.setSheetName(sheetName);
                        return excelResult;
                    }
                    return null;

                });


    }

    /**
     * 创建一个测试Bean
     */
    public static class Model {
        private String a;
        private String b;
        private String c;

        public Model(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public Model() {
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }
    }
}
