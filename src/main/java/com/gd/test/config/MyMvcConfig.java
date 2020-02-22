package com.gd.test.config;

import com.gd.test.interceptor.MyInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2020/1/10.
 */
//WebMvcConfigurer来扩展SpringMVC的功能
@Configuration
public class MyMvcConfig implements WebMvcConfigurer{

    private WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter webMvcAutoConfigurationAdapter;
    private ArrayList<String> strings;

    //增加视图解析器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       // registry.addViewController("/gdtest").setViewName("success");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("dashboard");

    }
    //增加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        strings = new ArrayList<>();
        strings.add("/index.html");
        strings.add("/");
        strings.add("/user/login");
        strings.add("/user/logout");
        strings.add("/asserts/*");
        strings.add("/webjars/*");
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/index.html","/","/user/login","/user/logout","/asserts/**","/webjars/**");

        //.excludePathPatterns("/index.html","/","/user/login");
        //,"/asserts*","/webjars*"   /add
    }
}
































