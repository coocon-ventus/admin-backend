package com.coocon.admin.common.config;

import com.coocon.admin.common.filter.LoggingFilter;
import com.coocon.admin.common.filter.RequestResponseWrapperFilter;
import com.coocon.admin.common.interceptor.DefaultInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean requestResponsefilterBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new RequestResponseWrapperFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean filterBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new LoggingFilter());
        registrationBean.addUrlPatterns("/*");

        return  registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DefaultInterceptor()).excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");
    }
}
