package com.github.kennycyb.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Unit tests for GreetingController.
 * Tests the /greeting endpoint for default and custom messages, and ID incrementation.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String GREETING_URL = "/greeting";

    /**
     * Test that the greeting endpoint returns the default message "HELLO, WORLD!" with ID 1.
     */
    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get(GREETING_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("HELLO, WORLD!"))
                .andExpect(jsonPath("$.id").value(1));
    }

    /**
     * Test that the greeting endpoint returns a custom message with the provided name in uppercase.
     */
    @Test
    public void greetingShouldReturnCustomMessage() throws Exception {
        this.mockMvc.perform(get(GREETING_URL).param("name", "Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("HELLO, TEST!"))
                .andExpect(jsonPath("$.id").value(1));
    }

    /**
     * Test that the greeting ID increments with each call.
     */
    @Test
    public void greetingShouldIncrementId() throws Exception {
        // First call
        this.mockMvc.perform(get(GREETING_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        // Second call
        this.mockMvc.perform(get(GREETING_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }
}