package com.lt.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @author liangtao
 * @description
 * @date 2021年05月25 14:43
 **/
@Configuration
public class MyWebRequestInterceptor implements WebRequestInterceptor {
    @Override
    public void preHandle(WebRequest request) throws Exception {
        showRequest(request);
        System.out.println("webRequestInterceptor preHandle");
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        showRequest(request);
        System.out.println("webRequestInterceptor postHandle");
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        showRequest(request);
        System.out.println("webRequestInterceptor afterCompletion");
    }

    private void showRequest(WebRequest request){
        request.getHeaderNames().forEachRemaining(s->{
            System.out.println(s+" " + request.getHeader(s));
        });
        System.out.println();
        System.out.println("-------------");
    }
}
