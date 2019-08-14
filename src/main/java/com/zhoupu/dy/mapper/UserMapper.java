package com.zhoupu.dy.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import com.zhoupu.dy.dto.User;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where id=#{id}")
    User findOne(Long id);

    @Update("update t_user set realName=#{realName},userName=#{userName} where id=#{id}")
    void update(User user);

    @Insert("insert into t_user(userName,realName)values(#{userName},#{realName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("select * from t_user")
    List<User> getAll();
}
