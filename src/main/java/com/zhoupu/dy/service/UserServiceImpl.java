package com.zhoupu.dy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.zhoupu.dy.dto.User;
import com.zhoupu.dy.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "user", key = "#id")
    public User getOne(Long id) {
        return userMapper.findOne(id);
    }

    @Override
    @CachePut(value = "user", key = "#user.id")
    public User update(User user) {
        userMapper.update(user);
        return user;
    }

    @Override
    public User insert(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    public <N> N getNumber(String attributeName) {
        return (N) new Integer(12);
    }


    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
}
