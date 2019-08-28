package com.zhoupu.dy.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.zhoupu.dy.beanregister.beans.IDto;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class AopTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void proxyTest() {
        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new NopInterceptor());
        Pojo pojo = (Pojo) factory.getProxy();
        pojo.foo();

    }

    @Test
    public void proxyFactoryTest() {
        ProxyFactory factory = new ProxyFactory(new SimplePojo("ProxyFactory"));
        factory.addInterface(Pojo.class);
        factory.addAdvice(new NopInterceptor());
        Pojo pojo = (Pojo) factory.getProxy();
        pojo.foo();
    }

    @Test
    public void proxyFactoryAndBeanTest() {
        IDto dtoImpl = context.getBean("dtoImpl", IDto.class);
        log.info("{}", dtoImpl.getName());
        ProxyFactory simplePojo2 = context.getBean("simplePojo2", ProxyFactory.class);
        ((Pojo) simplePojo2.getProxy()).foo();

        IDto dto = context.getBean("idtoProxyFactoryBean", IDto.class);
        ProxyFactoryBean proxyFactoryBean =
                context.getBean("&idtoProxyFactoryBean", ProxyFactoryBean.class);
        log.info("----1----{}", ((IDto) proxyFactoryBean.getObject()).getName());
        log.info("----2----{}", dto.getName());

        ProxyFactory proxyFactory = context.getBean("simplePojoProxyFactory", ProxyFactory.class);
        Pojo pojo = (Pojo) proxyFactory.getProxy();
        pojo.foo();
    }

    @Test
    public void aspectJProxyFactoryTest() {
        AspectJProxyFactory aspectJProxyFactory =
                context.getBean("aspectJProxyFactory", AspectJProxyFactory.class);
        Pojo proxy = aspectJProxyFactory.getProxy();
        proxy.foo();
    }
}
