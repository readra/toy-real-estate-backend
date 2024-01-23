package com.toy.realestatebackend.controller;

import com.toy.realestatebackend.domain.AptTradeItem;
import com.toy.realestatebackend.domain.AptTradeSearchCondition;
import com.toy.realestatebackend.enums.LawdGuType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * 아파트매매실거래 Controller Layer
 *
 * @author 김진용
 */
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AptTradeController {
    /**
     * 아파트매매실거래 목록 API
     *
     * @return
     *      아파트매매실거래 목록
     */
    @GetMapping("/api/apartment")
    public List<AptTradeItem> apartment(AptTradeSearchCondition aptTradeSearchCondition) {
        List<AptTradeItem> aptTradeItems = new LinkedList<>();

        try {
            YearMonth nowYearMonth = aptTradeSearchCondition.getStartYearMonth();

            while ( true == nowYearMonth.isBefore(aptTradeSearchCondition.getEndYearMonth()) || true == nowYearMonth.equals(aptTradeSearchCondition.getEndYearMonth()) ) {
                try {
                    StringBuilder stringBuilder = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade"); /*URL*/
                    stringBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + "%2F7MeSbybd07ucEmj8BF72GmhsZV9KbqQ2BTpylshbKDKGNzSktYgCYvTOkvKuZCxWc8WHA5B3ecQ9qld7%2BGjOw%3D%3D"); /*Service Key*/
                    stringBuilder.append("&" + URLEncoder.encode("LAWD_CD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(Integer.toString(aptTradeSearchCondition.getLawdCode()), StandardCharsets.UTF_8)); /*각 지역별 코드*/
                    stringBuilder.append("&" + URLEncoder.encode("DEAL_YMD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(nowYearMonth.format(DateTimeFormatter.ofPattern("yyyyMM")), StandardCharsets.UTF_8)); /*월 단위 신고자료*/

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document document = builder.parse(stringBuilder.toString());
                    document.getDocumentElement().normalize();

                    NodeList nodeList = document.getElementsByTagName("item");
                    for ( int idx = 0; idx < nodeList.getLength(); idx++ ) {
                        Node node = nodeList.item(idx);
                        if ( Node.ELEMENT_NODE == node.getNodeType() ) {
                            Element element = (Element) node;

                            AptTradeItem aptTradeItem = AptTradeItem.builder()
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
                                    .lawd(LawdGuType.codeOf(Integer.parseInt(getElementByTagName(element, "지역코드"))).getName())
                                    .layer(Integer.parseInt(getElementByTagName(element, "층")))
                                    .liftReasonDate(getElementByTagName(element, "해제사유발생일"))
                                    .isLift(Boolean.parseBoolean(getElementByTagName(element, "해제여부")))
                                    .build();

                            if ( false == aptTradeItem.isValid(aptTradeSearchCondition) ) {
                                continue;
                            }

                            aptTradeItems.add(aptTradeItem);
                        }
                    }
                } finally {
                    nowYearMonth = nowYearMonth.plusMonths(1);
                }
            }
        } catch ( Exception e ) {
            log.error("Failed to parse date format.", e);
        }

        return aptTradeItems;
    }

    /**
     * 태그명 기준 XML Element 를 취득한다.
     *
     * @param element
     *      XML Element
     * @param tagName
     *      태그명
     * @return
     *      XML Element
     */
    public String getElementByTagName(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue();
    }
}
