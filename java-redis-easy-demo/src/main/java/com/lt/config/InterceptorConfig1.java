package com.lt.config;

import com.lt.interceptor.MyHandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author liangtao
 * @description 拦截器配置方式1，直接继承WebMvcConfigurationSupport,这种方式不推荐
 * 除非我们需要全部自定义配置，否则继承WebMvcConfigurationSupport会导致EnableAutoConfiguration注解失效
 * 具体参考https://charming.blog.csdn.net/article/details/84636521?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-2.control
 * @date 2021年05月25 11:34
 **/
//@Configuration
public class InterceptorConfig1 extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**");
    }
}
