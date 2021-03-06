package com.xyd.config;

import com.xyd.Inteceptor.LoginInteceptor;
import com.xyd.Inteceptor.ResourcesInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
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

    /**
     * 由于spring的生命周期中，@Bean创建组件bean会先执行
     * 依赖注入操作会后执行，可以从容器中获取ResourcesInteceptor对象
     */
    @Autowired
    ResourcesInteceptor resourcesInteceptor;

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

    /**
     * 当springmvc加载创建该bean的时候，父容器已经启动，并且service层对象已经创建，可以
     * 在组件中使用@AutoWired
     * @return
     */
    @Bean
    public ResourcesInteceptor getResourcesInteceptor(){

        return new ResourcesInteceptor();
    }


    /**
     * 注册拦截器设置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInteceptor loginInteceptor = new LoginInteceptor();
        //注册拦截器对象
        InterceptorRegistration loginRegistration = registry.addInterceptor(loginInteceptor);
        //设置拦截逻辑
        loginRegistration.addPathPatterns(new String[]{"/**"});//拦截所有请求
        //设置放行逻辑
        loginRegistration.excludePathPatterns(new String[]{"/toLogin","/doLogin","/index","/manager/menu/selectByUid"});
        loginRegistration.order(1);

        InterceptorRegistration resourcesRegistration = registry.addInterceptor(resourcesInteceptor);
        //设置拦截逻辑

        resourcesRegistration.addPathPatterns(new String[]{"/**"});//拦截所有请求
        resourcesRegistration.excludePathPatterns(new String[]{"/toLogin","/doLogin","/index","/manager/menu/selectByUid"});
        resourcesRegistration.order(2);


    }
}