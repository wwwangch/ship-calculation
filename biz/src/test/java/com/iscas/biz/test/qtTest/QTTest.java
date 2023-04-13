package com.iscas.biz.test.qtTest;

import com.iscas.common.tools.office.excel.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.*;

/**
 * 测试qt
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/9/18 9:52
 * @since jdk1.8
 */
@Slf4j
public class QTTest {
    private static String pPath = "C:/Users/admin/Downloads/hqtzl/hqtzl";
    private static String tPath = "C:/Users/admin/Downloads/qt/target";
    public static void main(String[] args) throws IOException {
        try {
            new File(tPath).mkdirs();

        } catch (Exception e) {}
        File file = new File(pPath);
        for (File htmlFile : Objects.requireNonNull(file.listFiles(pathname -> pathname.getName().endsWith(".html")))) {
            Document document = Jsoup.parse(htmlFile, "windows-1252");
            Elements center = document.getElementsByTag("center");
            Elements h1 = center.get(0).getElementsByTag("h1");
            String text = "";
            try {
                text = h1.get(1).text();
            } catch (Exception e) {
                log.warn("未获取到标题,使用空字符串", e);
            }
            //生成excel名
            String tmpExcelname = StringUtils.substringBeforeLast(htmlFile.getName(), ".") + "-" + text;
            tmpExcelname = tmpExcelname.replace("\\", "_");
            tmpExcelname = tmpExcelname.replace("/", "_");
            String excelName = tmpExcelname + ".xlsx";
//            System.out.println(excelName);

            ExcelUtils.ExcelResult excelResult = new ExcelUtils.ExcelResult();

            excelResult.setHeader(new LinkedHashMap<>(){{
                put("size", "Size");
                put("description", "Image Description");
                put("source", "Source");
            }});
            excelResult.setSheetName(tmpExcelname);
            List<Map> content = new ArrayList<>();
            //获取table
            Elements table = document.getElementsByTag("table");
            if (table != null && table.size() > 0) {
                Element table0 = table.get(0);
                if (table0 != null) {
                    Elements tr = table0.getElementsByTag("tr");
                    tr.stream().skip(1).forEach(r -> {
                        Elements tds = r.getElementsByTag("td");
                        if (tds.size() >= 4) {
                            Map map = new HashMap();
                            map.put("size", tds.get(1).text());
                            map.put("description", tds.get(2).text());
                            map.put("source", tds.get(3).text());
                            content.add(map);
                        }
                    });
                }
            }
            excelResult.setContent(content);
            File targetFile = new File(tPath, excelName);
            try {
                ExcelUtils.exportXLSXExcel(Arrays.asList(excelResult),  targetFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
