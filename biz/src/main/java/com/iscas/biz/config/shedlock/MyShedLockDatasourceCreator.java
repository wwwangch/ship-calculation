package com.iscas.biz.config.shedlock;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.iscas.base.biz.config.shedlock.ConditionalOnShedLock;
import com.iscas.base.biz.config.shedlock.ShedLockDatasourceCreator;
import com.iscas.biz.mp.aop.enable.ConditionalOnMybatis;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * 生成一个datasource
 *
 * @author zhuquanwen
 * @version 1.0
 * @date 2021/5/20 19:56
 * @since jdk1.8
 */
@SuppressWarnings("unused")
@Component
@ConditionalOnShedLock
@ConditionalOnMybatis
@Slf4j
public class MyShedLockDatasourceCreator implements ShedLockDatasourceCreator, EnvironmentAware {
    private Environment environment;

    @Value("${spring.datasource.names}")
    private String datasourceNames;

    @Override
    public DataSource createDataSource() {
        String prefix = "spring.datasource.druid." + datasourceNames.split(",")[0].trim() + ".";
        String driverClassNameCfg = prefix + "driver-class-name";
        String urlCfg = prefix + "url";
        String usernameCfg = prefix + "username";
        String passwordCfg = prefix + "password";
        String decryptCfg = prefix + "connect-properties.config.decrypt";
        String decryptKeyCfg = prefix + "connect-properties.config.decrypt.key";
        String driverClassName = environment.getProperty(driverClassNameCfg);
        String url = environment.getProperty(urlCfg);
        String username = environment.getProperty(usernameCfg);
        String password = environment.getProperty(passwordCfg);
        String decrypt = environment.getProperty(decryptCfg);
        String decryptKey = environment.getProperty(decryptKeyCfg);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);

        //noinspection AlibabaUndefineMagicConstant
        if (Objects.equals("true", decrypt)) {
            //解密
            try {
                password = ConfigTools.decrypt(decryptKey, password);
            } catch (Exception e) {
                log.error("密码:[" + password + "]解密失败", e);
            }
        }
        dataSource.setPassword(password);
        return dataSource;
    }

    @Override
    public void setEnvironment(@NotNull Environment environment) {
        this.environment = environment;
    }
}
