package com.iscas.common.tools.core.io.file;


import com.iscas.common.tools.TestConstants;
import lombok.Cleanup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * 图片生成缩略图工具类测试
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/14 18:10
 * @since jdk1.8
 */
public class ThumbnailPicUtilsTests {
    private static byte[] TEST_PIC_BYTES = null;
    /**
     * 准备一张图片
     * */
    @BeforeAll
    public static void init1() {
        TEST_PIC_BYTES = Base64.getDecoder().decode(TestConstants.TEST_PIC_BASE64);
    }
    @Test
    public void thumbnailTest() throws Exception {
        System.out.println("--------缩略图工具ThumbnailPicUtils#transform(String originalFile, String thumbnailFile, int thumbWidth, int thumbHeight) begin---------");
        File ori = File.createTempFile("test", ".jpg");
        ori.deleteOnExit();
        @Cleanup OutputStream os = new FileOutputStream(ori);
        os.write(TEST_PIC_BYTES);
        System.out.println("原始大小:" + ori.length());
        File target = File.createTempFile("target", ".jpg");
        target.deleteOnExit();
        ThumbnailPicUtils.transform(ori.getAbsolutePath(), target.getAbsolutePath(), 10,10);
        System.out.println("生成缩略图后代销:" + target.length());
        ori.delete();
        target.delete();
        System.out.println("--------缩略图工具ThumbnailPicUtils#transform(String originalFile, String thumbnailFile, int thumbWidth, int thumbHeight) end---------");
    }
}
