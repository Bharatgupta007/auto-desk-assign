package com.beta.replyservice.controller;

import com.beta.replyservice.ReplyV2Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@ExtendWith(SpringExtension.class)
@WebMvcTest(ReplyV2Controller.class)
@Import(ReplyV2Controller.class)

public class ReplyV2ControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void emptyMessageTesting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Message is empty"))
                .andReturn();
    }

    @Test
    public void correctMessageTesting1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/11-kbzw9ru"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("kbzw9ru"))
                .andReturn();
    }

    @Test
    public void correctMessageTesting2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/12-kbzw9ru"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("5a8973b3b1fafaeaadf10e195c6e1dd4"))
                .andReturn();
    }

    @Test
    public void correctMessageTesting3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/22-kbzw9ru"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("e8501e64cf0a9fa45e3c25aa9e77ffd5"))
                .andReturn();
    }

    @Test
    public void invalidMessageTesting1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/22-"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid Input"))
                .andReturn();
    }

    @Test
    public void invalidMessageTesting2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/13-kbzw9ru"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid Input"))
                .andReturn();
    }
}
