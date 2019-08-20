package com.zhoupu.dy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import com.zhoupu.dy.beanregister.beans.Foo;
import com.zhoupu.dy.beanregister.beans.Student;
import com.zhoupu.dy.beanregister.beans.Teacher;
import com.zhoupu.dy.beanregister.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 字体什么样子
 */
@SpringBootApplication
@Slf4j
public class DyApplication implements CommandLineRunner {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    public static void main(String[] args) {
        SpringApplication.run(DyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student s = context.getBean(Student.class);
        log.info("{}", s);

        Teacher t = context.getBean(Teacher.class);
        t.setName("老师");
        log.info("{}", t);

        User user = context.getBean(User.class);
        log.info("{}", user);

        Foo foo = context.getBean(Foo.class);
        log.info("{}", foo);

        log.info("taskExecutor={}", taskExecutor);

    }

}
