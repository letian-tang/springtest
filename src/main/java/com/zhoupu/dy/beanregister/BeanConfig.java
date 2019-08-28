package com.zhoupu.dy.beanregister;

import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.zhoupu.dy.aop.Pojo;
import com.zhoupu.dy.aop.SimplePojo;
import com.zhoupu.dy.beanregister.beans.DtoImpl;
import com.zhoupu.dy.beanregister.beans.Teacher;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Import({Teacher.class})
@Slf4j
public class BeanConfig {

    @Autowired
    private ApplicationContext context;

    @Bean("simplePojoProxyFactory")
    public ProxyFactory simplePojo() {
        ProxyFactory factory = new ProxyFactory(new SimplePojo("SimplePojo"));
        //factory.addInterface(Pojo.class);
        factory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                return invocation.proceed();
            }
        });
        return factory;
    }

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
