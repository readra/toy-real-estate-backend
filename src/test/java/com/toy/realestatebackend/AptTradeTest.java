package com.toy.realestatebackend;

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
import java.util.LinkedList;
import java.util.List;

public class AptTradeTest {
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

        List<Item> items = new LinkedList<>();
        NodeList nodeList = document.getElementsByTagName("item");
        for ( int i = 0; i < nodeList.getLength(); i++ ) {
            Node node = nodeList.item(i);
            if ( node.getNodeType() == Node.ELEMENT_NODE ) {
                Element element = (Element) node;
                String transactionAmount = element.getElementsByTagName("거래금액").item(0).getChildNodes().item(0).getNodeValue();
                String transactionType = element.getElementsByTagName("거래유형").item(0).getChildNodes().item(0).getNodeValue();
                int buildingYear = Integer.parseInt(element.getElementsByTagName("건축년도").item(0).getChildNodes().item(0).getNodeValue());
                items.add(new Item(transactionAmount, transactionType, buildingYear));
            }
        }

        for ( Item item : items ) System.out.println(item.toString());
    }

    static class Item {
        private final String transactionAmount;
        private final String transactionType;
        private final int buildingYear;

        public Item(String transactionAmount, String transactionType, int buildingYear) {
            this.transactionAmount = transactionAmount;
            this.transactionType = transactionType;
            this.buildingYear = buildingYear;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "transactionAmount='" + transactionAmount + '\'' +
                    ", transactionType='" + transactionType + '\'' +
                    ", buildingYear=" + buildingYear +
                    '}';
        }
    }
}
