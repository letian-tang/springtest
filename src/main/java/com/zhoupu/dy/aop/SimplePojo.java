package com.zhoupu.dy.aop;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class SimplePojo implements Pojo {

    private String name;

    public SimplePojo(String name) {
        this.name = name;
    }

    @Override
    public void foo() {
        log.info("{}", ">>>>>>>>>>>>>foo");
    }

    @Override
    public void bar() {
        log.info("{}", ">>>>>>>>>>>>>bar");
    }

    @Override
    public String toString() {
        return name;
    }
}
