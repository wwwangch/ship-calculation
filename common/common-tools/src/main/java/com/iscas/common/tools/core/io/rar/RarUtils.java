package com.iscas.common.tools.core.io.rar;

import com.github.junrar.Archive;
import com.github.junrar.UnrarCallback;
import com.github.junrar.rarfile.FileHeader;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


/**
 * rar工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/3/14 15:11
 * @since jdk1.8
 */
public class RarUtils {

    /**
     * @param rarFileName rar file name
     * @param outFilePath output file path
     * @param callback    callback
     * @throws Exception 异常
     */
    public static void unrar(String rarFileName, String outFilePath, UnrarCallback callback) throws Exception {
        Archive archive = new Archive(new File(rarFileName), callback);
        if (archive.isEncrypted()) {
            throw new Exception(rarFileName + " IS ENCRYPTED!");
        }
        List<FileHeader> files = archive.getFileHeaders();
        for (FileHeader fh : files) {
            if (fh.isEncrypted()) {
                throw new Exception(rarFileName + " IS ENCRYPTED!");
            }
            String fileName = fh.getFileNameString();
            if (fileName != null && fileName.trim().length() > 0) {
                String saveFileName = outFilePath + File.separator + fileName;
                File saveFile = new File(saveFileName);
                File parent = saveFile.getParentFile();
                if (!parent.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    parent.mkdirs();
                }
                if (!saveFile.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    saveFile.createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(saveFile);
                try {
                    archive.extractFile(fh, fos);
                } finally {
                    try {
                        fos.flush();
                        fos.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }
}
