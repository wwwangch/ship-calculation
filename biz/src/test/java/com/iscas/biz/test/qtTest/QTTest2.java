package com.iscas.biz.test.qtTest;

import com.iscas.common.tools.office.excel.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
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
public class QTTest2 {
    private static String pPath = "C:/Users/admin/Downloads/qy/navysite.de2";
    private static String tPath = "C:/Users/admin/Downloads/cy/target";
    public static void main(String[] args) throws IOException {
        try {
            new File(tPath).mkdirs();

        } catch (Exception e) {}
        File file = new File(pPath);
        for (File listFile : file.listFiles()) {
            String preKey = listFile.getName();
            File[] files = listFile.listFiles((dir, name) -> name.endsWith(".html") && !name.endsWith(").html"));
            if (files != null) {
                for (File htmlFile : files) {
                    Document document = Jsoup.parse(htmlFile, "utf-8");
                    String text = document.getElementsByTag("h1").get(0).text();
                    String tmpExcelname = preKey + "-" + text;
                    tmpExcelname = tmpExcelname.replace("\\", "_");
                    tmpExcelname = tmpExcelname.replace("/", "_");
                    String excelName = tmpExcelname + ".xlsx";
                    System.out.println(excelName);

                    ExcelUtils.ExcelResult excelResult = new ExcelUtils.ExcelResult();

                    excelResult.setHeader(new LinkedHashMap<>(){{
                        put("name", "Name");
                        put("rank", "Rank/Rate");
                        put("period", "Period");
                        put("division", "Division");
                        put("remark", "Remarks/Photo");
                    }});
                    excelResult.setSheetName(tmpExcelname);
                    List<Map> content = new ArrayList<>();

                    //解析表格
                    Elements table = document.getElementsByTag("table");
                    if (table != null && table.size() > 0) {
                        Elements tbody = table.get(0).getElementsByTag("tbody");
                        if (tbody != null && tbody.size() > 0) {
                            Elements tr = tbody.get(0).getElementsByTag("tr");
                            if (tr != null && tr.size() > 0) {
                                for (Element t : tr) {
                                    Elements td = t.getElementsByTag("td");
                                    if (td != null && td.size() >= 5) {
                                        Map map = new HashMap();
                                        map.put("name", td.get(0).text());
                                        map.put("rank", td.get(0).text());
                                        map.put("period", td.get(0).text());
                                        map.put("division", td.get(0).text());
                                        map.put("remark", td.get(0).text());
                                        content.add(map);
                                    }
                                }
                            }
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
    }
}
