package com.iscas.common.tools.pdfword;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;


/**
 * @author admin
 */
@SuppressWarnings({"unused", "DanglingJavadoc"})
public final class WaterMarkUtilOffice {

    public WaterMarkUtilOffice() {
    }

    private static WaterMarkUtilOffice instance;
    /**
     * 用于存储一个文档：比如新增一个文档时返回，新增的文档
     */
    private Dispatch aDoc = null;
    /**
     * 当前活动窗口
     */
    private Dispatch activeWindow = null;
    /**
     * 存储当前被选中文档
     */
    private Dispatch docSelection = null;
    /**
     * 存储所有的文档
     */
    private Dispatch wordDocs = null;
    private String fileName;
    /**
     * Word对象
     */
    private ActiveXComponent wordApp;

    public static synchronized WaterMarkUtilOffice getInstance() {
        if (instance == null) {
            instance = new WaterMarkUtilOffice();
        }
        return instance;
    }


    /*** 初始化Word对象*/
    public boolean initWordApp() {
        boolean retFlag;
        //初始化com线程【相当于打开冰箱门，准备放大箱】
        //使用结束后要调用 realease方法关闭线程【相当于关上冰箱门】
        ComThread.InitSTA();
        // 初始化word应用程序，初始化表格是：Excel.Application
        wordApp = new ActiveXComponent("Word.Application");
        try {
            //配置启动word时是显示执行还是隐式执行
            wordApp.setProperty("Visible", new Variant(false));
            // 获取word所有文档对象
            wordDocs = wordApp.getProperty("Documents").toDispatch();
            retFlag = true;
        } catch (Exception e) {
            retFlag = false;
            e.printStackTrace();
        }
        return retFlag;
    }


    /**
     * 打开一个已存在的文档
     */
    public void openDocument(String docPath) {
        if (this.aDoc != null) {
            this.closeDocument();
        }
        //docPath要打开的文档的详细地址
        aDoc = Dispatch.call(wordDocs, "Open", new Variant(docPath)).toDispatch();
        //docPath要打开的文档的详细地址
        docSelection = Dispatch.get(wordApp, "Selection").toDispatch();
    }


    /**
     * 取得活动窗体对象
     */
    public void getActiveWindow() {
        // 取得活动窗体对象
        activeWindow = wordApp.getProperty("ActiveWindow").toDispatch();
    }


    /***创建一个新的word文档*/
    public void createNewDocument() {
        //创建一个新的word文档,并返回
        aDoc = Dispatch.call(wordDocs, "Add").toDispatch();
        //获得该文档对象，并返回
        docSelection = Dispatch.get(wordApp, "Selection").toDispatch();
    }


    /*** 保存并关闭当前word文档*/
    public void closeDocument() {
        if (aDoc != null) {
            //保存
            Dispatch.call(aDoc, "Save");
            //关闭
            Dispatch.call(aDoc, "Close", new Variant(0));
            aDoc = null;
        }
    }


    /*** 关闭Word资源*/
    public void closeWordObj() {
        // 关闭word文件
        wordApp.invoke("Quit", new Variant[]{});
        // 释放com线程。根据jacob的帮助文档，com的线程回收不由java的垃圾回收器处理
        ComThread.Release();
    }


    /**
     * 插入图片
     *
     * @param pages   总页数
     * @param imgPath 图片路径
     * @param left    距离左上角位置
     * @param top     距离上角位置
     */
    public void setImages(int pages, String imgPath, int left, int top) {
        for (int i = 0; i < pages; i++) {
            Dispatch selection = Dispatch.get(wordApp, "Selection").toDispatch();
            Dispatch inLineShapes = Dispatch.get(selection, "InLineShapes").toDispatch();
            Dispatch picture = Dispatch.call(inLineShapes, "AddPicture", imgPath).toDispatch();
            //选中图片
            Dispatch.call(picture, "Select");
            //设置宽度高度
            Dispatch.put(picture, "Width", new Variant(1000));
            Dispatch.put(picture, "Height", new Variant(1000));
            //设置图片相对左上角偏移位置
            selection = Dispatch.get(wordApp, "Selection").toDispatch();
            Dispatch shapeRange = Dispatch.get(selection, "ShapeRange").toDispatch();
            Dispatch.put(shapeRange, "Left", new Variant(left));
            Dispatch.put(shapeRange, "Top", new Variant(top));


            //翻到下一页
            Dispatch browser = Dispatch.get(wordApp, "Browser").toDispatch();
            Dispatch.call(browser, "Next");
        }
    }


    /**
     * 设置页眉
     *
     * @param headerStr headerStr
     */
    public void setHeader(String headerStr) {
        // 活动窗格
        Dispatch activePane = Dispatch.get(activeWindow, "ActivePane").toDispatch();
        // 视窗对象
        Dispatch view = Dispatch.get(activePane, "View").toDispatch();

        // 打开页眉，值为9，页脚值为10
        Dispatch.put(view, "SeekView", new Variant(9));
        // 获取页眉和页脚
        Dispatch headerfooter = Dispatch.get(docSelection, "HeaderFooter").toDispatch();

        //页眉赋值
        Dispatch range = Dispatch.get(headerfooter, "Range").toDispatch();
        Dispatch.put(range, "Text", new Variant(headerStr));

        //设置字体
        Dispatch font = Dispatch.get(range, "Font").toDispatch();
        Dispatch.put(font, "Name", new Variant("微软雅黑"));
        Dispatch.put(font, "Bold", new Variant(true));
        Dispatch.put(font, "Size", 20);
        //颜色是16进制倒着写，然后转10进制
        Dispatch.put(font, "Color", Integer.valueOf("0000FF", 16).toString());

        //0表示恢复视图;
        Dispatch.put(view, "SeekView", new Variant(0));
    }


    /**
     * 文档设置图片水印,waterPic水印图片路径
     */
    public void setWaterPic(String waterPic) {
        // 活动窗格
        Dispatch activePane = Dispatch.get(activeWindow, "ActivePane").toDispatch();
        // 视窗对象
        Dispatch view = Dispatch.get(activePane, "View").toDispatch();

        // 打开页眉，值为9，页脚值为10
        Dispatch.put(view, "SeekView", new Variant(9));
        // 获取页眉和页脚
        Dispatch headerfooter = Dispatch.get(docSelection, "HeaderFooter").toDispatch();
        // 获取水印图形对象
        Dispatch shapes = Dispatch.get(headerfooter, "Shapes").toDispatch();

        //调用shapes对象的AddPicture方法将图片插入当前文档
        Dispatch picture = Dispatch.call(shapes, "AddPicture", waterPic).toDispatch();

        //选择当前word文档的图片水印
        Dispatch.call(picture, "Select");

        //设置图片水印参数
        Dispatch.put(picture, "Left", new Variant(30));
        Dispatch.put(picture, "Top", new Variant(150));

        //关闭页眉，0表示恢复视图;
        Dispatch.put(view, "SeekView", new Variant(0));
    }


    /**
     * 文档设置文字水印--实质是设置页眉，将文字转为艺术字图片
     */
    public void setWaterMark(String waterMarkStr) {
        // 活动窗格
        Dispatch activePane = Dispatch.get(activeWindow, "ActivePane").toDispatch();
        // 视窗对象
        Dispatch view = Dispatch.get(activePane, "View").toDispatch();

        // 打开页眉，值为9，页脚值为10
        Dispatch.put(view, "SeekView", new Variant(9));
        // 获取页眉和页脚
        Dispatch headerfooter = Dispatch.get(docSelection, "HeaderFooter").toDispatch();

        // 获取水印图形对象
        Dispatch shapes = Dispatch.get(headerfooter, "Shapes").toDispatch();


        /**插入文字，并转为图片：
         * 操作对象、方法、艺术字格式[0白色、1黑左下右上，2黑中上两下、3黑中小两大、4黑竖排、5黑中下两上]
         * 水印内容、字体、字体大小、字体是否粗体、字体是否斜体
         * 左边距、上边距
         */
        Dispatch selection = Dispatch.call(shapes, "AddTextEffect", new Variant(0),
                waterMarkStr, "微软雅黑", new Variant(10), new Variant(true), new Variant(false),
                new Variant(150), new Variant(250)).toDispatch();


        //选中当前文档水印
        Dispatch.call(selection, "Select");
        Dispatch shapeRange = Dispatch.get(docSelection, "ShapeRange").toDispatch();


        /**
         * 设置水印透明度和颜色
         */
        Dispatch.put(shapeRange, "Name", "PowerPlusWaterMarkObject6");
        Dispatch textEffect = Dispatch.get(shapeRange, "TextEffect").toDispatch();
        Dispatch.put(textEffect, "NormalizedHeight", Boolean.FALSE);


        Dispatch line = Dispatch.get(shapeRange, "Line").toDispatch();
        Dispatch.put(line, "Visible", Boolean.FALSE);


        Dispatch fill = Dispatch.get(shapeRange, "Fill").toDispatch();
        Dispatch.put(fill, "Visible", Boolean.FALSE);
        // 设置水印透明度
        Dispatch.put(fill, "Transparency", new Variant(0.1));

        Dispatch foreColor = Dispatch.get(fill, "ForeColor").toDispatch();
        // 设置水印颜色
        Dispatch.put(foreColor, "RGB", new Variant(255));

        Dispatch.call(fill, "Solid");


        /**
         * 设置水印旋转角度、水印大小
         */
        //旋转角度
        Dispatch.put(shapeRange, "Rotation", new Variant(0));
        //调整大小时保持其长宽比例不变
        Dispatch.put(shapeRange, "LockAspectRatio", Boolean.TRUE);
        //高
        Dispatch.put(shapeRange, "Height", new Variant(1000));
        //宽
        Dispatch.put(shapeRange, "Width", new Variant(800));
        Dispatch.put(shapeRange, "Left", new Variant(16));
        Dispatch.put(shapeRange, "Top", new Variant(27));

        //0表示恢复视图;
        Dispatch.put(view, "SeekView", new Variant(0));
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
