package com.denysborozenets.secondtask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.denysborozenets.secondtask.JsonJacksonParser.*;


public class Main2 {

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        FinesRoot root = new FinesRoot();

        List<File> filesInFolder = filesIterator(DIRECTORY_PATH);

        int amountForSpeeding = 0;
        int amountRedLight = 0;
        int amountForParking = 0;

        for (File file : filesInFolder) {
            File absoluteFile = file.getAbsoluteFile();
            JsonNode node = objectMapper.readTree(absoluteFile);
            if (node.isArray()) {
                for (JsonNode j : node) {
                    String type = j.get(Keys.TYPE_KEY.getValue()).asText();
                    if (type.equals(Keys.SPEEDING.getValue())) {
                        amountForSpeeding += j.get(Keys.FINE_AMOUNT_KEY.getValue()).asInt();
                    } else if (type.equals(Keys.RED_TRAFFIC_LIGHT.getValue())) {
                        amountRedLight += j.get(Keys.FINE_AMOUNT_KEY.getValue()).asInt();
                    } else {
                        amountForParking += j.get(Keys.FINE_AMOUNT_KEY.getValue()).asInt();
                    }
                }
            }
        }

        Map<String, Integer> resultMap = new TreeMap<>();
        resultMap.put(Keys.SPEEDING.getValue(), amountForSpeeding);
        resultMap.put(Keys.RED_TRAFFIC_LIGHT.getValue(), amountRedLight);
        resultMap.put(Keys.PARKING.getValue(), amountForParking);
        root.setFines(resultMap);


        String stringJson = writeResultToJson(root, objectMapper, RESULT_JSON);
        String result = convertToXml(objectMapper, stringJson, HEAD_OF_XML);
        stringToDom(result, OUT_XML);

    }



}

