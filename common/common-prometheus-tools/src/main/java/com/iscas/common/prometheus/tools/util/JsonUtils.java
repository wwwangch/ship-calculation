package com.iscas.common.prometheus.tools.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TimeZone;

/**
 * JSON工具类
 *
 * @author LiangJian
 * @date 2018-08-29 09:56
 **/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
public class JsonUtils {
    public static String timePattern = "yyyy-MM-dd HH:mm:ss";

    public static TimeZone timeZone = null;

    private static final ThreadLocal<ObjectMapper> OBJECT_MAPPER_THREAD_LOCAL = new ThreadLocal<>();

    private static volatile ObjectMapper mapper;


    public static void setTimePattern(String timePattern) {
        JsonUtils.timePattern = timePattern;
    }

    public static void setTimeZone(TimeZone timeZone) {
        JsonUtils.timeZone = timeZone;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        OBJECT_MAPPER_THREAD_LOCAL.set(objectMapper);
    }

    /**
     * 对象转json
     *
     * @param object object
     * @return String
     */
    public static String toJson(Object object) {
        try {
            return getMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
//			throw new DataSongException(Status.PARAM_ERROR, String.format("object to json error: [%s]",DataSongExceptionUtils.getExceptionInfo(e)));
        }
//        return null;
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return getMapper().readValue(json, classOfT);
        } catch (IOException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @param json          json
     * @param typeReference 类型
     * @return T
     */
    public static <T> T fromJson(String json, TypeReference typeReference) {
        try {
            return (T) getMapper().readValue(json, typeReference);
        } catch (IOException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        return null;
    }

    /**
     * 定义一个嵌套的泛型、子泛型
     */
    static class ParametricTypes {
        /**
         * 泛型1
         */
        private Class clazz;

        /**
         * 子泛型
         */
        private List<ParametricTypes> subClazz;

        public Class getClazz() {
            return clazz;
        }

        public void setClazz(Class clazz) {
            this.clazz = clazz;
        }

        public List<ParametricTypes> getSubClazz() {
            return subClazz;
        }

        public void setSubClazz(List<ParametricTypes> subClazz) {
            this.subClazz = subClazz;
        }
    }

    @SuppressWarnings("deprecation")
    private static ObjectMapper getMapper() {
        if (!Objects.isNull(OBJECT_MAPPER_THREAD_LOCAL.get())) {
            return OBJECT_MAPPER_THREAD_LOCAL.get();
        }

        synchronized (JsonUtils.class) {
            if (mapper == null) {
                synchronized (JsonUtils.class) {
                    mapper = new ObjectMapper();
                    //为null的不输出
                    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    //大小写问题
                    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

                    //设置等同于@JsonIgnoreProperties(ignoreUnknown = true)
                    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                    //防止转为json是首字母大写的属性会出现两次
                    mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    //设置JSON时间格式
                    SimpleDateFormat myDateFormat = new SimpleDateFormat(timePattern);
                    mapper.setDateFormat(myDateFormat);

                    if (timeZone != null) {
                        mapper.setTimeZone(timeZone);
                    }

                    // 添加对LocalDateTime类型和LocalDate类型支持
                    JavaTimeModule module = new JavaTimeModule();
                    LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    module.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
                    module.addSerializer(LocalDateTime.class, dateTimeSerializer);

                    LocalDateDeserializer dateDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDateSerializer dateSerializer = new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    module.addDeserializer(LocalDate.class, dateDeserializer);
                    module.addSerializer(LocalDate.class, dateSerializer);
                    mapper.registerModules(module);

                    // 处理mybatis延迟加载造成序列化失败的问题
                    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

//			mapper.configure(SerializationFeature.WRAP_ROOT_VALUE CLOSE_CLOSEABLE)
                }
            }
        }
        return mapper;
    }

    /**
     * 单位缩进字符串。
     */
    private static final String SPACE = "\t";

    /**
     * 返回格式化JSON字符串。
     *
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public static String formatJson(String json) {
        StringBuilder result = new StringBuilder();

        int length = json.length();
        int number = 0;
        char key;

        //遍历输入字符串。
        for (int i = 0; i < length; i++) {
            //1、获取当前字符。
            key = json.charAt(i);

            //2、如果当前字符是前方括号、前花括号做如下处理：
            if ((key == '[') || (key == '{')) {
                //（1）如果前面还有字符，并且字符为“：”，打印：换行和缩进字符字符串。
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append('\n');
                    result.append(indent(number));
                }

                //（2）打印：当前字符。
                result.append(key);

                //（3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');

                //（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));

                //（5）进行下一次循环。
                continue;
            }

            //3、如果当前字符是后方括号、后花括号做如下处理：
            if ((key == ']') || (key == '}')) {
                //（1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');

                //（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));

                //（3）打印：当前字符。
                result.append(key);

                //（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if (((i + 1) < length) && (json.charAt(i + 1) != ',')) {
                    result.append('\n');
                }

                //（5）继续下一次循环。
                continue;
            }

            //4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }

            //5、打印：当前字符。
            result.append(key);
        }

        return result.toString();
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进三个空格，即SPACE。
     *
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private static String indent(int number) {
        return SPACE.repeat(Math.max(0, number));
    }

    /**
     * 校验一个JSON串是否为JSON结构，必须满足Map或集合结构
     */
    public static boolean validateJson(String json) {
        boolean flag = false;
        try {
            JsonUtils.fromJson(json, Map.class);
            return true;
        } catch (Exception ignored) {
        }
        try {
            JsonUtils.fromJson(json, List.class);
            return true;
        } catch (Exception ignored) {
        }
        return flag;

    }

    /**
     * 向JSON中追加参数
     * 注意：只支持Map类型的JSON
     *
     * @param json 原始JSON字符串。
     * @param data 要添加的数据，数组类型，数组里两个值，第一个值为key，第二个值为value
     * @return 追加后的JSON字符串。
     */
    public static String appendJson(String json, Object[]... data) throws RuntimeException {
        Map map;
        try {
            map = JsonUtils.fromJson(json, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("JSON格式错误，只支持Map格式的JSON", e);
        }
        if (data != null) {
            for (Object[] datum : data) {
                if (datum == null || datum.length != 2) {
                    throw new RuntimeException("传入的追加格式错误");
                }
                map.put(datum[0], datum[1]);
            }
        }
        return toJson(map);

    }





    //==========================Json序列化和反序列化扩展方法　add by zqw======================================

    /**
     * 嵌套一层泛型序列化
     * add by zqw
     */
    public static <T> T fromJson(String json, Class mainClass, Class subClass) {
        try {
            JavaType javaType = getMapper().getTypeFactory().constructParametricType(mainClass, subClass);
            return getMapper().readValue(json, javaType);
        } catch (IOException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 嵌套泛型序列化
     * add by zqw
     */
    public static <T> T fromJson(String json, ParametricTypes parametricTypes) {
        try {
//            getMapper().getTypeFactory().constructParametricType()
            JavaType javaType = getJavaType(parametricTypes);
            return getMapper().readValue(json, javaType);
        } catch (IOException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static JavaType getJavaType(ParametricTypes parametricTypes) {
        JavaType javaType;
        Class clazz = parametricTypes.getClazz();
        List<ParametricTypes> subClazz = parametricTypes.getSubClazz();
        if (subClazz == null || subClazz.size() == 0) {
            Class[] classes = new Class[0];
            javaType = getMapper().getTypeFactory().constructParametricType(clazz, classes);
        } else {
            JavaType[] javaTypes = new JavaType[subClazz.size()];
            for (int i = 0; i < subClazz.size(); i++) {
                JavaType jt = getJavaType(subClazz.get(i));
                javaTypes[i] = jt;
            }
            javaType = getMapper().getTypeFactory().constructParametricType(clazz, javaTypes);
        }

        return javaType;
    }

    /**
     * 对象直接序列化为字节数组
     */
    public static byte[] toBytes(Object object) {
        try {
            return getMapper().writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象直接序列化到输出流
     */
    public static void toOutputStream(OutputStream os, Object object) {
        try {
            getMapper().writeValue(os, object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象直接序列化到文件
     */
    public static void toFile(File file, Object object) {
        try {
            getMapper().writeValue(file, object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从输入流读取JSON并转化
     */
    public static <T> T fromJson(InputStream is, Class<T> classOfT) {
        try {
            return getMapper().readValue(is, classOfT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从输入流读取JSON并转化
     */
    public static <T> T fromJson(InputStream is, TypeReference<T> typeReference) {
        try {
            return getMapper().readValue(is, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从输入流读取JSON并转化
     */
    public static <T> T fromJson(InputStream is, ParametricTypes parametricTypes) {
        try {
            return getMapper().readValue(is, getJavaType(parametricTypes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从输入流读取JSON并转化
     */
    public static <T> T fromJson(Reader reader, Class<T> classOfT) {
        try {
            return getMapper().readValue(reader, classOfT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从输入流读取JSON并转化
     */
    public static <T> T fromJson(Reader reader, TypeReference<T> typeReference) {
        try {
            return getMapper().readValue(reader, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从输入流读取JSON并转化
     */
    public static <T> T fromJson(Reader reader, ParametricTypes parametricTypes) {
        try {
            return getMapper().readValue(reader, getJavaType(parametricTypes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从文件读取JSON并转化
     */
    public static <T> T fromJson(File file, Class<T> classOfT) {
        try {
            return getMapper().readValue(file, classOfT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从文件读取JSON并转化
     */
    public static <T> T fromJson(File file, TypeReference<T> typeReference) {
        try {
            return getMapper().readValue(file, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从文件读取JSON并转化
     */
    public static <T> T fromJson(File file, ParametricTypes parametricTypes) {
        try {
            return getMapper().readValue(file, getJavaType(parametricTypes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从字节数组读取JSON并转化
     */
    public static <T> T fromJson(byte[] bytes, Class<T> classOfT) {
        try {
            return getMapper().readValue(bytes, classOfT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从字节数组读取JSON并转化
     */
    public static <T> T fromJson(byte[] bytes, TypeReference<T> typeReference) {
        try {
            return getMapper().readValue(bytes, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 从字节数组读取JSON并转化
     */
    public static <T> T fromJson(byte[] bytes, ParametricTypes parametricTypes) {
        try {
            return getMapper().readValue(bytes, getJavaType(parametricTypes));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
