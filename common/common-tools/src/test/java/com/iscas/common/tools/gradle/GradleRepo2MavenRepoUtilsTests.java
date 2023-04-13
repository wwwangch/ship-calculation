package com.iscas.common.tools.gradle;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * gradlemaven仓库转为maven仓库
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/1/8 21:28
 * @since jdk1.8
 */
public class GradleRepo2MavenRepoUtilsTests {
    @Test
    @Disabled
    public void test() throws IOException {
        GradleRepo2MavenRepoUtils.gradle2Maven("D:\\soft\\gradle-repo", "d:/maven-repo");
    }
}
