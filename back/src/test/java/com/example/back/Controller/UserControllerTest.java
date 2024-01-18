package com.example.back.Controller;

import com.example.back.domain.dto.UserJoinRequest;
import com.example.back.domain.dto.UserLoginRequest;
import com.example.back.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest{

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;
    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser(username = "joon", password = "1234", roles = "USER")
    void join() throws Exception{
        mockMvc.perform(post("/api/users/join")
                        .with(csrf())//spring security 사용시 테스트할 때 필요
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest("yerang","7777"))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 성공")
    void login() throws Exception{
        mockMvc.perform(post("/api/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest("joon","1234"))))
                .andDo(print())
                .andExpect(status().isOk());
    }
}