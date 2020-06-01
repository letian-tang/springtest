package com.zhoupu.dy;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

/**
 * 代码描述
 * <p>
 *
 * @author 阿汤
 * @since 2020/2/20 16:42
 */
@Slf4j
public class Test333 {
    AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    public int incrementAndGetModulo(int modulo) {
        for (;;) {
            // 当前值
            int current = nextServerCyclicCounter.get();
            // 新值，通过对于modulo（就是实例个数）取余
            int next = (current + 1) % modulo;
            // 只有设置成功才返回
            if (nextServerCyclicCounter.compareAndSet(current, next)) {
                return next;
            }
        }
    }

    @Test
    public void tes() {
        Test333 t = new Test333();
        for (int i = 0; i < 12; i++) {
            log.info(t.incrementAndGetModulo(3) + "");
        }
        System.out.println(1285%32);
    }

    @Test
    public void tt2(){
        System.out.println( BeanUtils.isSimpleProperty(Map.class));
    }
}
