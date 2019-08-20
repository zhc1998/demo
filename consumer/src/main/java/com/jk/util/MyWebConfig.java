
package com.jk.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")       //拦截项目中的哪些请求
                .excludePathPatterns("/toshow/toIndex")
                .excludePathPatterns("/toshow/toUpdatePassword")
                .excludePathPatterns("/toshow/tofrontLogin")
                .excludePathPatterns("/toshow/toZhuCe")
                .excludePathPatterns("/xxf/getcode")
                .excludePathPatterns("/xxf/yz")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/xxf/login");  //对项目中的哪些请求不拦截
    }
}

