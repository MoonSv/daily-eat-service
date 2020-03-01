package com.daily.eat.service;

import com.daily.eat.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: daily-eat
 * @description: service for user
 * @author: Moon
 * @create: 2020-02-29 21:08
 **/
@Service
public class UserService implements UserDetailsService {

    private Map<String, String> passwordMap = new ConcurrentHashMap<>();

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserMapper userMapper;
    @Inject
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
//        save("hello", "world");
    }

    public void save(String username, String password) {
        userMapper.save(username, bCryptPasswordEncoder.encode(password));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.daily.eat.dao.User user = getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + "does not exsit");
        }
        String password = user.getEncryptedPassword();

        return new User(username, password, Collections.emptyList());

    }

    public com.daily.eat.dao.User getUserByUsername(String username) {
        return userMapper.getUserByName(username);
    }
}
