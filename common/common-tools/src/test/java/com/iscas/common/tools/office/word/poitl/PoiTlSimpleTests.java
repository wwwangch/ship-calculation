package com.iscas.common.tools.office.word.poitl;

import cn.hutool.core.io.resource.ClassPathResource;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 这里只测试了基本功能，其他功能使用方式参见：http://deepoove.com/poi-tl/
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/6/16 14:55
 * @since jdk1.8
 */
public class  PoiTlSimpleTests {

    /**
     * 最简单的DEMO
     * */
    @Test
    public void testSimple() throws IOException {
        ClassPathResource pathResource = new ClassPathResource("poitl/word-simple.docx");
        XWPFTemplate template = XWPFTemplate.compile(pathResource.getStream()).render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, Simple Word Test");
                }});
        File tmpFile = File.createTempFile("word-simple", ".docx");
        tmpFile.deleteOnExit();
        template.writeAndClose(new FileOutputStream(tmpFile));
        tmpFile.delete();
    }

    /**
     * 测试文本
     *
     * Texts可以设置的属性：
     *
     * {
     *   "text": "Sayi",
     *   "style": {
     *     "strike": false, //删除线
     *     "bold": true,  //粗体
     *     "italic": false,  //斜体
     *     "color": "00FF00", //颜色
     *     "underLine": false, //下划线
     *     "fontFamily": "微软雅黑", //字体
     *     "fontSize": 12, //字号
     *     "highlightColor": "green",  //背景高亮色
     *     "vertAlign": "superscript", //上标或者下标
     *     "characterSpacing" : 20 //间距
     *   }
     * }
     * */
    @Test
    public void testText() throws IOException {

        ClassPathResource pathResource = new ClassPathResource("poitl/word-text.docx");
        XWPFTemplate template = XWPFTemplate.compile(pathResource.getStream()).render(
                new HashMap<String, Object>(){{
                    put("title", "测试文本");
                    put("author", Texts.of("张三").style(Style.builder()
                    .buildUnderlineColor("664784").buildUnderlinePatterns(UnderlinePatterns.DASH).build()).bold().color("222222").create());
                    put("link", Texts.of("腾讯首页").link("http://www.qq.com").color("888888").create());
                }});
        File tmpFile = File.createTempFile("word-text", ".docx");
        tmpFile.deleteOnExit();
        template.writeAndClose(new FileOutputStream(tmpFile));
        tmpFile.delete();
    }

    @Test
    public void testTexts() throws IOException {

        ClassPathResource pathResource = new ClassPathResource("poitl/word-texts.docx");
        XWPFTemplate template = XWPFTemplate.compile(pathResource.getStream()).render(
                new HashMap<String, Object>(){{
                    put("title", "测试文本");
                    put("author", Texts.of("张三").style(Style.builder()
                            .buildUnderlineColor("664784").buildUnderlinePatterns(UnderlinePatterns.DASH).build()).bold().color("222222").create());
                    Style style = Style.builder().buildUnderlineColor("664784").buildUnderlinePatterns(UnderlinePatterns.DASH).build();
                    TextRenderData trd1 = new TextRenderData("大风起兮云飞扬，威加海内兮归故乡，安得猛士兮守四方！");
                    TextRenderData trd2 = new TextRenderData("岂曰无衣？七兮。不如子之衣，安且吉兮。岂曰无衣？六兮。不如子之衣，安且燠兮。");
                    TextRenderData trd3 = new TextRenderData("由来称独立，本自号倾城。柳叶眉间发，桃花脸上生。腕摇金钏响，步转玉环鸣。纤腰宜宝袜，红衫艳织成。悬知一顾重，别觉舞腰轻。");
                    trd1.setStyle(style);
                    trd2.setStyle(style);
                    trd3.setStyle(style);
                    put("texts", Arrays.asList(trd1, trd2, trd3));
                }});
        File tmpFile = File.createTempFile("word-text", ".docx");
        tmpFile.deleteOnExit();
        template.writeAndClose(new FileOutputStream(tmpFile));
        tmpFile.delete();
    }

    /**
     * 测试图片
     *
     * Pictures可以设置的属性：
     *
     * {
     *   "pictureType" : "PNG", //图片类型
     *   "pictureSupplier": () -> byte[],  //图片字节数组
     *   "pictureStyle": {
     *     "width": 100,  //宽度
     *     "height": 100  //高度
     *   },
     *   "altMeta": "图片不存在"
     * }
     * */
    @Test
    public void testPic() throws IOException {

        ClassPathResource pathResource = new ClassPathResource("poitl/word-pic.docx");
        XWPFTemplate template = XWPFTemplate.compile(pathResource.getStream()).render(
                new HashMap<String, Object>(){{
                    put("title", Texts.of("测试图片")
                            .color("004567").fontSize(20).fontFamily("微软雅黑").create());

                    //本地图片不存在，显示altMeta
                    put("pic1", Pictures.ofLocal("d:/test.png").altMeta("本地图片不存在").create());

                    //图片流-测试在resources下
                    put("pic2", Pictures.ofStream(new ClassPathResource("poitl/test.jpg").getStream(), PictureType.JPEG).size(200, 200).create());

                    //网络图片
                    put("pic3", Pictures.ofUrl("http://deepoove.com/images/icecream.png").size(200, 200).altMeta("网络图片不存在").create());

                    //svg图片
                    put("pic4", Pictures.ofUrl("https://img.shields.io/badge/jdk-1.6%2B-orange.svg").size(200, 200).altMeta("svg图片不存在").create());

                    //java图片
                    BufferedImage image = new BufferedImage(200, 200,
                            BufferedImage.TYPE_INT_RGB);
                    Graphics2D g2 = (Graphics2D) image.getGraphics();
                    g2.setColor(new Color(200, 200, 200));
                    g2.fillRect(0, 0, 200, 200);
                    put("pic5", Pictures.ofBufferedImage(image, PictureType.PNG).size(200, 200).create());


                }});
        File tmpFile = File.createTempFile("word-pic", ".docx");
        tmpFile.deleteOnExit();
        template.writeAndClose(new FileOutputStream(tmpFile));
        tmpFile.delete();
    }

    /**
     * 测试表格
     *
     * {
     *   "rows": [
     *     {
     *       "cells": [
     *         {
     *           "paragraphs": [
     *             {
     *               "contents": [
     *                 {
     *                   [TextRenderData]
     *                 },
     *                 {
     *                   [PictureRenderData]
     *                 }
     *               ],
     *               "paragraphStyle": null
     *             }
     *           ],
     *           "cellStyle": {
     *             "backgroundColor": "00000",
     *             "vertAlign": "CENTER"
     *           }
     *         }
     *       ],
     *       "rowStyle": {
     *         "height": 2.0f
     *       }
     *     }
     *   ],
     *   "tableStyle": {
     *     "width": 14.63f,
     *     "colWidths": null
     *   },
     *   "mergeRule": {
     *     "mapping": {
     *       "0-0": "1-2"
     *     }
     *   }
     * }
     * */
    @Test
    public void testTable() throws IOException {

        ClassPathResource pathResource = new ClassPathResource("poitl/word-table.docx");
        XWPFTemplate template = XWPFTemplate.compile(pathResource.getStream()).render(
                new HashMap<String, Object>(){{
                    put("title", Texts.of("测试表格")
                            .color("004567").fontSize(20).fontFamily("微软雅黑").create());

                    //普通表格
                    put("table1", Tables.of(new String[][] {
                            new String[] {"姓名", "年龄", "籍贯"},
                            new String[] {"张三", "22", "黑龙江"},
                            new String[] {"李四", "48", "北京"},
                            new String[] {"王五", "18", "山西"}
                    }).create());

                    //带样式表格
                    put("table2", Tables.create(Rows.of("姓名", "年龄", "学历").textColor("00000000").textBold().center().create(),
                            Rows.of("zhangsan", "28", "本科").center().create(),
                            Rows.of("lisi", "54", "博士").center().create(),
                            Rows.of("wangwu", "17", "小学").center().create()));

                    //合并单元格
                    MergeCellRule rule = MergeCellRule.builder().map(MergeCellRule.Grid.of(1, 0), MergeCellRule.Grid.of(1, 2)).build();
                    TableRenderData tableRenderData = Tables.create(Rows.of("姓名", "年龄", "学历").textColor("00000000").textBold().center().create(),
                            Rows.of("暂无数据", null, null).textColor("436782").textBold().center().create());
                    tableRenderData.setMergeRule(rule);
                    put("table3", tableRenderData);

                }});
        File tmpFile = File.createTempFile("word-table", ".docx");
        tmpFile.deleteOnExit();
        template.writeAndClose(new FileOutputStream(tmpFile));
        tmpFile.delete();
    }

    /**
     * 测试西光所的一个模板
     *
     */
    @Test
    public void xiguangTest() throws IOException {
        ClassPathResource pathResource = new ClassPathResource("poitl/xiguang-template.docx");
        XWPFTemplate template = XWPFTemplate.compile(pathResource.getStream()).render(
                new HashMap<String, Object>(){{
                    //标题
                    put("title", "某某光电设备健康状态信息汇报");
                    //系统名称
                    put("systemName", "某某光电设备");
                    //时间阶段
                    put("timeRange", "2020.12.1~2021.08.06");
                    //故障数目
                    put("breakdownSum", "20");
                    //图片
                    put("pic1", Pictures.ofStream(new ClassPathResource("poitl/test.jpg").getStream(), PictureType.JPEG).size(200, 200).create());
                    //内容
                    put("content", "1）\t1分系统：健康状态良好，啦啦啦啦啦啦啦啦啦，预计可靠性95%，预计寿命10年\n" +
                            "2）\t2分系统：健康状态良好，预计可靠性80%，预计寿命15年\n" +
                            "3）\t3分系统：健康状态良好，预计可靠性70%，预计寿命20年\n" +
                            "4）\t4分系统：健康状态良好，预计可靠性70%，预计寿命20年\n" +
                            "5）\t5分系统：健康状态良好，预计可靠性70%，预计寿命20年\n" +
                            "6）\t6分系统：健康状态良好，预计可靠性70%，预计寿命20年\n" +
                            "7）\t7分系统：健康状态良好，预计可靠性70%，预计寿命20年\n" +
                            "8）\t8分系统：健康状态良好，预计可靠性70%，预计寿命20年\n"
                    );
                    //维护建议
                    put("content2", "违规围观围观围观为各位灌灌灌灌灌灌灌灌围观围观围观为各位围观围观为普工奖品为冠军庞伟哦工具欧文\n\t围观围观围观为给我，为各位公文管家婆为各位公文管家婆围观围观围观为");

                }});
        File tmpFile = File.createTempFile("xiguang-simple", ".docx");
        tmpFile.deleteOnExit();
        template.writeAndClose(new FileOutputStream(tmpFile));
        tmpFile.delete();
    }


}
