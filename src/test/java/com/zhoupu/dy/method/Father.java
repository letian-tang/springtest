package com.zhoupu.dy.method;

public abstract class Father {
    abstract Object test(Object o);
}


class Son extends Father {

    @Override
    String test(Object o) {
        return "";
    }
}
