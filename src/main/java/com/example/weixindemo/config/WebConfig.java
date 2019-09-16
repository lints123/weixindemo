package com.example.weixindemo.config;

import com.example.weixindemo.comment.SpringInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        // registry.addInterceptor(new SpringInterceptor()).addPathPatterns("/**");
    }

}
