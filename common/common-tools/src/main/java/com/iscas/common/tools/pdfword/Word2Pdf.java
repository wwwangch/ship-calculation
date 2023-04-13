package com.iscas.common.tools.pdfword;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import java.io.File;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/8/28 14:54
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class Word2Pdf {
    /**PDF 格式*/
    private static final int WD_FORMAT_PDF = 17;
    public static void wordToPdf(String startFile, String overFile){

        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            Dispatch docs = app.getProperty("Documents").toDispatch();

            doc = Dispatch.call(docs,  "Open" , startFile).toDispatch();
            File tofile = new File(overFile);
            if (tofile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                tofile.delete();
            }
            Dispatch.call(doc,"SaveAs", overFile, WD_FORMAT_PDF);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert doc != null;
            Dispatch.call(doc,"Close",false);
            app.invoke("Quit", new Variant[] {});
        }
        //结束后关闭进程
        ComThread.Release();
    }
}
