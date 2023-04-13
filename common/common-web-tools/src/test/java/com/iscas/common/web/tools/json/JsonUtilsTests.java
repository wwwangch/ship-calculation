package com.iscas.common.web.tools.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2019/6/4 8:37
 * @since jdk1.8
 */
public class JsonUtilsTests {
    private String json = "{\n" +
                            "\t\"name\": \"zhangsan\",\n" +
                            "\t\"age\": 24,\n" +
                            "\t\"friends\": [{\n" +
                            "\t\t\"name\":\"lisi\",\n" +
                            "\t\t\"age\": 18\n" +
                            "\t}]\n" +
                            "}";

    private String json2 = "{\n" +
            "\t\"json\": {\n" +
            "\t\t\"a\": {\n" +
            "\t\t\t\"www\": \"ff\",\n" +
            "\t\t\t\"rrr\": [\"v1\", \"v2\"]\n" +
            "\t\t},\n" +
            "\t\t\"b\": {\n" +
            "\t\t\t\"www\": \"4567ttt\",\n" +
            "\t\t\t\"rrr\": [\"v1\", \"v2\"]\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

    @Test
    public void testDeSerialCost() {
        List<Map<String, Object>> list = new ArrayList<>();
        Random r = new Random();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 300; i++) {
                list.add(new HashMap<>(){{
                    put("id", 100);
                    put("time", "2020-12-15-48 14:14:25");
                    put("value", r.nextDouble());
                    put("xid", UUID.randomUUID());
                }});
            }
            String s = JsonUtils.toJson(list);
            long start = System.currentTimeMillis();
            List list1 = JsonUtils.fromJson(s, List.class);
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    @Test
    public void test0() {
        Map map = JsonUtils.fromJson(json2, Map.class);
        System.out.println(map);
    }

    @Test
    @Disabled
    public void test1() {
        String name = JsonUtils.getValueByKey(json, "name");
        System.out.println(name);
    }
    @Test
    @Disabled
    public void test2() {
        String ret = JsonUtils.getValueByKey(json2, "json.b.www");
        System.out.println(ret);
    }

    /**
     * 测试嵌套的类
     * */
    @Test
    public void test3() throws JsonProcessingException {
        A<SubA> a = new A<>();
        SubA subA = new SubA();
        subA.setName("张三");
        subA.setDesc("啦啦啦");
        a.setT(subA);
        a.setStr("cgwegwegweg");

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(a);
        System.out.printf("JSON字符串：%s\n", s);

        JsonUtils.ParametricTypes parametricTypes = new JsonUtils.ParametricTypes();
        parametricTypes.setClazz(A.class);

        JsonUtils.ParametricTypes subParametricTypes = new JsonUtils.ParametricTypes();
        subParametricTypes.setClazz(SubA.class);

        parametricTypes.setSubClazz(Arrays.asList(subParametricTypes));

        A o = JsonUtils.fromJson(s, parametricTypes);
        System.out.println(o);
    }

    /**
     * 测试嵌套的类
     * */
    @Test
    public void test4() throws JsonProcessingException {
        A<SubA> a = new A<>();
        SubA subA = new SubA();
        subA.setName("张三");
        subA.setDesc("啦啦啦");
        a.setT(subA);
        a.setStr("cgwegwegweg");

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(a);
        System.out.printf("JSON字符串：%s\n", s);


        A o = JsonUtils.fromJson(s, A.class, SubA.class);
        System.out.println(o);
    }

    static class A<T> {
        private T t;
        private String str;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
    static class SubA {
        private String name;
        private String desc;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }





    @Test
    public void ttt() throws JsonProcessingException {
        Map<String, String> map1 = new HashMap<>();
        map1.put("name", "lalala");
        TestModelA ta1 = new TestModelA("1", "zhangsan", 18, map1);
        TestModelA ta2 = new TestModelA("2", "lisi", 25, map1);
        TestModelA ta3 = new TestModelA("3", "wangwu", 39, map1);
        List<TestModelA> modelAList = new ArrayList<>();
        Collections.addAll(modelAList, ta1, ta2, ta3);
        String jsonStr = JsonUtils.toJson(modelAList);

        ObjectMapper mapper = new ObjectMapper();
        Class mainClass = ArrayList.class;
        Class subClass = TestModelA.class;
        JavaType javaType = mapper.getTypeFactory().constructParametricType(mainClass, subClass);
        List<TestModelA> result = mapper.readValue(jsonStr, javaType);
        System.out.println(result);
    }

    static class TestModelA {
        private String id;
        private String name;
        private Integer age;
        private Map<String, String> map = new HashMap<>();

        public TestModelA() {
        }

        public TestModelA(String id, String name, Integer age, Map<String, String> map) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.map = map;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
    }

    /**
     * json序列化为字节数组
     * */
    @Test
    public void testObjectToBytes() throws JsonProcessingException, UnsupportedEncodingException {
        A<SubA> a = new A<>();
        SubA subA = new SubA();
        subA.setName("张三");
        subA.setDesc("啦啦啦");
        a.setT(subA);
        a.setStr("cgwegwegweg");

        final byte[] bytes = JsonUtils.toBytes(a);
        System.out.println(Arrays.toString(bytes));

        Object o = JsonUtils.fromJson(new String(bytes, "utf-8"), A.class, SubA.class);
        System.out.println(o);
    }

    /**
     * json序列化到输出流
     * */
    @Test
    public void testObjectToOutputStream() throws JsonProcessingException, UnsupportedEncodingException {
        A<SubA> a = new A<>();
        SubA subA = new SubA();
        subA.setName("张三");
        subA.setDesc("啦啦啦");
        a.setT(subA);
        a.setStr("cgwegwegweg");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        JsonUtils.toOutputStream(os, a);
        final byte[] bytes = os.toByteArray();
        System.out.println(Arrays.toString(bytes));

        Object o = JsonUtils.fromJson(new String(bytes, "utf-8"), A.class, SubA.class);
        System.out.println(o);
    }

    /**
     * json序列化到文件
     * */
    @Test
    public void testObjectToFile() throws IOException {
        A<SubA> a = new A<>();
        SubA subA = new SubA();
        subA.setName("张三");
        subA.setDesc("啦啦啦");
        a.setT(subA);
        a.setStr("cgwegwegweg");
        File file = File.createTempFile("test", ".json");
        file.deleteOnExit();
        JsonUtils.toFile(file, a);
        final List<String> strings = Files.readAllLines(file.toPath());
        strings.forEach(System.out::println);
        file.delete();
    }

    /**
     * 测试localDatetime序列化
     * */
    @Test
    public void testLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        String jsonStr = JsonUtils.toJson(now);
        System.out.println(jsonStr);
    }

    /**
     * 测试localDatetime序列化
     * */
    @Test
    public void testLocalDateTime2() {
        TM tm = new TM();
        tm.setTime(LocalDateTime.now());
        tm.setStr("test");
        System.out.println(JsonUtils.toJson(List.of(tm)));
    }

    @Data
    private static class TM {
        private LocalDateTime time;
        private String str;
    }

}
