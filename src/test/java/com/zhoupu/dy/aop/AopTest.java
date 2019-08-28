package com.zhoupu.dy.aop;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

public class AopTest {

    @Test
    public void proxyTest() {
        ProxyFactory factory = new ProxyFactory(new SimplePojo());
        factory.addInterface(Pojo.class);
        factory.addAdvice(new NopInterceptor());
        Pojo pojo = (Pojo) factory.getProxy();
        pojo.foo();

    }
}
