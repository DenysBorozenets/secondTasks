package com.denysborozenets.firsttask;

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XmlParser {
    public static String INPUT_XML = "C:\\Resourses\\ProfitSoft\\secondTasks\\src\\main\\resources\\input.xml";
    public static String OUTPUT_XML = "C:\\Resourses\\ProfitSoft\\secondTasks\\src\\main\\resources\\output.xml";
    public static void transformerXml(Document input, String output) throws TransformerException, FileNotFoundException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Transformer xFormer = factory.newTransformer();
        xFormer.setOutputProperty(OutputKeys.INDENT, "yes");

        xFormer.transform(new DOMSource(input), new StreamResult(new FileOutputStream(output)));
    }
}
