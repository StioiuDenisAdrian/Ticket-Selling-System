package com.example.main;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controller.TicketController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

@SpringBootTest(
        classes = TicketController.class)
@AutoConfigureMockMvc
public class TestTicketExceeding  {
    @Autowired
    private MockMvc mvc;
    @Test
    void addTicket() throws Exception {
        final ResultActions result = mvc.perform(post("/sellTicket").content("{ \"concertId\":1 ,\"email\":\"test@test.com\",\"participant\":\"Tester\", \"tickets\":200000}").contentType(MediaType.APPLICATION_JSON));
        result.andExpect(status().isBadRequest()).andExpect(content()
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$", is("Not enough tickets available")));
    }
}
