package com.movers.eazymovers.common.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    RequestVerifyInterceptor requestVerifyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestVerifyInterceptor);
        super.addInterceptors(registry);
    }
}
