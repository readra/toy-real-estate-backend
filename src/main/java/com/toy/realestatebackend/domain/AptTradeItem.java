package com.toy.realestatebackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 아파트매매실거래 정보
 *
 * @author 김진용
 */
@Getter
@ToString
public class AptTradeItem {
    // 거래금액
    private final String transactionAmount;
    // 거래유형
    private final String transactionType;
    // 건축년도
    private final int buildingYear;
    // 년
    private final int year;
    // 월
    private final int month;
    // 일
    private final int day;
    // 등기일자
    private final String registrationDate;
    // 법정동
    private final String legalBuilding;
    // 아파트
    private final String apartmentName;
    // 전용면적
    private final double exclusiveArea;
    // 중개사소재지
    private final String agencyLocation;
    // 지번
    private final String localNumber;
    // 지역코드
    private final int lawdCode;
    // 층
    private final int layer;
    // 해제사유발생일
    private final String liftReasonDate;
    // 해제여부
    private final boolean isLift;

    /**
     * 생성자
     *
     * @param transactionAmount
     *      거래금액
     * @param transactionType
     *      거래유형
     * @param buildingYear
     *      건축년도
     * @param year
     *      년
     * @param month
     *      월
     * @param day
     *      일
     * @param registrationDate
     *      등기일자
     * @param legalBuilding
     *      법정동
     * @param apartmentName
     *      아파트
     * @param exclusiveArea
     *      전용면적
     * @param agencyLocation
     *      중개사소재지
     * @param localNumber
     *      지번
     * @param lawdCode
     *      지역코드
     * @param layer
     *      층
     * @param liftReasonDate
     *      해제사유발생일
     * @param isLift
     *      해제여부
     */
    @Builder
    public AptTradeItem(String transactionAmount, String transactionType, int buildingYear, int year, int month, int day, String registrationDate, String legalBuilding, String apartmentName, double exclusiveArea, String agencyLocation, String localNumber, int lawdCode, int layer, String liftReasonDate, boolean isLift) {
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.buildingYear = buildingYear;
        this.year = year;
        this.month = month;
        this.day = day;
        this.registrationDate = registrationDate;
        this.legalBuilding = legalBuilding;
        this.apartmentName = apartmentName;
        this.exclusiveArea = exclusiveArea;
        this.agencyLocation = agencyLocation;
        this.localNumber = localNumber;
        this.lawdCode = lawdCode;
        this.layer = layer;
        this.liftReasonDate = liftReasonDate;
        this.isLift = isLift;
    }
}
