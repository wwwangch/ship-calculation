package com.iscas.base.biz.util.echarts;

import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Pie;
import com.iscas.common.tools.picture.ImageUtils;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/24 21:05
 * @since jdk1.8
 */
public class EchartTests {
    private static String url = "http://localhost:7777";

    //测试柱状图
    @Test
    public void test() throws TemplateException, IOException {
        // 变量
        String title = "销量";
        String[] categories = new String[] { "2019", "2020", "2021" };
        int[] values = new int[] { 100, 105, 200 };

        // 模板参数
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("categories", JSON.toJSONString(categories));
        datas.put("values", JSON.toJSONString(values));
        datas.put("title", title);

        // 生成option字符串
//        String option = FreemarkerUtils.generateString("histogram.ftl", "C:\\ideaProjects\\newframe\\biz-base\\src\\test\\java\\com\\iscas\\base\\biz\\util\\echarts", datas);
        String option = FreemarkerUtils.generateString("histogram.ftl", "classpath:ftl", datas);

        // 根据option参数
        String base64 = EchartsUtils.generateEchartsBase64(url, option);

        System.out.println("BASE64:" + base64);

        File test = File.createTempFile("test", ".png");
        test.deleteOnExit();
        try (OutputStream os = new FileOutputStream(test)) {
            ImageUtils.convertBase64ToImage(base64, os);
        }
        System.out.println("测试完成");
    }

    //测试折线图
    @Test
    public void testLine() throws TemplateException, IOException {
        // 标题
        String title = "资源增长情况";
        //legendData
        String[] legendData = new String[]{"ECS", "实例", "CPU", "MEM"};
        //xAxisData
        String[] xAxisData = new String[]{"2021-10-01", "2021-10-02", "2021-10-03", "2021-10-04", "2021-10-05"};
        //yAxis
        ArrayList<Map<String, Object>> yAxis = new ArrayList<>(){{
            add(new HashMap<>(){{
                put("type", "value");
                put("position", "left");
                put("name", "台");
            }});
            add(new HashMap<>(){{
                put("type", "value");
                put("position", "right");
                put("name", "使用量");
            }});
        }};
        //seriesData
        ArrayList<Map<String, Object>> series = new ArrayList<>(){{
            add(new HashMap<>(){{
                put("name", "ECS");
                put("type", "line");
                put("data", new int[]{100, 200, 210, 180, 260});
            }});
            add(new HashMap<>(){{
                put("name", "实例");
                put("type", "line");
                put("data", new int[]{320, 220, 268, 320, 330});
            }});
            add(new HashMap<>(){{
                put("name", "CPU");
                put("type", "line");
                put("data", new int[]{120, 110, 121, 110, 160});
            }});
            add(new HashMap<>(){{
                put("name", "MEM");
                put("type", "line");
                put("data", new int[]{320, 23, 65, 190, 285});
            }});
        }};

        // 模板参数
        HashMap<String, Object> datas = new HashMap<>();
        datas.put("xAxisData", JSON.toJSONString(xAxisData));
        datas.put("legendData", JSON.toJSONString(legendData));
        datas.put("title", title);
        datas.put("yAxis", JSON.toJSONString(yAxis));
        datas.put("data", JSON.toJSONString(series));


        // 生成option字符串
        String option = FreemarkerUtils.generateString("line.ftl", "classpath:ftl", datas);

        // 根据option参数
//        option = "{\"title\":{\"text\":\"资源增长情况\",\"x\":\"left\"},\"toolbox\":{\"feature\":{\"saveAsImage\":{\"show\":true,\"title\":\"保存为图片\",\"type\":\"png\",\"lang\":[\"点击保存\"]}},\"show\":true},\"tooltip\":{\"trigger\":\"axis\"},\"legend\":{\"data\":[\"ECS\",\"实例\",\"CPU\",\"MEM\"]},\"xAxis\":[{\"boundaryGap\":false,\"type\":\"category\",\"data\":[\"2019-03-09\",\"2019-03-02\",\"2019-03-16\"]}],\"yAxis\":[{\"type\":\"value\",\"position\":\"left\",\"name\":\"ECS台\",\"axisLine\":{\"lineStyle\":{\"color\":\"#1E90FF\"}}},{\"type\":\"value\",\"position\":\"left\",\"name\":\"容器实例台\",\"axisLine\":{\"lineStyle\":{\"color\":\"#8A2BE2\"}}}],\"series\":[{\"name\":\"ECS\",\"type\":\"line\",\"stack\":\"总量\",\"data\":[120,132,101,134,90,230,210]},{\"name\":\"实例\",\"type\":\"line\",\"stack\":\"总量\",\"data\":[220,182,191,234,290,330,310]},{\"name\":\"CPU\",\"type\":\"line\",\"stack\":\"总量\",\"data\":[150,232,201,154,190,330,410]},{\"name\":\"MEM\",\"type\":\"line\",\"stack\":\"总量\",\"data\":[150,232,201,154,190,330,410]}]}" ;

        String base64 = EchartsUtils.generateEchartsBase64(url, option);

        System.out.println("BASE64:" + base64);

        File test = File.createTempFile("test", ".png");
        test.deleteOnExit();
        try (OutputStream os = new FileOutputStream(test)) {
            ImageUtils.convertBase64ToImage(base64, os);
        }
        System.out.println("测试完成");
    }


    //测试饼状图
    @Test
    public void testPie() throws IOException {

        String option = createPie();

        String base64 = EchartsUtils.generateEchartsBase64(url, option);

        System.out.println("BASE64:" + base64);

        File test = File.createTempFile("test", ".png");
        test.deleteOnExit();
        try (OutputStream os = new FileOutputStream(test)) {
            ImageUtils.convertBase64ToImage(base64, os);
        }
        System.out.println("测试完成");
    }

    private String createPie() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("NAME", "张三" + i);
            map.put("TOTAL", new Random().nextInt(50));
            list.add(map);
        }
        //创建Option
        Option option = new GsonOption();
        option.title(new Title().text("测试饼图").x("middle"));
        option.tooltip(Trigger.item);
        option.legend(new Legend().orient(Orient.vertical).left("left"));
        //饼图数据
        Pie pie = new Pie("测试饼图");
        //循环数据
        for (Map<String, Object> objectMap : list) {
            //饼图数据
            pie.data(new PieData(objectMap.get("NAME").toString(), objectMap.get("TOTAL")));
        }
        //设置数据
        option.series(pie);
        String optionStr = option.toString().replace(" ", "");
        return optionStr;
    }

}
