package com.zhoupu.dy.beanregister;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;
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
        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("user", beanDefinition);
    }

    private GenericBeanDefinition getGenericBeanDefinition() {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(User.class);
        beanDefinition.getPropertyValues().add("id", 1234L).add("name", "张三");
        return beanDefinition;
    }
}
