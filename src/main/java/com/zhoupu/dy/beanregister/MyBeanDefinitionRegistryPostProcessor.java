package com.zhoupu.dy.beanregister;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;
import com.zhoupu.dy.beanregister.beans.Foo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
            throws BeansException {
        log.info("-111111-postProcessBeanDefinitionRegistry");
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Foo.class)
                .addPropertyValue("name", "张三").addPropertyValue("description", "描述对象")
                .getBeanDefinition();
        registry.registerBeanDefinition("foo", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        log.info("-222222-postProcessBeanFactory");
    }
}
