package com.daily.eat.controller;

import com.daily.eat.GlobalResult.GlobalResult;
import com.daily.eat.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: daily-eat
 * @description: Auth Controller
 * @author: Moon
 * @create: 2020-02-27 10:12
 **/
@Controller
public class AuthController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/")
    @ResponseBody
    public Object d() {
        return "haha";
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public Object login(@RequestBody Map<String, Object> usernameAndPassword) {
        String username = (String) usernameAndPassword.get("username");
        String password = (String) usernameAndPassword.get("password");
        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);

        } catch (UsernameNotFoundException e) {
            return GlobalResult.fail("Username does not exist", null);
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        try {
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);
            User user = new User("1", "77", "male");
            return GlobalResult.success(user);
        } catch (BadCredentialsException e) {
            return GlobalResult.fail("Wrong password", null);
        }
    }
}
