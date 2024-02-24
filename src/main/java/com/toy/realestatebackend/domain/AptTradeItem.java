package com.toy.realestatebackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 아파트매매실거래 정보
 *
 * @author 김진용
 */
@Getter
@ToString
@NoArgsConstructor
public class AptTradeItem {
    // 거래금액
    private String transactionAmount;
    // 건축년도
    private int buildingYear;
    // 년
    private int year;
    // 월
    private int month;
    // 일
    private int day;
    // 법정동
    private String legalBuilding;
    // 아파트
    private String apartmentName;
    // 전용면적
    private double exclusiveArea;
    // 지번
    private String localNumber;
    // 지역코드
    private String lawd;
    // 층
    private int layer;

    /**
     * 생성자
     *
     * @param transactionAmount
     *      거래금액
     * @param buildingYear
     *      건축년도
     * @param year
     *      년
     * @param month
     *      월
     * @param day
     *      일
     * @param legalBuilding
     *      법정동
     * @param apartmentName
     *      아파트
     * @param exclusiveArea
     *      전용면적
     * @param localNumber
     *      지번
     * @param lawd
     *      지역코드
     * @param layer
     *      층
     */
    @Builder
    public AptTradeItem(String transactionAmount, int buildingYear, int year, int month, int day, String legalBuilding, String apartmentName, double exclusiveArea, String localNumber, String lawd, int layer) {
        this.transactionAmount = transactionAmount;
        this.buildingYear = buildingYear;
        this.year = year;
        this.month = month;
        this.day = day;
        this.legalBuilding = legalBuilding;
        this.apartmentName = apartmentName;
        this.exclusiveArea = exclusiveArea;
        this.localNumber = localNumber;
        this.lawd = lawd;
        this.layer = layer;
    }

    /**
     * 아파트매매실거래 유효성 검사
     *
     * @param aptTradeSearchCondition
     *      아파트매매실거래 검색 조건
     * @return
     *      아파트매매실거래 유효성 여부
     */
    public boolean isValid(AptTradeSearchCondition aptTradeSearchCondition) {
        if ( null == aptTradeSearchCondition.getStartTransactionAmount() && null == aptTradeSearchCondition.getEndTransactionAmount() ) {
            return true;
        }

        int amount = Integer.parseInt(this.transactionAmount.trim().replaceAll(",", ""));

        if ( null == aptTradeSearchCondition.getEndTransactionAmount() ) {
            return aptTradeSearchCondition.getStartTransactionAmount() <= amount;
        } else {
            if ( null == aptTradeSearchCondition.getStartTransactionAmount() ) {
                return aptTradeSearchCondition.getEndTransactionAmount() >= amount;
            } else {
                return aptTradeSearchCondition.getStartTransactionAmount() <= amount && aptTradeSearchCondition.getEndTransactionAmount() >= amount;
            }
        }
    }
}
