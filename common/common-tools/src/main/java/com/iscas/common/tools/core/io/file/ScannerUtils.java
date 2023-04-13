package com.iscas.common.tools.core.io.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 扫描包的工具类
 * @author zhuquanwen
 * @date 2018/4/25 17:30
 **/
@SuppressWarnings("unused")
@Slf4j
public class ScannerUtils {

    @SuppressWarnings({"unused", "AlibabaLowerCamelCaseVariableNaming"})
    public static Set<Class<?>> getClasses(String pack) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageDirName = pack.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 循环迭代下去
        while (dirs.hasMoreElements()) {
            // 获取下一个元素
            URL url = dirs.nextElement();
            // 得到协议的名称
            String protocol = url.getProtocol();
            // 如果是以文件的形式保存在服务器上
            if ("file".equals(protocol)) {
                if (log.isDebugEnabled()) {
                    log.debug("============file类型的扫描");
                }
                // 获取包的物理路径
                String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);
                // 以文件的方式扫描整个包下的文件 并添加到集合中
                findAndAddClassesInPackageByFile(pack, filePath, recursive, classes);
            } else if ("jar".equals(protocol)) {
                String packageName1 = "";
                // 如果是jar包文件
                // 定义一个JarFile
                if (log.isDebugEnabled()) {
                    log.debug("=============jar类型的扫描");
                }
                JarFile jar;
                // 获取jar
                try {
                    jar = ((JarURLConnection) url.openConnection()).getJarFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // 从此jar包 得到一个枚举类
                Enumeration<JarEntry> entries = jar.entries();
                // 同样的进行循环迭代
                while (entries.hasMoreElements()) {
                    // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    // 如果是以/开头的
                    if (name.charAt(0) == '/') {
                        // 获取后面的字符串
                        name = name.substring(1);
                    }
                    // 如果前半部分和定义的包名相同
                    if (name.startsWith(packageDirName)) {
                        int idx = name.lastIndexOf('/');
                        // 如果以"/"结尾 是一个包
                        if (idx != -1) {
                            // 获取包名 把"/"替换成"."
                            packageName1 = name.substring(0, idx).replace('/', '.');
                        }
                        // 如果可以迭代下去 并且是一个包
                        // 如果是一个.class文件 而且不是目录
                        if (name.endsWith(".class") && !entry.isDirectory() && !name.contains("$")) {
                            // 去掉后面的".class" 获取真正的类名
                            String className = name.substring(packageName1.length() + 1, name.length() - 6);

                            addClasses(packageName1 + '.' + className, classes);
                        }
                    }
                }

            }
        }
        return classes;
    }

    public static void findAndAddClassesInPackageByFile(
            String packageName,
            String packagePath,
            final boolean recursive,
            Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
        File[] dirfiles = dir.listFiles(file -> (recursive && file.isDirectory()) || (file.getName().endsWith(".class")));
        // 循环所有文件
        assert dirfiles != null;
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);

                addClasses(packageName + '.' + className, classes);
            }
        }
    }

    public static String getPackageRealPath(String packageName) {
        String packageDirName = packageName.replace('.', '/');
        return Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(packageDirName)).getFile();
    }

    private static void addClasses(String fullName, Set<Class<?>> classes) {
        // 添加到集合中去
        // classes.add(Class.forName(packageName + '.' + className));
        // 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
        Class<?> aClass = null;
        try {
            // 添加到classes
            aClass = Thread.currentThread().getContextClassLoader().loadClass(fullName);
        } catch (Exception e) {
            log.warn("加载类出错", e);
        }
        //添加当前类和内部类
        if (aClass != null) {
            Class<?>[] innerClasses = aClass.getClasses();
            classes.add(aClass);
            if (innerClasses != null) {
                classes.addAll(Arrays.asList(innerClasses));
            }
        }
    }

}
