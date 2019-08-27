package com.zhoupu.dy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * 字体什么样子
 */
@SpringBootApplication
@Slf4j
public class DyApplication implements CommandLineRunner {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    public static void main(String[] args) {
        SpringApplication.run(DyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("taskExecutor={}", taskExecutor);
    }

}
