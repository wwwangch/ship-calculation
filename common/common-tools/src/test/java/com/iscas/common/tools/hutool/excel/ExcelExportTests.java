package com.iscas.common.tools.hutool.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/11/12 14:40
 * @since jdk1.8
 */
public class ExcelExportTests {
    @Test
    public void test1() throws IOException {

        List<String> header = CollUtil.newArrayList("人员", "周", "周", "CMI", "视频直播", "植被检测", "国大");

        List<String> row1 = CollUtil.newArrayList("张三", "上周情况", "总结", "XXXXXXXXX",null,null,null);
        List<String> row2 = CollUtil.newArrayList("张三", "上周情况", "进度", "100%", null,null,null);
        List<String> row3 = CollUtil.newArrayList("张三", "本周计划", "总结", "yyyyyyyyyy",null,null,null);

        List<String> row4 = CollUtil.newArrayList("李四", "上周情况", "总结", null, "往往会问我为何", null, null);
        List<String> row5 = CollUtil.newArrayList("李四", "上周情况", "进度", null, "100%", null, null);
        List<String> row6 = CollUtil.newArrayList("李四", "本周计划", "总结", null, "我给问问呵呵呵让我很好玩", null, null);

//        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

        File file = new File("test.xlsx");
        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(file);
        file.deleteOnExit();
        //合并单元格后的标题行，使用默认标题样式，把周合并
        writer.merge(0,0,1,2,"周",true);
        writer.writeHeadRow(header);

        //填写数据内容，具体业务时候可以数据格式写循环，这里的两组数据只是做演示
        writer.merge(1,3, 0, 0, row1.get(0), false);
        writer.merge(1,2, 1, 1, "上周情况", false);
        writer.merge(3,3, 1, 2, "本周计划", false);
        writer.writeRow(row1);
        writer.writeRow(row2);
        writer.writeRow(row3);

        writer.merge(4,6, 0, 0, row4.get(0), false);
        writer.merge(4,5, 1, 1, "上周情况", false);
        writer.merge(6, 6, 1, 2, "本周计划", false);
        writer.writeRow(row4);
        writer.writeRow(row5);
        writer.writeRow(row6);

        //关闭writer，释放内存
        writer.close();
        file.delete();
    }
}
