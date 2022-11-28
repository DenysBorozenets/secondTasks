package com.denysborozenets.secondtask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsonJacksonParser {
    public static String DIRECTORY_PATH = "C:\\Resourses\\ProfitSoft\\secondTasks\\src\\main\\resources\\secondtaskres";
    public static String RESULT_JSON = "C:\\Resourses\\ProfitSoft\\secondTasks\\src\\main\\resources\\fines.json";
    public static String OUT_XML = "C:\\Resourses\\ProfitSoft\\secondTasks\\src\\main\\resources\\result.xml";
    public static String HEAD_OF_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
    public static List<File> filesIterator(String source) throws IOException {
        return Files.walk(Paths.get(source))
                .filter(Files::isRegularFile)
                .map(Path::toFile).toList();
    }

    public static String writeResultToJson(FinesRoot root, ObjectMapper objectMapper, String source) throws IOException {
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.writeValue(new File(source), root);
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }

    public static String convertToXml(ObjectMapper objectMapper, String json, String header) throws JsonProcessingException {
        ObjectMapper xmlMapper = new XmlMapper();
        StringBuilder stringBuilder = new StringBuilder();

        JsonNode tree = objectMapper.readTree(json);

        String jsonAsXml = xmlMapper.writer().withRootName("root").withDefaultPrettyPrinter().writeValueAsString(tree);
        stringBuilder.append(header);
        stringBuilder.append(jsonAsXml);

        return stringBuilder.toString();
    }

    public static void stringToDom(String xmlSource, String out) throws IOException {
        File file = new File(out);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(xmlSource);
        fileWriter.close();
    }
}
