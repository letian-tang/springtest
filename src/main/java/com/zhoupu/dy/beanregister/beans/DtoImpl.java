package com.zhoupu.dy.beanregister.beans;

public class DtoImpl implements IDto {

    private String name;

    public DtoImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
