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
 * Unit tests for SystemController.
 * Tests the system health check endpoints for readiness and liveness.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SystemController.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class SystemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String READY_URL = "/system/ready";
    private static final String ALIVE_URL = "/system/alive";

    /**
     * Test the /system/ready endpoint returns OK status.
     */
    @Test
    public void readyShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get(READY_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status").value("OK"));
    }

    /**
     * Test the /system/alive endpoint returns ALIVE status.
     */
    @Test
    public void aliveShouldReturnAliveStatus() throws Exception {
        mockMvc.perform(get(ALIVE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status").value("ALIVE"));
    }
}