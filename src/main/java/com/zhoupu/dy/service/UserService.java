package com.zhoupu.dy.service;

import com.zhoupu.dy.dto.User;

import java.util.List;

public interface UserService {

    User getOne(Long id);

    User update(User user);

    User insert(User user);

    <N> N getNumber(String attributeName);

    List<User> getAll();

}
