package com.iscas.biz.mp.util;

import cn.hutool.core.io.resource.ClassPathResource;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/20 17:43
 * @since jdk1.8
 */
@SuppressWarnings("unused")
public class MybatisGeneratorUtils {
    private MybatisGeneratorUtils() {}
    public static void generate(String configPath) throws IOException, XMLParserException,
            InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ClassPathResource classPathResource = new ClassPathResource("mybatisGeneratorConfig.xml");

//        File configFile = new File("E:\\coding\\idea\\project\\mybatis-learn\\src\\main\\resources\\generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(classPathResource.getStream());
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
