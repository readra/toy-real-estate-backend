package com.toy.realestatebackend.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.realestatebackend.domain.AptTradeItem;
import com.toy.realestatebackend.domain.CommonListCountResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class AptTradeControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    private static final String BASE_URL = "/api";

    @Test
    @DisplayName("아파트 매매 신고정보 조회 테스트")
    void apartment_test() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(BASE_URL + "/apartment?lawdCode={lawdCode}&startYearMonth={startYearMonth}&endYearMonth={endYearMonth}&itemCount={itemCount}",
                        11740,
                        YearMonth.parse("201501", DateTimeFormatter.ofPattern("yyyyMM")),
                        YearMonth.parse("201501", DateTimeFormatter.ofPattern("yyyyMM")),
                        100))
                .andExpect(status().isOk())
                .andReturn();

        CommonListCountResponse<AptTradeItem> aptTradeItems = objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});

        assertThat(aptTradeItems.getResults()).isNotEmpty();
        assertThat(aptTradeItems.getTotalCount()).isGreaterThan(0);
    }
}
