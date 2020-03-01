package com.daily.eat.controller;

import com.daily.eat.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class AuthControllerTest {

    MockMvc mockMvc;

    @Mock
    UserService userService;

    @Mock
    AuthenticationManager authenticationManage;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AuthController(userService, authenticationManage)).build();
    }

    @Test
    void returnNotLoginByDefault() throws Exception {
        mockMvc.perform(get("/auth")).andExpect(status().isOk())
                .andExpect(mvcResult ->
                                Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("use have not logged in")));
    }
}