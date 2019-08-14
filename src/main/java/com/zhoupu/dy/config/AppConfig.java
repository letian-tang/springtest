package com.zhoupu.dy.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableCaching
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {


}
