package com.xyd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: xueYaDong
 * @Company: 东方标准
 * @Date: 2019/12/23/18:51
 * @Description:
 */
@EnableWebMvc
@ComponentScan(basePackages = "com.xyd.controller")
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver getViewResolver(){
        return new InternalResourceViewResolver("/WEB-INF/html",".html");
    }

    @Bean("multipartResolver")
    public CommonsMultipartResolver getMultipartResolver(){
        return new CommonsMultipartResolver();
    }
}