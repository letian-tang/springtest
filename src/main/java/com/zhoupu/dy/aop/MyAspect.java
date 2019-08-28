package com.zhoupu.dy.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
public class MyAspect {
    @Pointcut("execution(* foo(..))")
    private void beforeAdd() {}

    @Before("beforeAdd()")
    public void before() {
        log.info("-----------before-----------");
    }
}
