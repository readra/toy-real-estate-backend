package com.toy.realestatebackend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

/**
 * 아파트 매매 신고정보 조회 조건
 *
 * @author 김진용
 */
@Getter
@ToString
public class AptTradeSearchCondition {
    // 지역명
    private final int lawdCode;
    // 조회년월 (시작)
    @DateTimeFormat(pattern = "yyyyMM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMM", timezone = "Asia/Seoul")
    private final YearMonth startYearMonth;
    // 조회년월 (종료)
    @DateTimeFormat(pattern = "yyyyMM")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMM", timezone = "Asia/Seoul")
    private final YearMonth endYearMonth;

    @Builder
    public AptTradeSearchCondition(int lawdCode, YearMonth startYearMonth, YearMonth endYearMonth) {
        this.lawdCode = lawdCode;
        this.startYearMonth = startYearMonth;
        this.endYearMonth = endYearMonth;
    }
}
