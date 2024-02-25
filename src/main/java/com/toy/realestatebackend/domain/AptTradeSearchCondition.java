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
    private final Integer lawdCode;
    // 조회년월 (시작)
    private final YearMonth startYearMonth;
    // 조회년월 (종료)
    private final YearMonth endYearMonth;
    // 조회가격 (시작)
    private final Integer startTransactionAmount;
    // 조회가격 (종료)
    private final Integer endTransactionAmount;

    @Builder
    public AptTradeSearchCondition(Integer lawdCode, YearMonth startYearMonth, YearMonth endYearMonth, Integer startTransactionAmount, Integer endTransactionAmount) {
        this.lawdCode = lawdCode;
        this.startYearMonth = startYearMonth;
        this.endYearMonth = endYearMonth;
        this.startTransactionAmount = startTransactionAmount;
        this.endTransactionAmount = endTransactionAmount;
    }

    /**
     * Redis Key 를 생성한다.
     * @param yearMonth
     *      년월
     * @return
     *      Redis Key
     */
    public String getRedisKey(YearMonth yearMonth) {
        return String.join("-", lawdCode.toString(), yearMonth.toString());
    }
}
