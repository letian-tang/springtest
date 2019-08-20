package com.zhoupu.dy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统启动
 *
 * @author tangdingyi
 * @date 创建时间：2018年11月12日 上午9:50:36
 * @version 1.0 *
 * @since
 */
@Component
@Slf4j
public class StartupListener implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;


    private void onStartUpLoad() {
         log.info("-----------onStartUpLoad={}",taskExecutor);
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        onStartUpLoad();
    }
}
