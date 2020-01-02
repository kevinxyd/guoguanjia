package com.xyd.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/23/17:49
 * @Description:
 */
@Configuration
@MapperScan(basePackages = "com.xyd.mapper")
@Import({SpringTxConfig.class,SpringCacheConfig.class})
@PropertySource(value = "classpath:system.properties",encoding = "utf-8")
public class MybatisConfig {
    //配置数据源
    @Bean
    public DruidDataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        InputStream resourceAsStream = MybatisConfig.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        druidDataSource.configFromPropety(properties);//自动配置参数
        return druidDataSource;
    }
    //获取sqlsession
    @Bean
    public SqlSessionFactoryBean getSqlSession(DruidDataSource dataSource){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);//设置数据源
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);//设置支持驼峰命名转换
        configuration.setLogImpl(Log4j2Impl.class);//使用log4j2日志输出
        factoryBean.setConfiguration(configuration);
        PageInterceptor pageInterceptor = new PageInterceptor();//分页拦截对象
        //开启分页对象默认设置，解决自动适配方言问题
        pageInterceptor.setProperties(new Properties());
        factoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        return factoryBean;
        /*

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        tk.mybatis.mapper.session.Configuration configuration = new tk.mybatis.mapper.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(Log4j2Impl.class);
        factoryBean.setConfiguration(configuration);
        PageInterceptor pageInterceptor = new PageInterceptor();
        //开启分页对象默认设置，解决自动适配方言问题
        pageInterceptor.setProperties(new Properties());
        factoryBean.setPlugins(pageInterceptor);
        return factoryBean;*/
    }
}