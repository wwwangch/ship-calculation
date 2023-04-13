package com.iscas.common.tools.core.io.zip;

import cn.hutool.core.io.IoUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/4/30 11:01
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class ZipUtils {
    private ZipUtils() {
    }


    /**
     * 递归压缩方法
     *
     * @param sourceFile       源文件
     * @param os               输出流
     * @param name             压缩后的名称
     * @param keepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception 异常
     */
    public static void compress(File sourceFile, ZipOutputStream os, String name, boolean keepDirStructure) throws Exception {
        if (sourceFile.isFile()) {
            os.putNextEntry(new org.apache.tools.zip.ZipEntry(name));
            FileInputStream is = new FileInputStream(sourceFile);
            IoUtil.copy(is, os);
            is.close();
        } else {
            File[] listFiles = sourceFile.listFiles();

            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (keepDirStructure) {
                    // 空文件夹的处理
                    os.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                }
            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (keepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, os, name + "/" + file.getName(), true);
                    } else {
                        compress(file, os, file.getName(), false);
                    }
                }
            }
        }
    }

    /**
     * 压缩文件或文件夹
     *
     * @param sourcePath          源文件路径
     * @param destinationPath     压缩包存放路径，是否已斜杠结尾均可
     * @param destinationFileName 压缩包名字，带不带后缀名均可
     * @return 处理后的压缩包绝对路径
     */
    @SuppressWarnings({"AlibabaUndefineMagicConstant", "ResultOfMethodCallIgnored"})
    public static String toZip(String sourcePath, String destinationPath, String destinationFileName)
            throws Exception {
        /*参数预处理*/
        if (StringUtils.isEmpty(sourcePath) || StringUtils.isEmpty(destinationPath) || StringUtils.isEmpty(destinationFileName)) {
            throw new Exception("parameter is null or empty");
        }
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            throw new Exception(String.format("file: '%s' not exist", sourcePath));
        }
        String sourceFileName = sourcePath.substring(1 + Math.max(sourcePath.lastIndexOf("/"), sourcePath.lastIndexOf("\\")));
        if (!destinationPath.endsWith("/") && !destinationPath.endsWith("\\")) {
            destinationPath += File.separator;
        }
        if (!destinationFileName.endsWith(".zip")) {
            destinationFileName += ".zip";
        }
        File targetFile = new File(destinationPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        /*压缩*/
        ZipOutputStream zip = null;
        String result;
        try {
            result = destinationPath + destinationFileName;
            zip = new ZipOutputStream(new FileOutputStream(result));
            compress(sourceFile, zip, sourceFileName, true);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            /*确保文件被释放*/
            try {
                if (zip != null) {
                    zip.closeEntry();
                    zip.close();
                }
            } catch (IOException ignored) {
            }
        }

        return result;
    }

    /**
     * 压缩文件或文件夹,压缩后的文件名由源文件决定
     *
     * @param sourcePath      源文件路径
     * @param destinationPath 压缩包存放路径，是否已斜杠结尾均可
     * @return 处理后的压缩包绝对路径
     */
    public static String toZip(String sourcePath, String destinationPath) throws Exception {
        /*参数预处理*/
        if (StringUtils.isEmpty(sourcePath)) {
            throw new Exception("parameter is null or empty");
        }

        String sourceFileName = sourcePath.substring(1 + Math.max(sourcePath.lastIndexOf("/"), sourcePath.lastIndexOf("\\")));
        int index = sourceFileName.indexOf(".");
        String destinationFileName = (index >= 0 ? sourceFileName.substring(0, index) : sourceFileName) + ".zip";
        return toZip(sourcePath, destinationPath, destinationFileName);
    }

    /**
     * zip解压
     *
     * @param srcFile     zip源文件
     * @param destDirPath 解压后的目标文件夹
     * @throws RuntimeException 解压失败会抛出运行时异常
     **/

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    File dir = new File(destDirPath, entry.getName());
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath, entry.getName());
                    //为了不报安全漏洞，做一个判断
                    if (Objects.equals(targetFile.getCanonicalPath(), "/")) {
                        throw new RuntimeException("unzip error, targetPath error");
                    }

                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    try (InputStream is = zipFile.getInputStream(entry);
                         FileOutputStream fos = new FileOutputStream(targetFile)) {
                        is.transferTo(fos);
                    }
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("解压完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static String validateFilenameInDir(String filename, String intendedDirectory) throws IOException {
        File checkFile = new File(filename);
        String canonicalPathToCheck = checkFile.getCanonicalPath();
        File intendedDir = new File(intendedDirectory);
        String canonicalPathToVerify = intendedDir.getCanonicalPath();
        if (canonicalPathToCheck.startsWith(canonicalPathToVerify)) {
            return canonicalPathToCheck;
        } else {
            throw new IllegalStateException("This file is outside the intended extraction directory.");
        }
    }


}
