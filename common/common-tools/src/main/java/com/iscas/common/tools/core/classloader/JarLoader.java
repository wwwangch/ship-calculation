package com.iscas.common.tools.core.classloader;

import org.apache.commons.collections4.MapUtils;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 提供Jar隔离的加载机制，会把传入的路径、及其子路径、以及路径中的jar文件加入到class path。
 * 破坏双亲委派机制，改为逆向
 *
 * @author admin*/
@SuppressWarnings({"rawtypes", "unused"})
public class JarLoader extends URLClassLoader {
    @SuppressWarnings("AlibabaThreadLocalShouldRemove")
    private static final ThreadLocal<URL[]> THREAD_LOCAL = new ThreadLocal<>();
    @SuppressWarnings("FieldMayBeFinal")
    private URL[] allUrl;
    @SuppressWarnings("FieldMayBeFinal")
    private boolean useCache;
    @SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal", "unused"})
    private String[] paths;
    @SuppressWarnings("FieldMayBeFinal")
    private String pathStr;
    private String dbType;
    /**缓存当前类加载器加载的类*/
    private final Map<String, Class<?>>  jarLoaderClasses = new ConcurrentHashMap<>();

    /**
     * 缓存对应类型的加载的类
     * */
//    public static Map<String, Map<String, Class>> typeJarLoaderClasses = new ConcurrentHashMap<>();

    /**缓存对象*/
    private static final Map<String, Map<String, byte[]>> CLASS_BYTES = new ConcurrentHashMap<>();


    public JarLoader(String[] paths, boolean useCache, String type) {
        this(paths, JarLoader.class.getClassLoader(), useCache);
        this.dbType = type;
    }

    public JarLoader(String[] paths, ClassLoader parent, boolean useCache) {
        super(getUrls(paths), parent);
        //暂时先这样
        allUrl = THREAD_LOCAL.get();
        this.useCache = useCache;
        this.paths = paths;
        pathStr = String.join(";", paths);
    }

    public JarLoader(String[] paths) {
        this(paths, JarLoader.class.getClassLoader(), false);
    }

    public JarLoader(String[] paths, ClassLoader parent) {
        this (paths, parent, false);
    }

    /**
     * 清除某个路径下的缓存，
     * 可适用于不想重启服务，但更新了外部插件的jar包的情况下调用
     * */
    public static void clearCache(String[] paths) {
        String pathStr = String.join(";", paths);
        CLASS_BYTES.remove(pathStr);
    }


    /**
     * 加载class文件，方便加载的方法
     * */
    public static Class<?> outerLoadClass(String name) throws ClassNotFoundException {
        return Thread.currentThread().getContextClassLoader().loadClass(name);
    }

    private static URL[] getUrls(String[] paths) {
        if (null == paths || 0 == paths.length) {
            throw new RuntimeException("jar包路径不能为空.");
        }

        List<File> jarFiles = new ArrayList<>();
        List<String> dirFiles = new ArrayList<>();
        for (String path : paths) {
            File file = new File(path);
            if (file.isFile()) {
                jarFiles.add(file);
            } else {
                dirFiles.add(path);
            }
        }

        List<String> dirs = new ArrayList<>();
        for (String path : dirFiles) {
            dirs.add(path);
            JarLoader.collectDirs(path, dirs);
        }

        List<URL> urls = new ArrayList<>();
        for (String path : dirs) {
            urls.addAll(doGetUrls(path));
        }

        for (File jarFile : jarFiles) {
            try {
                URL url = jarFile.toURI().toURL();
                urls.add(url);
            } catch (Exception e) {
                throw new RuntimeException("系统加载jar包出错", e);
            }

        }

        URL[] urls1 = urls.toArray(new URL[0]);
        THREAD_LOCAL.set(urls1);
        return urls1;
    }

    private static void collectDirs(String path, List<String> collector) {
        if (null == path || "".equalsIgnoreCase(path)) {
            return;
        }

        File current = new File(path);
        if (!current.exists() || !current.isDirectory()) {
            return;
        }

        for (File child : Objects.requireNonNull(current.listFiles())) {
            if (!child.isDirectory()) {
                continue;
            }

            collector.add(child.getAbsolutePath());
            collectDirs(child.getAbsolutePath(), collector);
        }
    }

    private static List<URL> doGetUrls(final String path) {
        if (null == path || "".equalsIgnoreCase(path)) {
            throw new RuntimeException("jar包路径不能为空.");
        }
        File jarPath = new File(path);

        if (!jarPath.exists() || !jarPath.isDirectory()) {
            throw new RuntimeException("jar包路径必须存在且为目录.");
        }

        /* set filter */
        FileFilter jarFilter = pathname -> pathname.getName().endsWith(".jar");

        /* iterate all jar */
        File[] allJars = new File(path).listFiles(jarFilter);
        assert allJars != null;
        List<URL> jarUrls = new ArrayList<>(allJars.length);

        for (File allJar : allJars) {
            try {
                jarUrls.add(allJar.toURI().toURL());
            } catch (Exception e) {
                throw new RuntimeException("系统加载jar包出错", e);
            }
        }
        return jarUrls;
    }
    /**破坏双亲委派模型,采用逆向双亲委派*/
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        //读取缓存
        Class<?> aClass = jarLoaderClasses.get(name);
        if (aClass == null) {
            aClass = findClass(name);
        }

        if (aClass == null) {
            return super.loadClass(name);
        } else {
            // 放入缓存
            jarLoaderClasses.put(name, aClass);
            // 放入带数据库类型的缓存
//            typeJarLoaderClasses.computeIfAbsent(dbType, key -> new ConcurrentHashMap<>(32)).put(name, aClass);
        }
        return aClass;
    }

    @Override
    public Class<?> findClass(String name) {
        //如果开启了缓存，查看class文件对应字节数组有没有缓存起来，如果有缓存，直接使用缓存的字节数组
        if (useCache) {
            synchronized (name.intern()) {
                Map<String, byte[]> cacheMap = CLASS_BYTES.get(pathStr);
                if (MapUtils.isNotEmpty(cacheMap)) {
                    byte[] bytes = cacheMap.get(name);
                    if (bytes != null) {
                       Class<?> aClassx = this.defineClass(name, bytes, 0, bytes.length);
                       if (aClassx != null) {
                           System.out.println("读取到缓存.....");
                           return aClassx;
                       }
                    }
                }
            }
        }

        Class<?> aClass = null;
        if (allUrl != null) {
            String classPath = name.replace(".", "/");
            classPath = classPath.concat(".class");

            for (URL url : allUrl) {
                byte[] data;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream is = null;
                try {
                    File file = new File(url.toURI());
                    if (file.exists()) {
                        @SuppressWarnings("resource") JarFile jarFile = new JarFile(file);
                        JarEntry jarEntry = jarFile.getJarEntry(classPath);
                        if (jarEntry != null) {
                            is = jarFile.getInputStream(jarEntry);
                            int c;
                            byte[] buff = new byte[4096];
                            while (-1 != (c = is.read(buff))) {
                                baos.write(buff, 0, c);
                            }
                            data = baos.toByteArray();
                            aClass = this.defineClass(name, data, 0, data.length);

                            synchronized (name.intern()) {
                                if (useCache && aClass != null) {
                                    System.out.println("写入缓存---");
                                    Map<String, byte[]> classByteMap = CLASS_BYTES.get(pathStr);
                                    if (MapUtils.isEmpty(classByteMap)) {
                                        classByteMap = new ConcurrentHashMap<>(2);
                                        CLASS_BYTES.put(pathStr, classByteMap);
                                    }
                                    CLASS_BYTES.get(pathStr).put(name, data);
                                }
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        baos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return aClass;
    }

}
