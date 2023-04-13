package com.iscas.base.biz.util.echarts;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import com.iscas.common.tools.constant.CharsetConstant;
import com.iscas.common.tools.constant.CommonConstant;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

/**
 * freemarker工具类
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/11/24
 * @since jdk1.8
 */
public class FreemarkerUtils {


    public static String generateString(String templateFileName, String templateDirectoryPath, Map<String, Object> datas)
            throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);

        // 设置默认编码
        configuration.setDefaultEncoding(CharsetConstant.UTF8);

        if (templateDirectoryPath.startsWith(CommonConstant.CLASSPATH)) {
            //设置类加载器
            configuration.setClassLoaderForTemplateLoading(Thread.currentThread().getContextClassLoader(),
                    StringUtils.substringAfter(templateDirectoryPath, CommonConstant.CLASSPATH));
        } else {
            // 设置模板所在文件夹
            configuration.setDirectoryForTemplateLoading(new File(templateDirectoryPath));
        }

        // 生成模板对象
        Template template = configuration.getTemplate(templateFileName);

        // 将datas写入模板并返回
        try (StringWriter stringWriter = new StringWriter()) {
            template.process(datas, stringWriter);
            stringWriter.flush();
            return stringWriter.getBuffer().toString();
        }
    }
}