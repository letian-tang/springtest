package com.zhoupu.dy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import com.zhoupu.dy.dto.User;
import com.zhoupu.dy.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class DyApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void insertOne() {
        User user = User.builder().userName("tangdingyi").realName("汤定一").build();
        userService.insert(user);
        log.info("user={}", user);
    }

    @Test
    public void getOne() {
        User user = userService.getOne(2L);
        log.info("user={}", user);
    }


    @Test
    public void updateOne() {
        User user = User.builder().id(2L).userName("zaizai").realName("仔仔").build();
        userService.update(user);
    }

    @Test
    public void testNumber() {
        Integer i = userService.<Integer>getNumber("");
        log.info("{}", i);
    }

    @Test
    public void getAll() {
        List<User> users = userService.getAll();
        log.info("user={}", users);
    }


    @Test
    public void testListLambda() {}

}
