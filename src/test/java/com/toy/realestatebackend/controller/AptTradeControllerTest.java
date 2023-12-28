package com.toy.realestatebackend.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AptTradeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/api";

    @Test
    @DisplayName("아파트 매매 신고정보 조회 테스트")
    void apartment_test() throws Exception {
        mockMvc.perform(get(BASE_URL + "/apartment"))
                .andExpect(status().isOk());
    }
}
