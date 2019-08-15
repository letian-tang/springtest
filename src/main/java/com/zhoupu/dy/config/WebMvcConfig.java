package com.zhoupu.dy.config;

import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import com.zhoupu.dy.mapping.ApiHandlerMapping;
import com.zhoupu.dy.mapping.UserHandler;

@Configuration
public class WebMvcConfig extends DelegatingWebMvcConfiguration {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        ApiHandlerMapping handlerMapping = new ApiHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(taskExecutor);
    }


    @Autowired
    public void setHandlerMapping(RequestMappingHandlerMapping mapping, UserHandler handler)
            throws NoSuchMethodException {
        RequestMappingInfo info =
                RequestMappingInfo.paths("/user/{id}").methods(RequestMethod.GET).build();
        Method method = UserHandler.class.getMethod("getUser", Long.class);
        mapping.registerMapping(info, handler, method);
    }

}
