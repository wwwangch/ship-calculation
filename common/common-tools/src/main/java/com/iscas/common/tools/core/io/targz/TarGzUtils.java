package com.iscas.common.tools.core.io.targz;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.zip.GZIPOutputStream;

/**
 * tar.gz文件的操作
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2022/12/27 11:21
 * @since jdk1.8
 */
public class TarGzUtils {
    private TarGzUtils() {
    }

    /**
     * tar.gz文件解压
     *
     * @param sourceTarGzFile 原始压缩文件
     * @param targetDir       解压后的文件夹
     * @date 2022/12/27
     * @since jdk1.8
     */
    public static void uncompress(File sourceTarGzFile, File targetDir) throws IOException {
        TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(new GzipCompressorInputStream(Files.newInputStream(sourceTarGzFile.toPath())));
        TarArchiveEntry entry;
        while ((entry = tarArchiveInputStream.getNextTarEntry()) != null) {
            if (entry.isDirectory()) {
                File dir = new File(targetDir, entry.getName());
                // 创建文件夹
                dir.mkdirs();
            } else {
                File file = new File(targetDir, entry.getName());
                // 拷贝文件
                try (OutputStream outputStream = Files.newOutputStream(file.toPath())) {
                    IOUtils.copy(tarArchiveInputStream, outputStream);
                }
            }
        }
    }

    /**
     * 将一个目录压缩为tar.gz
     *
     * @param sourceDir  原始资源目录
     * @param targetFile 压缩后的目标文件
     * @return void
     * @date 2022/12/27
     * @since jdk1.8
     */
    public static void compress(File sourceDir, File targetFile) throws IOException {
        try (
                OutputStream os = Files.newOutputStream(targetFile.toPath());
                BufferedOutputStream bos = new BufferedOutputStream(os);
                GZIPOutputStream gzipOs = new GZIPOutputStream(bos);
                TarArchiveOutputStream tarOs = new TarArchiveOutputStream(gzipOs);
        ) {
            // 使文件名支持超过 100 个字节
            tarOs.setLongFileMode(TarArchiveOutputStream.LONGFILE_POSIX);
            addToTarget(sourceDir, "", tarOs);
        }
    }

    private static void addToTarget(File source, String parentPath, TarArchiveOutputStream tarOs) throws IOException {
        String fileName;
        if ("".equals(parentPath)) {
            fileName = source.getName();
        } else {
            fileName = parentPath + File.separator + source.getName();
        }
        tarOs.putArchiveEntry(new TarArchiveEntry(source, fileName));
        if (source.isDirectory()) {
            // 如果是文件夹，直接关闭
            tarOs.closeArchiveEntry();
            // 递归
            for (File file : source.listFiles()) {
                addToTarget(file, fileName, tarOs);
            }
        } else if (source.isFile()) {
            // 如果是文件，写入文件
            try (
                    InputStream is = Files.newInputStream(source.toPath());
                    BufferedInputStream bis = new BufferedInputStream(is);
            ) {
                IOUtils.copy(bis, tarOs);
                tarOs.closeArchiveEntry();
            }
        }
    }

}
