package com.zhoupu.dy.meh2;

import com.zhoupu.dy.method.Student;

public class Te {

    public void m1(Student s) {
        m2(s);
        System.out.println(s.getName());
    }


    public void m2(Student s) {
        s.setName("22222");
    }


    public void m3(String s) {
        m4(s);
        System.out.println(s);
    }


    public void m4(String s) {
        s = "4444444444";
    }


    public void m5(Student s) {
        m6(s);
        System.out.println(s.getName());
    }


    public void m6(Student s) {
        s = new Student("6666666");
    }

    public static void main(String[] args) {
        new Te().m1(new Student("111111"));
        new Te().m3("333333");
        new Te().m5(new Student("555555555"));
    }
}
