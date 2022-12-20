package com.travelin.travelinapi.configurations.interceptors;

import com.travelin.travelinapi.components.interceptors.GenericHttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Mihai-Cristian Popescu
 * @since : 12/26/2022, Mon
 **/

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private GenericHttpInterceptor genericHttpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(genericHttpInterceptor).addPathPatterns("/auth/**");
    }
}
