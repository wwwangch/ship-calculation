package com.iscas.base.biz.util;


import com.iscas.common.tools.core.reflect.ReflectUtils;
import com.iscas.templet.exception.Exceptions;
import lombok.Cleanup;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 代码生成器工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2018/7/25 17:11
 * @since jdk1.8
 */
@SuppressWarnings({"unused", "rawtypes", "unchecked", "ResultOfMethodCallIgnored"})
public class TableHeaderCodeCreatorUtils {
    private TableHeaderCodeCreatorUtils() {
    }

    /**
     * 生成表头信息到JSON文件
     *
     * @param clazzs    实体类集合
     * @param targetDir 目标路 最好写个径绝对路径
     * @throws FileNotFoundException 文件未找到异常
     * @date 2018/7/25
     * @since jdk1.8
     */
    public static void create(List<Class> clazzs, String targetDir) throws FileNotFoundException {

        for (Class clazz : clazzs) {
            create(clazz, targetDir);
        }
    }

    /**
     * 生成表头信息到JSON文件
     *
     * @param clazz     实体类
     * @param targetDir 目标路 最好写个径绝对路径
     * @throws FileNotFoundException 未找到文件异常
     * @date 2018/7/25
     * @since jdk1.8
     */
    public static <T> void create(Class<T> clazz, String targetDir) throws FileNotFoundException {

        File parentFile = new File(targetDir);
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        //创建tableInfo
        createTableInfo(clazz, parentFile);
    }

    private static <T> void createTableInfo(Class<T> clazz, File parentFile) throws FileNotFoundException {
        String changeLine = System.getProperty("line.separator");
        File file = new File(parentFile, "tableInfo");
        if (!file.exists()) {
            file.mkdirs();
        }

        File reFile = new File(file, StringUtils.upperFist(clazz.getSimpleName()) + ".json");
        if (reFile.exists()) {
            throw Exceptions.runtimeException(reFile.getAbsolutePath() + "已经存在,无法创建");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{\"cols\": ").append(changeLine);
        sb.append("[").append(changeLine);
        List<String> fields = Arrays.stream(ReflectUtils.getFields(clazz)).map(Field::getName).toList();
        sb.append("\t").append("{").append(changeLine);
        sb.append("\t").append("\"field\": \"").append("no").append("\"").append(",").append(changeLine);
        sb.append("\t").append("\"header\": \"").append("no").append("\"").append(",").append(changeLine);
        sb.append("\t").append("\"editable\": ").append(false).append(",").append(changeLine);
        sb.append("\t").append("\"addable\": ").append(false).append(",").append(changeLine);
        sb.append("\t").append("\"sortable\": ").append(false).append(",").append(changeLine);
        sb.append("\t").append("\"search\": ").append(false).append(",").append(changeLine);
        sb.append("\t").append("\"link\": ").append(false).append(",").append(changeLine);
        sb.append("\t").append("\"type\": ").append("\"text\"").append(changeLine);
        sb.append("\t").append("\t").append("}").append(",").append(changeLine);
        if (!CollectionUtils.isEmpty(fields)) {
            int i = 0;
            for (String name : fields) {
                if (i++ != 0) {
                    sb.append("\t").append(",").append(changeLine);
                }
                sb.append("\t").append("{").append(changeLine);
                sb.append("\t").append("\"field\": \"").append(name).append("\"").append(",").append(changeLine);
                sb.append("\t").append("\"header\": \"").append(name).append("\"").append(",").append(changeLine);
                sb.append("\t").append("\"editable\": ").append(true).append(",").append(changeLine);
                sb.append("\t").append("\"addable\": ").append(true).append(",").append(changeLine);
                sb.append("\t").append("\"sortable\": ").append(false).append(",").append(changeLine);
                sb.append("\t").append("\"search\": ").append(false).append(",").append(changeLine);
                sb.append("\t").append("\"link\": ").append(false).append(",").append(changeLine);
                sb.append("\t").append("\"type\": ").append("\"text\"").append(changeLine);
                sb.append("\t").append("\t").append("}").append(changeLine);
            }
        }
        sb.append("]").append(changeLine);
        sb.append(",").append(changeLine);
        sb.append("\"setting\": ").append("{").append(changeLine);
        sb.append("\t").append("\"viewType\": ").append("\"multi\"").append(",").append(changeLine);
        sb.append("\t").append("\"checkbox\": ").append(true).append(",").append(changeLine);
        sb.append("\t").append("\"frontInfo\": ").append("\"\"").append(",").append(changeLine);
        sb.append("\t").append("\"backInfo\": ").append("\"\"").append(",").append(changeLine);
        sb.append("\t").append("\"title\": ").append("\"\"").append(changeLine);
        sb.append("}");
        sb.append("}");

        @Cleanup PrintWriter pw = new PrintWriter(new FileOutputStream(reFile, true));
        pw.println(sb);
    }

}
