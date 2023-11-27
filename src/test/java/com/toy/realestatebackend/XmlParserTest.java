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
import java.util.LinkedList;
import java.util.List;

public class XmlParserTest {
    @Test
    public void xmlParserTest() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("D:\\workspace\\toy-real-estate-backend\\src\\test\\java\\com\\toy\\realestatebackend\\employee.xml");
        List<Employee> employees = new LinkedList<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for ( int i = 0; i < nodeList.getLength(); i++ ) {
            Node node = nodeList.item(i);
            if ( node.getNodeType() == Node.ELEMENT_NODE ) {
                Element element = (Element) node;
                String firstName = element.getElementsByTagName("firstname").item(0).getChildNodes().item(0).getNodeValue();
                String lastName = element.getElementsByTagName("lastname").item(0).getChildNodes().item(0).getNodeValue();
                double salary = Double.parseDouble(element.getElementsByTagName("salary").item(0).getChildNodes().item(0).getNodeValue());
                employees.add(new Employee(firstName, lastName, salary));
            }
        }
        for ( Employee employee : employees ) System.out.println(employee.toString());
    }

    static class Employee {
        private final String firstName;
        private final String lastName;
        private final double salary;
        public Employee(String firstName, String lastName, double salary) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.salary = salary;
        }
        @Override
        public String toString() {
            return "[" + firstName + ", " + lastName + ", " + salary + "]";
        }
    }
}
