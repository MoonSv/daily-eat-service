package com.daily.eat.mapper;

import com.daily.eat.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @program: daily-eat
 * @description: mapper for user
 * @author: Moon
 * @create: 2020-03-01 00:22
 **/
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE NAME = #{name}")
    User getUserByName(@Param("name") String name);

    @Select("INSERT INTO user(name, encrypted_password, created_at, updated_at) VALUES (#{name}, #{pwd}, now(), now())")
    void save(@Param("name") String username, @Param("pwd") String pwd);
}
