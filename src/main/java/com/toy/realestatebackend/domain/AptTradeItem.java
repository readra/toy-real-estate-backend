package com.toy.realestatebackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AptTradeItem {
    private final String transactionAmount;
    private final String transactionType;
    private final int buildingYear;
    private final int year;
    private final int month;
    private final int day;
    private final String registrationDate;
    private final String legalBuilding;
    private final String apartmentName;
    private final double exclusiveArea;
    private final String agencyLocation;
    private final String localNumber;
    private final int lawdCode;
    private final int layer;
    private final String liftReasonDate;
    private final boolean isLift;

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
