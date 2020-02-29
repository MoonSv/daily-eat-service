package com.daily.eat.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: daily-eat
 * @description: user entity
 * @author: Moon
 * @create: 2020-02-29 17:34
 **/
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String name;
    private String gender;

    public User(String id, String name, String gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }
}
