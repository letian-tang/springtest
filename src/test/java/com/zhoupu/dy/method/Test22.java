package com.zhoupu.dy.method;

interface TInter {
    <T> T getValue(T o);
}


class Class5 implements TInter {


    @Override
    public <T> T getValue(T o) {
        return null;
    }
}


public class Test22 {
    public static void main(String[] args) {
        TInter inter = new Class5();
        inter.getValue("ss");
    }
}

