package com.toy.realestatebackend.service;

import com.toy.realestatebackend.domain.AptTradeItem;
import com.toy.realestatebackend.domain.AptTradeSearchCondition;
import com.toy.realestatebackend.enums.LawdDongType;
import com.toy.realestatebackend.enums.LawdGuType;
import com.toy.realestatebackend.enums.LawdSiType;
import com.toy.realestatebackend.enums.LawdType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
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
 * 아파트매매실거래 Service Layer
 *
 * @author 김진용
 */
@Slf4j
@Service
public class AptTradeService {
    private final RedisService redisService;

    @Autowired
    public AptTradeService(final RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 아파트매매실거래 목록 조회
     *
     * @param aptTradeSearchCondition
     *      아파트매매실거래 조회 조건
     * @return
     *      아파트매매실거래 목록
     */
    public Pair<YearMonth, List<AptTradeItem>> findAptTradeItems(AptTradeSearchCondition aptTradeSearchCondition) {
        List<AptTradeItem> aptTradeItems = new LinkedList<>();
        YearMonth nowYearMonth = aptTradeSearchCondition.getStartYearMonth();

        try {
            while ( true == nowYearMonth.isBefore(aptTradeSearchCondition.getEndYearMonth()) || true == nowYearMonth.equals(aptTradeSearchCondition.getEndYearMonth()) ) {
                try {
                    if ( aptTradeSearchCondition.getItemCount() <= aptTradeItems.size() ) {
                        break;
                    }

                    // 검색을 위한 Redis key 생성
                    String redisKey = aptTradeSearchCondition.getRedisKey(nowYearMonth);
                    // Redis key 기준 아파트매매실거래 목록 조회
                    List<AptTradeItem> subAptTradeItems = null; //redisService.getValues(redisKey, AptTradeItem.class);

                    if ( null == subAptTradeItems || true == subAptTradeItems.isEmpty() ) {
                        log.info("Empty key. [KEY]{}, [ITEM]{}", redisKey, subAptTradeItems);

                        // 아파트매매실거래 목록 미존재할 경우, Open API 로 부터 아파트실거래 목록 조회
                        subAptTradeItems = findAptTradeItemFromOpenApi(aptTradeSearchCondition, nowYearMonth);
                        // Redis 저장
//                        redisService.setValues(redisKey, subAptTradeItems);
                    }

                    aptTradeItems.addAll(subAptTradeItems);
                } finally {
                    nowYearMonth = nowYearMonth.plusMonths(1);
                }
            }
        } catch ( Exception e ) {
            log.error("Failed to find apt trade item. [YM]{}, [COND]{}", nowYearMonth, aptTradeSearchCondition, e);
        }

        return Pair.of(nowYearMonth, aptTradeItems);
    }

    /**
     * Open API 로 부터 아파트매매실거래 목록 조회
     *
     * @param aptTradeSearchCondition
     *      아파트매매실거래 조회 조건
     * @param nowYearMonth
     *      검색년월
     * @return
     *      아파트매매실거래 목록 조회
     * @throws Exception
     *      Exception
     */
    public List<AptTradeItem> findAptTradeItemFromOpenApi(AptTradeSearchCondition aptTradeSearchCondition, YearMonth nowYearMonth) throws Exception {
        List<AptTradeItem> aptTradeItems = new LinkedList<>();

        LawdType lawdType;
        boolean isDongType = false;

        // 검색 조건 내, 지역 코드 기준 지역 코드 enum 확인
        if ( null == (lawdType = LawdSiType.codeOf(aptTradeSearchCondition.getLawdCode())) ) {
            if ( null == (lawdType = LawdGuType.codeOf(aptTradeSearchCondition.getLawdCode())) ) {
                if ( null == (lawdType = LawdDongType.codeOf(aptTradeSearchCondition.getLawdCode())) ) {
                    throw new IllegalArgumentException("Unsupported lawd code. [CODE]" + aptTradeSearchCondition.getLawdCode());
                } else {
                    isDongType = true;
                }
            }
        }

        int lawdCode = lawdType.getCode();
        // LawdDongType 일 경우, lawdCode substring
        if ( true == isDongType ) {
            lawdCode = Integer.parseInt(Integer.toString(aptTradeSearchCondition.getLawdCode()).substring(0, 5));
        }

        StringBuilder stringBuilder = new StringBuilder("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade"); /*URL*/
        stringBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=" + "%2F7MeSbybd07ucEmj8BF72GmhsZV9KbqQ2BTpylshbKDKGNzSktYgCYvTOkvKuZCxWc8WHA5B3ecQ9qld7%2BGjOw%3D%3D"); /*Service Key*/
        stringBuilder.append("&" + URLEncoder.encode("LAWD_CD", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(Integer.toString(lawdCode), StandardCharsets.UTF_8)); /*각 지역별 코드*/
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
                        .buildingYear(Integer.parseInt(getElementByTagName(element, "건축년도")))
                        .year(Integer.parseInt(getElementByTagName(element, "년")))
                        .month(Integer.parseInt(getElementByTagName(element, "월")))
                        .day(Integer.parseInt(getElementByTagName(element, "일")))
                        .legalBuilding(getElementByTagName(element, "법정동"))
                        .apartmentName(getElementByTagName(element, "아파트"))
                        .exclusiveArea(Double.parseDouble(getElementByTagName(element, "전용면적")))
                        .localNumber(getElementByTagName(element, "지번"))
                        .lawd(LawdGuType.codeOf(Integer.parseInt(getElementByTagName(element, "지역코드"))).getName())
                        .layer(Integer.parseInt(getElementByTagName(element, "층")))
                        .build();

                // LawdDongType 일 경우, aptTradeItem 필터링
                // 검색 조건에 부합하지 않을 경우 필터링
                if ( (true == isDongType && false == lawdType.getName().equalsIgnoreCase(aptTradeItem.getLegalBuilding())) || (false == aptTradeItem.isValid(aptTradeSearchCondition)) ) {
                    continue;
                }

                aptTradeItems.add(aptTradeItem);
            }
        }

        return aptTradeItems;
    }

    /**
     * 태그명 기준 XML Element 취득
     *
     * @param element
     *      XML Element
     * @param tagName
     *      태그명
     * @return
     *      XML Element
     */
    public String getElementByTagName(Element element, String tagName) {
        String result = element.getElementsByTagName(tagName).item(0).getChildNodes().item(0).getNodeValue();

        return (null == result || true == result.isBlank()) ? result : result.trim();
    }
}
