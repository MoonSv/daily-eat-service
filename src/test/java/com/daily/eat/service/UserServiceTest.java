package com.daily.eat.service;

import com.daily.eat.dao.User;
import com.daily.eat.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    BCryptPasswordEncoder mockEncoder;
    @Mock
    UserMapper mockUserMapper;
    @InjectMocks
    UserService userService;

    @Test
    public void testSave() {
        // 调用userservice
        // 验证userservice将请求转发给了usermapper
        when(mockEncoder.encode("myPassword")).thenReturn("encodedPassword");
        userService.save("myUser", "myPassword");
        verify(mockUserMapper).save("myUser", "encodedPassword");
    }

    @Test
    public void testGetByUserName() {
        userService.getUserByUsername("myUser");
        verify(mockUserMapper).getUserByName("myUser");
    }

    @Test
    public void testUsernameNotFoundException() {
        when(mockUserMapper.getUserByName("myUser")).thenReturn(null);
        Assertions.assertThrows(UsernameNotFoundException.class, ()-> {
           userService.loadUserByUsername("myUser");
        });
    }

    @Test
    public void testLoadUserByUserName() {
        when(mockUserMapper.getUserByName("myUser")).thenReturn(new User("1", "myUser", "password"));
        UserDetails userDetails = userService.loadUserByUsername("myUser");
        Assertions.assertEquals(userDetails.getPassword(), "password");
        Assertions.assertEquals(userDetails.getUsername(), "myUser");
    }
}