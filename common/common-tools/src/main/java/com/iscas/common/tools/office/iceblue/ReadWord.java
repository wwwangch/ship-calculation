package com.iscas.common.tools.office.iceblue;

import com.spire.doc.Document;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/25 16:55
 * @since jdk1.8
 */
@SuppressWarnings({"CommentedOutCode", "AlibabaRemoveCommentedCode"})
public class ReadWord {
    public static String readWord() {
        //加载Word文档
        Document document = new Document();
//        document.loadFromFile("D:\\文档资料\\登录模块.docx");
        document.loadFromFile("/opt/soft/应用软件与系统集成0623.doc");
        return document.getText();
//        //获取文档中的文本保存为String
//        String text=document.getText();
//        System.out.println(text);

        //遍历
//        DocumentObjectCollection childObjects = document.getChildObjects();
//        for (int i = 0; i < childObjects.getCount(); i++) {
//            DocumentObject documentObject = childObjects.get(i);
//            Section section = (Section) documentObject;
//            DocumentObjectCollection childObjects1 = section.getChildObjects();
//            for (int j = 0; j < childObjects1.getCount(); j++) {
//                DocumentObject documentObject1 = childObjects1.get(j);
//                DocumentObjectCollection childObjects2 = documentObject1.getChildObjects();
//                if (childObjects2 != null) {
//                    for (Object o : childObjects2) {
//                        if (o instanceof Paragraph) {
//                            Paragraph paragraph = (Paragraph) o;
//                            ParagraphStyle style = paragraph.getStyle();
//                            boolean title = false;
//                            if (style != null) {
//                                String name = style.getName();
//                                if (name.startsWith("Title")) {
//                                    System.out.println("<<<<<<<<<<" + name + "::::" + paragraph.getListText() + paragraph.getText());
//                                    title = true;
//                                } else if (name.startsWith("Heading ")) {
//                                    //标题
//                                    System.out.println(">>>>>>>" + name + "::::" + paragraph.getListText() + paragraph.getText());
//                                    title = true;
//                                } else if (name.startsWith("Subtitle")) {
//                                    //子标题
//                                    System.out.println("<><><><><><>" + name + "::::" + paragraph.getListText() + paragraph.getText());
//                                    title = true;
//                                }
//                            }
//                            if (!title) {
//                                //正文
//                                System.out.println("||||||||" + paragraph.getListText() + paragraph.getText());
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

}
