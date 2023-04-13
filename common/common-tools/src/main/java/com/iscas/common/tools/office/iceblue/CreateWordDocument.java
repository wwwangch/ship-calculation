package com.iscas.common.tools.office.iceblue;

import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.TextRange;

import java.awt.*;


/**
 * 参考<a href="https://www.e-iceblue.cn/spiredocforjavaoperating/create-word-document-in-java.html">https://www.e-iceblue.cn/spiredocforjavaoperating/create-word-document-in-java.html</a>
 *
 * @author admin*/

public class CreateWordDocument {
    @SuppressWarnings("AlibabaMethodTooLong")
    public static void main(String[] args){
        //创建Word文档
        Document document = new Document();


        //大标题样式
        ParagraphStyle style1 = new ParagraphStyle(document);
        style1.setName("titleStyle");
        style1.getCharacterFormat().setBold(true);
        style1.getCharacterFormat().setTextColor(Color.BLACK);
        style1.getCharacterFormat().setFontName("宋体");
        style1.getCharacterFormat().setFontSize(18f);
        document.getStyles().add(style1);

        //正文内容得样式
        ParagraphStyle style2 = new ParagraphStyle(document);
        style2.setName("contentStyle");
        style2.getCharacterFormat().setFontName("宋体");
        style2.getCharacterFormat().setFontSize(11f);
        document.getStyles().add(style2);

        //一级标题样式
        ParagraphStyle styleOne = new ParagraphStyle(document);
        styleOne.setName("fistTitleStyle");
        styleOne.getCharacterFormat().setBold(true);
        styleOne.getCharacterFormat().setTextColor(Color.BLACK);
        styleOne.getCharacterFormat().setFontName("宋体");
        styleOne.getCharacterFormat().setFontSize(16f);
        document.getStyles().add(styleOne);

        //二级标题样式
        ParagraphStyle styleTwo = new ParagraphStyle(document);
        styleTwo.setName("secondTitleStyle");
        styleTwo.getCharacterFormat().setBold(true);
        styleTwo.getCharacterFormat().setTextColor(Color.BLACK);
        styleTwo.getCharacterFormat().setFontName("宋体");
        styleTwo.getCharacterFormat().setFontSize(14f);
        document.getStyles().add(styleTwo);

        //添加一个section
        Section section = document.addSection();

        //大标题
        Paragraph para0 = section.addParagraph();
        para0.appendText("测试Word");
        //设置标题的对其方式
        para0.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
        para0.applyStyle("titleStyle");

        //指导思想标题
        Paragraph para1 = section.addParagraph();
        para1.appendText("一、指导思想和目的");
        para1.applyStyle("fistTitleStyle");

        //指导思想内容
        Paragraph para2 = section.addParagraph();
        para2.appendText("豫章故郡，洪都新府。星分翼轸，地接衡庐。襟三江而带五湖，控蛮荆而引瓯越。"+
                        "物华天宝，龙光射牛斗之墟；人杰地灵，徐孺下陈蕃之榻。雄州雾列，俊采星驰。台隍枕夷夏之交，宾主尽东南之美。"+
                        "都督阎公之雅望，棨戟遥临；宇文新州之懿范，襜帷暂驻。十旬休假，胜友如云；千里逢迎，高朋满座。"+
                        "腾蛟起凤，孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；童子何知，躬逢胜饯。");

        Paragraph para3 = section.addParagraph();
        para3.appendText("时维九月，序属三秋。潦水尽而寒潭清，烟光凝而暮山紫。俨骖騑于上路，访风景于崇阿；临帝子之长洲，得天人之旧馆。"+
                        "层峦耸翠，上出重霄；飞阁流丹，下临无地。鹤汀凫渚，穷岛屿之萦回；桂殿兰宫，即冈峦之体势。");

        para2.applyStyle("contentStyle");
        para3.applyStyle("contentStyle");

        //设置第二段和第三段的段首缩进
        para2.getFormat().setFirstLineIndent(25f);
        para3.getFormat().setFirstLineIndent(25f);

        //设置第一段和第二段的段后间距
        para0.getFormat().setAfterSpacing(15f);
        para1.getFormat().setAfterSpacing(10f);
        para2.getFormat().setAfterSpacing(10f);

        //ZZ背景
        Paragraph para4 = section.addParagraph();
        para4.appendText("二、ZZ背景");
        para4.applyStyle("fistTitleStyle");
        para4.getFormat().setBeforeSpacing(15f);

        //ZZ背景的内容
        Paragraph para5 = section.addParagraph();
        para5.appendText("披绣闼，俯雕甍，山原旷其盈视，川泽纡其骇瞩。闾阎扑地，钟鸣鼎食之家；舸舰弥津，" +
                "青雀黄龙之舳。云销雨霁，彩彻区明。落霞与孤鹜齐飞，秋水共长天一色。渔舟唱晚，响穷彭蠡之滨，" +
                "雁阵惊寒，声断衡阳之浦。");
        para5.getFormat().setBeforeSpacing(15f);
        para5.applyStyle("contentStyle");
        para5.getFormat().setFirstLineIndent(25f);

        //ZZ编成
        Paragraph para6 = section.addParagraph();
        para6.appendText("三、ZZ编成");
        para6.applyStyle("fistTitleStyle");
        para6.getFormat().setBeforeSpacing(15f);

        //ZZ编成-红军
        Paragraph para7 = section.addParagraph();
        para7.appendText("(一) 红军");
        para7.getFormat().setBeforeSpacing(15f);
        para7.applyStyle("secondTitleStyle");


        //表格
        //数据
        String[] header = {"姓名", "性别", "部门", "工号"};
        String[][] data =
                {
                        new String[]{"Winny", "", "综合", "0109"},
                        new String[]{"Lois", "女", "综合", "0111"},
                        new String[]{"Jois", "男", "技术", "0110"},
                        new String[]{"Moon", "女", "销售", "0112"},
                        new String[]{"Vinit", "女", "后勤", "0113"},
                };

        //添加表格
        Table table = section.addTable(true);
        //设置表格的行数和列数
        table.resetCells(data.length + 1, header.length);

        //设置第一行作为表格的表头并添加数据
        TableRow row = table.getRows().get(0);
        row.isHeader(true);
        row.setHeight(20);
        row.setHeightType(TableRowHeightType.Exactly);
        row.getRowFormat().setBackColor(Color.gray);
        for (int i = 0; i < header.length; i++) {
            row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange range1 = p.appendText(header[i]);
            range1.getCharacterFormat().setFontName("Arial");
            range1.getCharacterFormat().setFontSize(12f);
            range1.getCharacterFormat().setBold(true);
        }

        //添加数据到剩余行
        for (int r = 0; r < data.length; r++) {
            TableRow dataRow = table.getRows().get(r + 1);
            dataRow.setHeight(25);
            dataRow.setHeightType(TableRowHeightType.Exactly);
            dataRow.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < data[r].length; c++) {
                dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                TextRange range2 = dataRow.getCells().get(c).addParagraph().appendText(data[r][c]);
                range2.getCharacterFormat().setFontName("Arial");
                range2.getCharacterFormat().setFontSize(10f);
            }
        }

        //设置单元格背景颜色
        for (int j = 1; j < table.getRows().getCount(); j++) {
            if (j % 2 == 0) {
                TableRow row2 = table.getRows().get(j);
                for (int f = 0; f < row2.getCells().getCount(); f++) {
                    row2.getCells().get(f).getCellFormat().setBackColor(new Color(173, 216, 230));
                }
            }
        }
        table.applyVerticalMerge(1, 1, 2);

        //保存文档
        document.saveToFile("d:/111/Output.docx", FileFormat.Docx);
    }
}