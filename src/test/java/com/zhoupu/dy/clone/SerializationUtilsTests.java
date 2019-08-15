package com.zhoupu.dy.clone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.springframework.util.SerializationUtils;

public class SerializationUtilsTests {


    @Test
    @SuppressWarnings("unchecked")
    public void copyList() throws Exception {
        List<Student> students = new ArrayList<>();
        students.add(new Student(100L, "张三"));
        students.add(new Student(101L, "李四"));
        students.add(new Student(102L, "王五"));
        byte[] bytes = SerializationUtils.serialize(students);
        List<Student> copyStudents = (List<Student>) SerializationUtils.deserialize(bytes);
        copyStudents.get(1).setName("李四修改");
        System.out.println(students);
        System.out.println(copyStudents);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void copyMap() throws Exception {
        Map<Integer, Student> map = new HashMap<>();
        map.put(1,new Student(100L, "张三"));
        map.put(2,new Student(101L, "李四"));
        map.put(3,new Student(102L, "王五"));
        byte[] bytes = SerializationUtils.serialize(map);
        Map<Integer, Student> copyMap = (Map<Integer, Student>) SerializationUtils.deserialize(bytes);
        copyMap.get(2).setName("李四修改map");
        System.out.println(map);
        System.out.println(copyMap);
    }
}
