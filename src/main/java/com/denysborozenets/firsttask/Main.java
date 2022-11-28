package com.denysborozenets.firsttask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

import static com.denysborozenets.firsttask.XmlParser.*;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, TransformerException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        Document input = dbf.newDocumentBuilder().parse(new File(INPUT_XML));

        XPath xpath = XPathFactory.newInstance().newXPath();

        NodeList list = (NodeList) xpath.evaluate("//person", input, XPathConstants.NODESET);

        for (int i = 0; i < list.getLength(); i++) {
            String name = xpath.compile("./@name").evaluate(list.item(i));
            String surname = xpath.compile("./@surname").evaluate(list.item(i));
            String fullName = name + " " + surname;
            Element value = (Element) list.item(i);
            value.setAttribute("name", fullName);
            value.removeAttribute("surname");

        }

        transformerXml(input, OUTPUT_XML);
    }
}