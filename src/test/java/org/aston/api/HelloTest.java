package org.aston.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HelloControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    void hello_should200_whenSuccess() throws Exception {
        mockMvc.perform(get("/hello")
                        .contentType(APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(APPLICATION_JSON),
                        jsonPath("$.data.message").value("Hello controller")
                );
    }
}