package com.iscas.common.tools.htmlsucker;

import net.oschina.htmlsucker.Article;
import net.oschina.htmlsucker.HtmlSucker;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2020/6/27 10:54
 * @since jdk1.8
 */
public class HtmlSuckerTests {
    @Test
    @Disabled
    public void test() throws IOException {
        String url = "https://www.oschina.net/news/92798/micro-match-1-0-1-released";
        Article aiticle = HtmlSucker.select(HtmlSucker.TEXT_DENSITY_EXTRACTOR).parse(url, 20000);
        System.out.println(aiticle);
    }
}
