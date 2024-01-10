package com.toy.realestatebackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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
    private final YearMonth startYearMonth;
    // 조회년월 (종료)
    private final YearMonth endYearMonth;
    // 조회가격 (시작)
    private final int startTransactionAmount;
    // 조회가격 (종료)
    private final int endTransactionAmount;

    @Builder
    public AptTradeSearchCondition(int lawdCode, YearMonth startYearMonth, YearMonth endYearMonth, int startTransactionAmount, int endTransactionAmount) {
        this.lawdCode = lawdCode;
        this.startYearMonth = startYearMonth;
        this.endYearMonth = endYearMonth;
        this.startTransactionAmount = startTransactionAmount;
        this.endTransactionAmount = endTransactionAmount;
    }
}
