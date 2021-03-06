package com.zhoupu.dy.beanregister;

import com.zhoupu.common.ApplicationContextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.zhoupu.dy.aop.NopInterceptor;
import com.zhoupu.dy.aop.Pojo;
import com.zhoupu.dy.aop.SimplePojo;
import com.zhoupu.dy.beanregister.beans.*;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class BeanRegisterTests {

    @Autowired
    private ApplicationContext context;

    @Test
    public void beanTest() {
        Student s = context.getBean("studentFactoryBean", Student.class);
        log.info("{}", s);

        Teacher t = context.getBean(Teacher.class);
        t.setName("老师");
        log.info("{}", t);

        User user = context.getBean(User.class);
        log.info("{}", user);

        Foo foo = context.getBean(Foo.class);
        log.info("{}", foo);


    }

    @Test
    public void beanTest2() {
        ApplicationContextUtils.getApplicationContext().getAutowireCapableBeanFactory();


    }



}
