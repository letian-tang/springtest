package com.zhoupu.dy.idgenerator;

import org.junit.Test;
import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;
import org.springframework.util.SimpleIdGenerator;

public class IdGeneratorTest {

    @Test
    public void simpleIdGeneratorTest() {
        IdGenerator idGenerator = new SimpleIdGenerator();
        String id = idGenerator.generateId().toString();
        System.out.println(id);
    }

    @Test
    public void alternativeJdkIdGeneratorTest() {
        IdGenerator idGenerator = new AlternativeJdkIdGenerator();
        String id = idGenerator.generateId().toString();
        System.out.println(id);
    }
}
