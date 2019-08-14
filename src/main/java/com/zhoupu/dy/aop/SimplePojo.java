package com.zhoupu.dy.aop;

public class SimplePojo implements Pojo {
    @Override
    public void foo() {
        System.out.println("foo");
    }

    @Override
    public void bar() {
        System.out.println("bar");
    }
}
