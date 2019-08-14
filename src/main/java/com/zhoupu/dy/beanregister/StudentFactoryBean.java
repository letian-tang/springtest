package com.zhoupu.dy.beanregister;

import com.zhoupu.dy.beanregister.beans.Student;
import org.springframework.beans.factory.FactoryBean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentFactoryBean implements FactoryBean<Student> {

    public StudentFactoryBean() {
        log.info("");
    }

    /**
     * 当getObject方法第一次被调用时，去创建方法体中对象，并将 对象注入到spring容器中
     * 
     * @return
     * @throws Exception
     */
    @Override
    public Student getObject() throws Exception {
        Student student = new Student();
        student.setName("小瑞同学");
        return student;
    }

    @Override
    public Class<?> getObjectType() {
        return Student.class;
    }



}
