package com.iscas.common.harbor.tools.utils;

import com.iscas.common.harbor.tools.HarborUtils;
import com.iscas.common.harbor.tools.exception.CallHarborException;
import com.iscas.common.harbor.tools.model.ModuleHealth;
import com.iscas.common.harbor.tools.model.Project;
import com.iscas.common.harbor.tools.model.Repository;
import com.iscas.common.harbor.tools.model.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 *
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/12/8 18:43
 * @since jdk1.8
 */
public class HarborUtilsTests {

    /**
     * 查看组件健康状况
     * */
    @Test
    public void test1() throws IOException, CallHarborException {
        List<ModuleHealth> healths = HarborUtils.health();
        healths.forEach(System.out::println);
    }

    /**
     * 查询project
     * */
    @Test
    public void test2() throws IOException, CallHarborException {
        List<Project> lib = HarborUtils.searchProject("lib");
        lib.forEach(System.out::println);
    }

    /**
     * 创建一个project
     * */
    @Test
    public void test3() throws IOException, CallHarborException {
        Project project = new Project();
        project.setName("xxx")
                .setProjectPublic(true)
                .setRepoCount(100);
        HarborUtils.createProject(project);
    }

    /**
     * 查询repository
     * */
    @Test
    public void test4() throws IOException, CallHarborException {
        List<Repository> lib = HarborUtils.searchRepo("lib");
        lib.forEach(System.out::println);
    }

    /**
     * 按照ID 获取repository的属性
     * */
    @Test
    public void test5() throws IOException, CallHarborException {
        Project project = HarborUtils.getProjectById(2);
        System.out.println(project);
    }

    /**
     * 根据镜像名称获取tag
     * */
    @Test
    public void test6() throws IOException, CallHarborException {
        List<Tag> tags = HarborUtils.getTags("library/consumer-test");
        tags.forEach(System.out::println);
    }

    /**
     * 删除一个镜像
     * */
    @Test
    public void test7() throws IOException, CallHarborException {
        boolean b = HarborUtils.deleteRepo("library/prom/node-exporter");
        Assertions.assertTrue(b);
    }

    /**
     * 删除一个镜像的标签
     * */
    @Test
    public void test8() throws IOException, CallHarborException {
        boolean b = HarborUtils.deleteRepoTag("library/cpaas-manager-frontend", "0.0.71");
        Assertions.assertTrue(b);
    }

}
