package com.example.J2Eproject.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AuthenticationController authenticationController;

//    @Autowired
//    private WebApplicationContext wac;

//    @Autowired
//    private FilterChainProxy filterChainProxy;

//    @Before
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(filterChainProxy).build();
//    }

    @Test
    void should_return_user() throws Exception {
        String loginInfo = "{" +
                "\"username\":\"testUser\"," +
                "\"password\":\"test123\"," +
                "\"firstName\":\"user\"," +
                "\"lastName\":\"user\"," +
                "\"email\":\"user@test.com\"," +
                "\"role\":[\"user\"]" +
                "}";

        mockMvc.perform( MockMvcRequestBuilders
                .post("/auth/register")
                .content(loginInfo)
                .contentType("application/json")
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_user_token() throws Exception {
        String loginInfo = "{\"username\":\"test\",\"password\":\"test123\"}";

        ResultActions result = mockMvc.perform( MockMvcRequestBuilders
                .post("/auth/login")
                .content(loginInfo)
                .contentType("application/json")
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").exists());
//                .andExpect(content().contentType("application/json;charset=UTF-8"));

//        String resultString = result.andReturn().getResponse().getContentAsString();
//
//        System.out.println("\n\ntoken: " + result.andReturn().getResponse().getContentAsString() + "\n\n");

//        JacksonJsonParser jsonParser = new JacksonJsonParser();
//
//        String token = jsonParser.parseMap(resultString).get("accessToken").toString();
//
//        System.out.println("\n\ntoken: " + token + "\n\n");
    }
}
