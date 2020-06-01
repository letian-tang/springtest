package com.zhoupu.dy.aop;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 代码描述
 * <p>
 */
@Component
@Aspect
@Slf4j
public class MethodParamInterceptor {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void paramAspect() {

    }


    @Before("paramAspect()")
    public void beforeDataSource(JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs()).forEach(paramObject -> {
            if (paramObject instanceof Map) {
                Map parameter = (Map) paramObject;
                processPage(parameter);
            }
        });

    }

    private void processPage(Map<String, Object> paramMap) {
        if (null == paramMap) {
            return;
        }
        if (!paramMap.containsKey("page") && !paramMap.containsKey("limit")) {
            return;
        }
        int page = 1;
        int rows = 10;
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            if ("page".equals(key)) {
                page = NumberUtils.toInt(value, page);
            } else if ("limit".equals(key)) {
                rows = NumberUtils.toInt(value, rows);
            }
        }
        int offset = (page - 1) * rows;
        paramMap.put("offset", offset);
        paramMap.put("rows", rows);
    }


    @After("paramAspect()")
    public void afterDataSource(JoinPoint joinPoint) {

    }
}
