package com.iscas.biz.util;

import com.iscas.biz.mp.util.MybatisGeneratorUtils;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/2/20 17:51
 * @since jdk1.8
 */
public class MybatisGeneratorHelper {
    public static void main(String[] args) throws InterruptedException, SQLException, InvalidConfigurationException, XMLParserException, IOException {
        MybatisGeneratorUtils.generate("mybatisGeneratorConfig.xml");
    }
}
