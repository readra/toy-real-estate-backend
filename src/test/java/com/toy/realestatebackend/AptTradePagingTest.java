package com.toy.realestatebackend;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class AptTradePagingTest {
    @Test
    public void aptTradeTest() throws IOException, ParserConfigurationException, SAXException {
        StringBuilder stringBuilder = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade"); /*URL*/
        stringBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + "%2F7MeSbybd07ucEmj8BF72GmhsZV9KbqQ2BTpylshbKDKGNzSktYgCYvTOkvKuZCxWc8WHA5B3ecQ9qld7%2BGjOw%3D%3D"); /*Service Key*/
        stringBuilder.append("&" + URLEncoder.encode("LAWD_CD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("11110", StandardCharsets.UTF_8)); /*각 지역별 코드*/
        stringBuilder.append("&" + URLEncoder.encode("DEAL_YMD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("201512", StandardCharsets.UTF_8)); /*월 단위 신고자료*/

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(stringBuilder.toString());
        document.getDocumentElement().normalize();

        int totalPageSize = 1;

        Node pageNo = document.getElementsByTagName("body").item(0);
        if ( pageNo.getNodeType() == Node.ELEMENT_NODE ) {
            Element element = (Element) pageNo;

            totalPageSize = Math.floorDiv(Integer.parseInt(getElementByTagName(element, "totalCount")), Integer.parseInt(getElementByTagName(element, "numOfRows"))) + 1;

            System.out.println("TOTAL PAGE SIZE : " + totalPageSize);
        }

        Set<Item> items = new HashSet<>();
        for ( int i = 1; i <= totalPageSize; i++ ) {
            StringBuilder sb = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade"); /*URL*/
            sb.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + "%2F7MeSbybd07ucEmj8BF72GmhsZV9KbqQ2BTpylshbKDKGNzSktYgCYvTOkvKuZCxWc8WHA5B3ecQ9qld7%2BGjOw%3D%3D"); /*Service Key*/
            sb.append("&" + URLEncoder.encode("LAWD_CD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("11110", StandardCharsets.UTF_8)); /*각 지역별 코드*/
            sb.append("&" + URLEncoder.encode("DEAL_YMD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("201512", StandardCharsets.UTF_8)); /*월 단위 신고자료*/
            sb.append("&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(String.valueOf(i), StandardCharsets.UTF_8));

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document itemDocument = documentBuilder.parse(sb.toString());
            itemDocument.getDocumentElement().normalize();

            NodeList nodeList = itemDocument.getElementsByTagName("item");
            for ( int j = 0; j < nodeList.getLength(); j++ ) {
                Node node = nodeList.item(j);
                if ( node.getNodeType() == Node.ELEMENT_NODE ) {
                    Element element = (Element) node;

                    items.add(
                            Item.builder()
                                    .transactionAmount(getElementByTagName(element, "거래금액"))
                                    .transactionType(getElementByTagName(element, "거래유형"))
                                    .buildingYear(Integer.parseInt(getElementByTagName(element, "건축년도")))
                                    .year(Integer.parseInt(getElementByTagName(element, "년")))
                                    .month(Integer.parseInt(getElementByTagName(element, "월")))
                                    .day(Integer.parseInt(getElementByTagName(element, "일")))
                                    .registrationDate(getElementByTagName(element, "등기일자"))
                                    .legalBuilding(getElementByTagName(element, "법정동"))
                                    .apartmentName(getElementByTagName(element, "아파트"))
                                    .exclusiveArea(Double.parseDouble(getElementByTagName(element, "전용면적")))
                                    .agencyLocation(getElementByTagName(element, "중개사소재지"))
                                    .localNumber(getElementByTagName(element, "지번"))
                                    .lawdCode(Integer.parseInt(getElementByTagName(element, "지역코드")))
                                    .layer(Integer.parseInt(getElementByTagName(element, "층")))
                                    .liftReasonDate(getElementByTagName(element, "해제사유발생일"))
                                    .isLift(Boolean.parseBoolean(getElementByTagName(element, "해제여부")))
                                    .build()
                    );
                }
            }
        }

        for ( Item item : items ) System.out.println(item.toString());

        System.out.println("TOTAL ITEM SIZE : " + items.size());
    }

    public String getElementByTagName(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue();
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    static class Item {
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
        public Item(String transactionAmount, String transactionType, int buildingYear, int year, int month, int day, String registrationDate, String legalBuilding, String apartmentName, double exclusiveArea, String agencyLocation, String localNumber, int lawdCode, int layer, String liftReasonDate, boolean isLift) {
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
}
