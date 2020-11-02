package com.example.initial_frame.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *  @author: SunDC
 *  @Date: 2020/10/30 3:05 下午
 *  @Description: 拦截器注册
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    IPVerificationInterceptor ipVerificationInterceptor ;
    @Override
    public void addInterceptors (InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(ipVerificationInterceptor).addPathPatterns("/**");
    }

}
