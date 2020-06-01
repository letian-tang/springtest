package com.zhoupu;

import com.zhoupu.dy.statemachine.StateMachineConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;

/**
 * 字体什么样子
 */
@SpringBootApplication
@Slf4j
public class DyApplication implements CommandLineRunner {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private StateMachine<StateMachineConfig.States, StateMachineConfig.Events> stateMachine;


    public static void main(String[] args) {
        SpringApplication.run(DyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        context.getBeansWithAnnotation(SpringBootApplication.class).forEach((k, v) -> {
            log.info("-------{}", v.getClass().getPackage());
        });
        log.info("taskExecutor={}", taskExecutor);
        stateMachine.start();

        stateMachine.sendEvent(StateMachineConfig.Events.E1);
//        stateMachine.sendEvent(StateMachineConfig.Events.E2);
    }

}
