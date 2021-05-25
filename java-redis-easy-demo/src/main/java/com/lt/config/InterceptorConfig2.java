package com.lt.config;

import com.lt.interceptor.MyWebRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liangtao
 * @description 拦截器配置方式2,推荐使用
 * @date 2021年05月25 11:36
 **/
@Configuration
public class InterceptorConfig2 implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**");
        registry.addWebRequestInterceptor(new MyWebRequestInterceptor()).addPathPatterns("/**");
    }


}
