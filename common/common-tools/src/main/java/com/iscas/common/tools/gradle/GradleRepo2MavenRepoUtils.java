package com.iscas.common.tools.gradle;

import cn.hutool.core.io.IoUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>gradle本地仓库转为maven仓库的结构</p>
 * <p>暂时有问题，先打上了过时标签</p>
 *
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/8 20:53
 * @since jdk1.8
 */
@Deprecated
@SuppressWarnings("all")
public class GradleRepo2MavenRepoUtils {
    private GradleRepo2MavenRepoUtils() {}

    /**
     * gradle仓库路径
     * */
    public static void gradle2Maven(String gradleDir, String mavenDir) throws IOException {
        File file = new File(gradleDir);
        if (!file.exists()) {
            throw new RuntimeException(String.format("gradle路径:[%s]不存在", gradleDir));
        }
        file = new File(gradleDir, "caches/modules-2/files-2.1");
        if (!file.exists()) {
            throw new RuntimeException(String.format("gradle路径:[%s]不存在", file.getAbsolutePath()));
        }
        File mavenFile = new File(mavenDir);
        if (!mavenFile.exists()) {
            mavenFile.mkdirs();
        }
        String toAppendPath = "";
        convert(file, mavenFile, toAppendPath);
    }

    private static void convert(File file, File mavenFile, String toAppendPath) throws IOException {
        if (file.isDirectory()) {
            //如果是文件夹，递归
            File[] files = file.listFiles();
            if (ArrayUtils.isNotEmpty(files)) {
                for (File file1 : files) {
                    String toAppendPath2 = toAppendPath + "/" + file1.getName();
                    convert(file1, mavenFile, toAppendPath2);
                }
            }
        } else {
            //如果是文件，去掉上一层的随机字符串文件夹
            File parentFile = file.getParentFile();
            String name = parentFile.getName();

            String mavenPath = StringUtils.substringBeforeLast(toAppendPath, "/" + name);
            String prefix = StringUtils.substringBeforeLast(mavenPath, "/");
            String suffix = StringUtils.substringAfterLast(mavenPath, "/");
            prefix = prefix.replace(".", "/");
            mavenPath = prefix + "/" + suffix;
            File distMavenFile = new File(mavenFile, mavenPath);
            if (!distMavenFile.exists()) {
                distMavenFile.mkdirs();
            }
            distMavenFile = new File(distMavenFile, file.getName());
            try (FileInputStream fis = new FileInputStream(file);
                 FileOutputStream fos = new FileOutputStream(distMavenFile);) {
                IoUtil.copy(fis, fos);
            }

        }
    }
}
