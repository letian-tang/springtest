package com.zhoupu.dy.beanregister;

import com.zhoupu.dy.beanregister.beans.DtoImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.zhoupu.dy.beanregister.beans.Teacher;

import java.lang.reflect.Method;

@Configuration
@Import({Teacher.class})
@Slf4j
public class BeanConfig {


    @Bean("studentFactoryBean")
    public StudentFactoryBean studentFactoryBean() {
        return new StudentFactoryBean();
    }

    @Bean("idtoProxyFactoryBean")
    public ProxyFactoryBean getProxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new DtoImpl("六哥"));
        proxyFactoryBean.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                log.info("--------------{}---------", method.getName());
            }
        });
        return proxyFactoryBean;
    }

}
