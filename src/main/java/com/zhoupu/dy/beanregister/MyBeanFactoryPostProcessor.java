package com.zhoupu.dy.beanregister;

import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
import com.zhoupu.dy.aop.SimplePojo;
import com.zhoupu.dy.beanregister.beans.DtoImpl;
import com.zhoupu.dy.beanregister.beans.User;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        log.info("-333333-postProcessBeanFactory");
        GenericBeanDefinition beanDefinition = getGenericBeanDefinition();
        DefaultListableBeanFactory defaultListableBeanFactory =
                ((DefaultListableBeanFactory) beanFactory);
        defaultListableBeanFactory.registerBeanDefinition("user", beanDefinition);
        // 使用ProxyFactoryBean代理
        defaultListableBeanFactory.registerSingleton("dtoImpl", getProxyFactoryBean());
        defaultListableBeanFactory.registerSingleton("simplePojo2", getProxyFactory());
    }

    private GenericBeanDefinition getGenericBeanDefinition() {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        beanDefinition.getPropertyValues().add("id", 1234L).add("name", "张三");
        return beanDefinition;
    }

    private ProxyFactoryBean getProxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        // proxyFactoryBean.setInterfaces(IDto.class);
        proxyFactoryBean.setTarget(new DtoImpl("王五"));
        proxyFactoryBean.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                log.info("--------------{}---------", method.getName());
            }
        });
        return proxyFactoryBean;
    }

    private ProxyFactory getProxyFactory() {
        ProxyFactory factory = new ProxyFactory(new SimplePojo("SimplePojo2"));
        factory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                return invocation.proceed();
            }
        });
        return factory;
    }

}
