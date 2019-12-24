package com.xyd.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/23/18:19
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = "com.xyd.service")
@EnableTransactionManagement
public class SpringTxConfig {

    @Bean
    public DataSourceTransactionManager getSourceTransactionManager(DruidDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}