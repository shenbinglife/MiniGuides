package io.github.shenbinglife.springboot2.layui.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CommonInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private BasePathInterceptor basePathInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(basePathInterceptor).addPathPatterns("/**");
    }
}
