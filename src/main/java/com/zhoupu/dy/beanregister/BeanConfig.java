package com.zhoupu.dy.beanregister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.zhoupu.dy.beanregister.beans.Teacher;

@Configuration
@Import({Teacher.class})
public class BeanConfig {


    @Bean("studentFactoryBean")
    public StudentFactoryBean studentFactoryBean() {
        return new StudentFactoryBean();
    }

}
