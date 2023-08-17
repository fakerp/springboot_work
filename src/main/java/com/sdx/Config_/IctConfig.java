package com.sdx.Config_;

import com.sdx.Interceptor_.loginIct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//拦截器的设置
@Configuration
public class IctConfig implements WebMvcConfigurer {

    @Autowired
    private loginIct login;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(login).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
