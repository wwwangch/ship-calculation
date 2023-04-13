package com.iscas.common.web.tools.echarts;

import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.code.Orient;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import org.junit.jupiter.api.Test;

import java.util.*;


/**
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/25 15:52
 * @since jdk1.8
 */
public class EchartCreateTests {

    @Test
    public void testEchart() {
        String[] types = {"ECS", "实例", "CPU", "MEM"};
        int[][] datas = {
                {120, 132, 101, 134, 90, 230, 210},
                {220, 182, 191, 234, 290, 330, 310},
                {150, 232, 201, 154, 190, 330, 410},
                {150, 232, 201, 154, 190, 330, 410}
        };

        String title = "资源增长情况";

        GsonOption option = new GsonOption();

        option.title().text(title).x("left");// 大标题、位置

        // 提示工具
        option.tooltip().trigger(Trigger.axis);// 在轴上触发提示数据
        // 工具栏
        option.toolbox().show(true).feature(Tool.saveAsImage);// 显示,保存为图片

        option.legend(types);// 图例

        com.github.abel533.echarts.axis.CategoryAxis category = new com.github.abel533.echarts.axis.CategoryAxis();// 轴分类
        category.data("2019-03-09", "2019-03-02", "2019-03-16");
        category.boundaryGap(false);// 起始和结束两端空白策略

        //循环数据
        for (int i = 0; i < types.length; i++) {
            Line line = new Line();// 三条线，三个对象
            String type = types[i];
            line.name(type).stack("总量");
            for (int j = 0; j < datas[i].length; j++)
                line.data(datas[i][j]);
            option.series(line);
        }

        if (true) {// 横轴为类别、纵轴为值
            option.xAxis(category);// x轴
            // y轴
            com.github.abel533.echarts.axis.ValueAxis ecsY = new com.github.abel533.echarts.axis.ValueAxis();
            ecsY.name("ECS 台").position("left").axisLine().lineStyle().color("#1E90FF");

            com.github.abel533.echarts.axis.ValueAxis insY = new com.github.abel533.echarts.axis.ValueAxis();
            insY.name("容器实例 台").position("left").axisLine().lineStyle().color("#8A2BE2");

            com.github.abel533.echarts.axis.ValueAxis cpuY = new com.github.abel533.echarts.axis.ValueAxis();
            cpuY.name("CPU 核").position("right").axisLine().lineStyle().color("#EEB422");

            com.github.abel533.echarts.axis.ValueAxis memY = new com.github.abel533.echarts.axis.ValueAxis();
            memY.name("MEM GB").position("right").axisLine().lineStyle().color("#00CD00");

            option.yAxis(ecsY, insY);
        } else {// 横轴为值、纵轴为类别
            option.xAxis(new com.github.abel533.echarts.axis.ValueAxis());// x轴
            option.yAxis(category);// y轴
        }

        String optionStr = option.toString().replace(" ", "");
        System.out.println(optionStr);

    }

    @Test
    public void testEchart2() {
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
        System.out.println(optionStr);
    }

}
