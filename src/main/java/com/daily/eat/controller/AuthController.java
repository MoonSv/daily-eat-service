package com.daily.eat.controller;

import com.daily.eat.GlobalResult.GlobalResult;
import com.daily.eat.dao.User;
import com.daily.eat.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Map;

/**
 * @program: daily-eat
 * @description: Auth Controller
 * @author: Moon
 * @create: 2020-02-27 10:12
 **/
@RestController
public class AuthController {
    UserService userService;
    AuthenticationManager authenticationManager;

    @Inject
    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/auth")
    public Object auth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication == null?null:authentication.getName();
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return GlobalResult.fail("use have not logged in", null);
        } else {
            return GlobalResult.success("user have logged in", user);
        }
    }

    @PostMapping("/auth/register")
    public GlobalResult register(@RequestBody Map<String, String> usernameAndPassword) {
        String username = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");

        if (username == null || password == null) {
            return GlobalResult.fail("username or password cannot not null", null);
        }
        if (username.length() < 5 || username.length() > 20) {
            return GlobalResult.fail("username'length should between 5 and 20", null);
        }
        if (password.length() < 6 || password.length() > 20) {
            return GlobalResult.fail("password'length should between 6 and 20", null);
        }
        try {
            userService.save(username, password);
            return GlobalResult.success("register successfully", null);
        } catch (DuplicateKeyException e) {
            return GlobalResult.fail("username already exists", null);
        }
    }

    @PostMapping("/auth/login")
    public GlobalResult login(@RequestBody Map<String, String> usernameAndPassword) {
        String username = usernameAndPassword.get("username");
        String password = usernameAndPassword.get("password");
        UserDetails userDetails;
        try {
            userDetails = userService.loadUserByUsername(username);

        } catch (UsernameNotFoundException e) {
            return GlobalResult.fail("Username does not exist", null);
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        try {
            authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(token);
            User user = userService.getUserByUsername(username);
            return GlobalResult.success("login success", user);
        } catch (BadCredentialsException e) {
            return GlobalResult.fail("Wrong password", null);
        }
    }

    @GetMapping("/auth/logout")
    public GlobalResult logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByUsername(username);

        if (user == null) {
            return GlobalResult.fail("no user logged in", user);
        }

        SecurityContextHolder.clearContext();
        return GlobalResult.success("successfully logged out", user);
    }
}
