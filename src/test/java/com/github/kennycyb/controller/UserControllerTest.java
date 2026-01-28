package com.github.kennycyb.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.kennycyb.model.User;
import com.github.kennycyb.model.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository repository;

    private static final String LOGIN_URL = "/user/login";
    private static final String USERNAME_PARAM = "username";
    private static final String PASSWORD_PARAM = "password";
    private static final String TEST_USER = "test";
    private static final String FIRST_NAME = "Hello";
    private static final String LAST_NAME = "World";

    @Test
    public void loginShouldReturnUserWhenPasswordValid() throws Exception {
        User user = new User(TEST_USER, FIRST_NAME, LAST_NAME);
        user.id = "1";
        String rawPassword = "testPassword123";
        user.passwordHash = new BCryptPasswordEncoder().encode(rawPassword);

        when(repository.findByUserName(TEST_USER)).thenReturn(user);

        this.mockMvc.perform(get(LOGIN_URL).param(USERNAME_PARAM, TEST_USER).param(PASSWORD_PARAM, rawPassword))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.userName").value(TEST_USER))
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME));
    }

    @Test
    public void loginShouldReturnUnauthorizedWhenPasswordInvalid() throws Exception {
        User user = new User(TEST_USER, FIRST_NAME, LAST_NAME);
        user.id = "1";
        user.passwordHash = new BCryptPasswordEncoder().encode("correctPassword");

        when(repository.findByUserName(TEST_USER)).thenReturn(user);

        this.mockMvc.perform(get(LOGIN_URL).param(USERNAME_PARAM, TEST_USER).param(PASSWORD_PARAM, "wrongPassword"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void loginShouldReturnNotFoundWhenUserMissing() throws Exception {
        when(repository.findByUserName("missing")).thenReturn(null);

        this.mockMvc.perform(get(LOGIN_URL).param(USERNAME_PARAM, "missing").param(PASSWORD_PARAM, "anyPassword"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void loginShouldReturnBadRequestWhenUsernameMissing() throws Exception {
        this.mockMvc.perform(get(LOGIN_URL).param(PASSWORD_PARAM, "anyPassword"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginShouldReturnBadRequestWhenPasswordMissing() throws Exception {
        this.mockMvc.perform(get(LOGIN_URL).param(USERNAME_PARAM, TEST_USER))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginShouldReturnBadRequestWhenBothParamsMissing() throws Exception {
        this.mockMvc.perform(get(LOGIN_URL))
                .andExpect(status().isBadRequest());
    }
}
