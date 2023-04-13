package com.iscas.common.aspose.tools;

import com.aspose.words.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.StreamSupport;

/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/7/11 13:29
 * @since jdk11
 */
@SuppressWarnings({"rawtypes", "unused"})
public class WordOperateUtils {

    static {
        LicenseUtils.initLicense();
    }

    private WordOperateUtils() {
    }

    /**
     * 设置书签值
     *
     * @param is   文档输入流
     * @param os   文档输出流
     * @param saveFormat 文件输出格式，{@link SaveFormat#DOC} 或 {@link SaveFormat#DOCX}
     * @param name 书签名称
     * @param val  书签值
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static void setBookmarkVal(InputStream is, OutputStream os, int saveFormat, String name, String val) throws Exception {
        Document doc = new Document(is);
        BookmarkCollection bookmarkCollection = doc.getRange().getBookmarks();
        for (Bookmark bookmark : bookmarkCollection) {
            if (Objects.equals(bookmark.getName(), name)) {
                bookmark.setText(val);
                break;
            }
        }
        doc.save(os, saveFormat);
    }

    /**
     * 设置书签值
     *
     * @param doc   文档
     * @param name 书签名称
     * @param val  书签值
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static void setBookmarkVal(Document doc, String name, String val) throws Exception {
        BookmarkCollection bookmarkCollection = doc.getRange().getBookmarks();
        for (Bookmark bookmark : bookmarkCollection) {
            if (Objects.equals(bookmark.getName(), name)) {
                bookmark.setText(val);
                return;
            }
        }
        throw new Exception("未找到书签:" + name);
    }

    /**
     * 根据书签名获取书签
     *
     * @param is   文档输入流
     * @param name 书签名称
     * @return java.util.List<com.aspose.words.Bookmark>
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static Bookmark getBookmark(InputStream is, String name) throws Exception {
        return getBookMarks(is).stream().filter(b -> Objects.equals(name, b.getName())).findFirst().orElse(null);
    }

    /**
     * 根据书签名获取书签
     *
     * @param doc   文档
     * @param name 书签名称
     * @return java.util.List<com.aspose.words.Bookmark>
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static Bookmark getBookmark(Document doc, String name) throws Exception {
        return getBookMarks(doc).stream().filter(b -> Objects.equals(name, b.getName())).findFirst().orElse(null);
    }

    /**
     * 获取书签
     *
     * @param is 文档输入流
     * @return java.util.List<com.aspose.words.Bookmark>
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static java.util.List<Bookmark> getBookMarks(InputStream is) throws Exception {
        return getBookMarks(new Document(is));
    }

    /**
     * 获取书签
     *
     * @param doc 文档
     * @return java.util.List<com.aspose.words.Bookmark>
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static java.util.List<Bookmark> getBookMarks(Document doc) throws Exception {
        BookmarkCollection bookmarkCollection = doc.getRange().getBookmarks();
        java.util.List<Bookmark> bookmarks = new ArrayList<>();
        return StreamSupport.stream(bookmarkCollection.spliterator(), false).toList();
    }


    /**
     * 清洁文档，将所有的批注和修订去除
     *
     * @param is         文档输入
     * @param os         文档输出
     * @param saveFormat 文件输出格式，{@link SaveFormat#DOC} 或 {@link SaveFormat#DOCX}
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static void cleanWord(InputStream is, OutputStream os, int saveFormat) throws Exception {
        Document doc = new Document(is);
        // 获取批注
        NodeCollection childNodes = doc.getChildNodes(NodeType.COMMENT, true);
        childNodes.clear();
        // 接受所有修订
        doc.acceptAllRevisions();
        doc.save(os, saveFormat);
    }

    /**
     * 拼接word，如果bookmark为空，从最后拼接，如果bookmark不为空，从此书签位置拼接
     *
     * @param mainIs     主文件输入流
     * @param addIs      拼接的文件输入流
     * @param os         文件输出的流
     * @param saveFormat 文件输出格式，{@link SaveFormat#DOC} 或 {@link SaveFormat#DOCX}
     * @param isPortrait 是否纵向纸张
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public static void appendWord(InputStream mainIs, InputStream addIs, OutputStream os, int saveFormat, boolean isPortrait) throws Exception {
        appendWord(mainIs, addIs, os, saveFormat, isPortrait, null);
    }

    /**
     * 拼接word，如果bookmark为空，从最后拼接，如果bookmark不为空，从此书签位置拼接
     *
     * @param mainIs     主文件输入流
     * @param addIs      拼接的文件输入流
     * @param os         文件输出的流
     * @param saveFormat 文件输出格式，{@link SaveFormat#DOC} 或 {@link SaveFormat#DOCX}
     * @param isPortrait 是否纵向纸张
     * @param bookmark   从书签位置拼接
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public static void appendWord(InputStream mainIs, InputStream addIs, OutputStream os, int saveFormat, boolean isPortrait, String bookmark) throws Exception {
        Document mainDoc = new Document(mainIs);
        Document addDoc = new Document(addIs);
        appendWord(mainDoc, addDoc, isPortrait, bookmark);
        mainDoc.save(os, saveFormat);
    }

    /**
     * 拼接word，如果bookmark为空，从最后拼接，如果bookmark不为空，从此书签位置拼接
     *
     * @param mainIs     主文件输入流
     * @param addIs      拼接的文件输入流
     * @param os         文件输出的流
     * @param saveFormat 文件输出格式，{@link SaveFormat#DOC} 或 {@link SaveFormat#DOCX}
     * @param isPortrait 是否纵向纸张
     * @param bookmark   从书签位置拼接
     * @throws Exception 异常
     * @date 2022/11/29
     * @since jdk11
     */
    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public static void appendPdf(InputStream mainIs, InputStream addIs, OutputStream os, int saveFormat, boolean isPortrait, String bookmark) throws Exception {
        Document mainDoc = new Document(mainIs);
        com.aspose.pdf.Document addDoc = new com.aspose.pdf.Document(addIs);
        appendPdf(mainDoc, addDoc, isPortrait, bookmark);
        mainDoc.save(os, saveFormat);
    }

    /**
     * 拼接word，从文档最后拼接
     *
     * @param mainDoc    主文件
     * @param addDoc     拼接的文件
     * @param isPortrait 是否纵向纸张
     * @return com.aspose.words.Document
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    public static Document appendWord(Document mainDoc, Document addDoc, boolean isPortrait) throws Exception {
        return appendWord(mainDoc, addDoc, isPortrait, null);
    }

    /**
     * 拼接word，如果bookmark为空，从最后拼接，如果bookmark不为空，从此书签位置拼接
     *
     * @param mainDoc    主文件
     * @param addDoc     拼接的文件
     * @param isPortrait 是否纵向纸张
     * @param bookmark   从书签位置拼接
     * @return com.aspose.words.Document
     * @throws Exception 异常
     * @date 2022/7/11
     * @since jdk11
     */
    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public static Document appendWord(Document mainDoc, Document addDoc, boolean isPortrait, String bookmark) throws Exception {
        long old = System.currentTimeMillis();
        DocumentBuilder builder = new DocumentBuilder(mainDoc);
        if (bookmark != null && bookmark.length() > 0) {
            //获取到书签
            BookmarkCollection bms = mainDoc.getRange().getBookmarks();
            Bookmark bm = bms.get(bookmark);
            if (bm != null) {
                builder.moveToBookmark(bookmark, true, false);
            }
        } else {
            builder.moveToDocumentEnd();
        }
        //            builder.writeln();
        builder.getPageSetup().setPaperSize(PaperSize.A4);
        Node insertAfterNode = builder.getCurrentParagraph().getPreviousSibling();
        insertDocumentAfterNode(insertAfterNode, mainDoc, addDoc);
        if (isPortrait) {
            // 纵向纸张
            builder.getPageSetup().setOrientation(Orientation.PORTRAIT);
        } else {
            // 横向纸张
            builder.getPageSetup().setOrientation(Orientation.LANDSCAPE);
        }
//        builder.insertBreak(BreakType.SECTION_BREAK_NEW_PAGE);
        long now = System.currentTimeMillis();
        System.out.println("拼接成功，共耗时：" + (now - old) + "毫秒");
        return mainDoc;
    }

    /**
     * 拼接多个word，在每个尾部另起一页按顺序拼接
     * @since jdk1.8
     * @date 2022/12/28
     * @param docs 待拼接的Document
     * @return com.aspose.words.Document
     */
    public static Document appendWordAuto(Document... docs) throws Exception {
        Document resDoc = new Document();
        resDoc.removeAllChildren();
        for (Document doc : docs) {
            resDoc.appendDocument(doc, ImportFormatMode.USE_DESTINATION_STYLES);
        }
        return resDoc;
    }

    /**
     * 拼接pdf，如果bookmark为空，从最后拼接，如果bookmark不为空，从此书签位置拼接
     *
     * @param mainDoc    主文件
     * @param addDoc     拼接的文件
     * @param isPortrait 是否纵向纸张
     * @param bookmark   从书签位置拼接
     * @return com.aspose.words.Document
     * @throws Exception 异常
     * @date 2022/11/29
     * @since jdk11
     */
    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public static Document appendPdf(Document mainDoc, com.aspose.pdf.Document addDoc, boolean isPortrait, String bookmark) throws Exception {
        long old = System.currentTimeMillis();
        DocumentBuilder builder = new DocumentBuilder(mainDoc);
        if (bookmark != null && bookmark.length() > 0) {
            //获取到书签
            BookmarkCollection bms = mainDoc.getRange().getBookmarks();
            Bookmark bm = bms.get(bookmark);
            if (bm != null) {
                builder.moveToBookmark(bookmark, true, false);
            }
        } else {
            builder.moveToDocumentEnd();
        }

        // pdf转为word
        File word = Files.createTempFile("word", ".docx").toFile();
        try (FileOutputStream os = new FileOutputStream(word)) {
            addDoc.save(os, com.aspose.pdf.SaveFormat.DocX);
            os.flush();
        }
        //            builder.writeln();
        builder.getPageSetup().setPaperSize(PaperSize.A4);
        Node insertAfterNode = builder.getCurrentParagraph().getPreviousSibling();
        insertDocumentAfterNode(insertAfterNode, mainDoc, new Document(word.getAbsolutePath()));
        if (isPortrait) {
            // 纵向纸张
            builder.getPageSetup().setOrientation(Orientation.PORTRAIT);
        } else {
            // 横向纸张
            builder.getPageSetup().setOrientation(Orientation.LANDSCAPE);
        }
        builder.insertBreak(BreakType.SECTION_BREAK_NEW_PAGE);
        word.delete();
        long now = System.currentTimeMillis();
        System.out.println("拼接成功，共耗时：" + (now - old) + "毫秒");
        return mainDoc;
    }


    @SuppressWarnings("rawtypes")
    private static void insertDocumentAfterNode(Node insertAfterNode, Document mainDoc, Document srcDoc) throws Exception {
        if (insertAfterNode.getNodeType() != 8 & insertAfterNode.getNodeType() != 5) {
            throw new Exception("The destination node should be either a paragraph or table.");
        } else {
            CompositeNode dstStory = insertAfterNode.getParentNode();
            while (null != srcDoc.getLastSection().getBody().getLastParagraph()
                    && !srcDoc.getLastSection().getBody().getLastParagraph().hasChildNodes()) {
                srcDoc.getLastSection().getBody().getLastParagraph().remove();
            }
            NodeImporter importer = new NodeImporter(srcDoc, mainDoc, 1);
            int sectCount = srcDoc.getSections().getCount();
            for (int sectIndex = 0; sectIndex < sectCount; ++sectIndex) {
                Section srcSection = srcDoc.getSections().get(sectIndex);
                int nodeCount = srcSection.getBody().getChildNodes().getCount();
                for (int nodeIndex = 0; nodeIndex < nodeCount; ++nodeIndex) {
                    Node srcNode = srcSection.getBody().getChildNodes().get(nodeIndex);
                    Node newNode = importer.importNode(srcNode, true);
                    dstStory.insertAfter(newNode, insertAfterNode);
                    insertAfterNode = newNode;
                }
            }
        }
    }


    /**
     * 复制列
     *
     * @param sectionIndex section索引
     * @param tableIndex   表的索引
     * @param srcFilePath  原始模板路径
     * @param dstFilePath  目标路径
     * @param rowOffset    行下标
     * @param copyRowCount 行数
     * @param colOffset    需要处理的列开始下标
     * @param colEnd       需要处理的列结束下标
     * @param colCount     需处理的几个列处理后变为的列数
     * @date 2022/11/29
     * @since jdk1.8
     */
    public static void copyCell(int sectionIndex, int tableIndex, String srcFilePath, String dstFilePath, int rowOffset,
                                int copyRowCount, int colOffset, int colEnd, int colCount) throws Exception {
        com.aspose.words.Document document = new com.aspose.words.Document(srcFilePath);
        NodeCollection childNodes = document.getChildNodes(NodeType.TABLE, true);
        com.aspose.words.Table tb = (com.aspose.words.Table) childNodes.get(tableIndex);
        for (int i = rowOffset; i < copyRowCount + rowOffset; i++) {
            CellCollection cells = tb.getRows().get(i).getCells();
            Cell start = cells.get(colOffset);
            Cell end = cells.get(colEnd);
            mergeCells(start, end);
        }
        document.save(dstFilePath, SaveFormat.DOCX);
        com.aspose.words.Document document2 = new com.aspose.words.Document(dstFilePath);
        NodeCollection childNodes2 = document2.getChildNodes(NodeType.TABLE, true);
        com.aspose.words.Table tb2 = (com.aspose.words.Table) childNodes2.get(tableIndex);
        double subWidth = 0;
        for (int i = rowOffset; i < copyRowCount + rowOffset; i++) {
            RowCollection rows = tb2.getRows();
            Row row = rows.get(i);
            Cell offsetCell = row.getCells().get(colOffset);
            double width = offsetCell.getCellFormat().getPreferredWidth().getValue();
            subWidth = width / colCount;
            row.removeChild(offsetCell);
            for (int j = 0; j < colCount; j++) {
                Cell cell = new Cell(document2);
                cell.getCellFormat().clearFormatting();
                cell.getCellFormat().setWrapText(true);
                cell.getCellFormat().setHorizontalMerge(CellMerge.NONE);
                cell.getCellFormat().setVerticalMerge(CellMerge.NONE);
                cell.getCellFormat().setFitText(true);
                cell.getCellFormat().setWidth(subWidth);
                row.getCells().insert(colOffset + j, cell);
            }
        }
        document2.save(dstFilePath, SaveFormat.DOCX);
    }

    public static void mergeCells(Cell startCell, Cell endCell) {
        com.aspose.words.Table parentTable = startCell.getParentRow().getParentTable();

        // Find the row and cell indices for the start and end cell.
        Point startCellPos = new Point(startCell.getParentRow().indexOf(startCell), parentTable.indexOf(startCell.getParentRow()));
        Point endCellPos = new Point(endCell.getParentRow().indexOf(endCell), parentTable.indexOf(endCell.getParentRow()));
        // Create the range of cells to be merged based off these indices. Inverse each index if the end cell if before the start cell.
        Rectangle mergeRange = new Rectangle(Math.min(startCellPos.x, endCellPos.x), Math.min(startCellPos.y, endCellPos.y), Math.abs(endCellPos.x - startCellPos.x) + 1,
                Math.abs(endCellPos.y - startCellPos.y) + 1);

        for (Row row : parentTable.getRows()) {
            for (Cell cell : row.getCells()) {
                Point currentPos = new Point(row.indexOf(cell), parentTable.indexOf(row));

                // Check if the current cell is inside our merge range then merge it.
                if (mergeRange.contains(currentPos)) {
                    if (currentPos.x == mergeRange.x) {
                        cell.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
                    } else {
                        cell.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
                    }

                    if (currentPos.y == mergeRange.y) {
                        cell.getCellFormat().setVerticalMerge(CellMerge.FIRST);
                    } else {
                        cell.getCellFormat().setVerticalMerge(CellMerge.PREVIOUS);
                    }
                }
            }
        }
    }

    /**
     * 生成目录
     * */
    public static void insertCatalogue(Document doc) throws Exception {
        DocumentBuilder builder = new DocumentBuilder(doc);
        doc.getFirstSection().getBody().prependChild(new Paragraph(doc));
        builder.moveToDocumentStart();
        //设置目录的格式
        //“目录”两个字居中显示、加粗、搜宋体
        builder.getCurrentParagraph().getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        builder.setBold(true);
        builder.getFont().setName("宋体");
        builder.writeln("目录");
        //清清除所有样式设置
        builder.getParagraphFormat().clearFormatting();

        //目录居左
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
        //插入目录，这是固定的
        builder.insertTableOfContents("\\o \"1-3\" \\h \\z \\u");
        builder.insertBreak(BreakType.PAGE_BREAK);
        doc.updateFields();// 更新域

        //TOC_1：一级目录 TOC_2：二级目录 。。。
        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_1).getFont().setSize(12);
        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_1).getFont().setBold(true);
        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_1).getFont().setName("微软雅黑");
        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_1).getParagraphFormat().setLineSpacing(12);

        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_2).getFont().setSize(10.5);
        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_2).getFont().setName("微软雅黑");
        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_2).getParagraphFormat().setLineSpacing(10.5);

        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_3).getFont().setSize(9);
        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_3).getFont().setName("微软雅黑");
        doc.getStyles().getByStyleIdentifier(StyleIdentifier.TOC_3).getParagraphFormat().setLineSpacing(9);

        // 目录字体样式变更后再次更新域
        doc.updateFields();
    }
}
