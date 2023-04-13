package com.iscas.biz.mp.config.db;


import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;


/**
 * @author admin
 */
@SuppressWarnings("unused")
public class MybatisPlusConfig implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }

    /**
     * 相当于顶部的：
     * {@code @MapperScan("com.baomidou.springboot.mapper*")}
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        String mpScannerPackage = environment.getProperty("mybatis-plus.scanner.package");
        scannerConfigurer.setBasePackage(mpScannerPackage);
        return scannerConfigurer;
    }


}
