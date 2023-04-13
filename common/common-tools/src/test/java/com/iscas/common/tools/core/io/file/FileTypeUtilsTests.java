package com.iscas.common.tools.core.io.file;


import com.iscas.common.tools.TestConstants;
import lombok.Cleanup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * 判断文件的类型
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/8/6 9:11
 * @since jdk1.8
 */
public class FileTypeUtilsTests {
    private static byte[] TEST_PIC_BYTES = null;
    /**
     * 准备一张图片
     * */
    @BeforeAll
    public static void init1() {
        TEST_PIC_BYTES = Base64.getDecoder().decode(TestConstants.TEST_PIC_BASE64);
    }
    @Test
    public void test() throws IOException {

        System.out.println("-------FileTypeUtils#getFileType(String path) begin---------");
        File picFile = File.createTempFile("test", ".jpg");
        picFile.deleteOnExit();
        @Cleanup FileOutputStream fos = new FileOutputStream(picFile);
        fos.write(TEST_PIC_BYTES);
        fos.close();
        FileTypeEnum fileType = FileTypeUtils.getFileType(picFile.getAbsolutePath());
        System.out.println(fileType);
        picFile.delete();
        System.out.println("-------FileTypeUtils#getFileType(String path) end---------");
    }
}
