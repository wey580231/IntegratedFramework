package com.rengu.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * Created by hanchangming on 2017/5/24.
 */
public class Tools {
    public static Object jsonConvertToEntity(String jsonString) {
        Object object = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        try {
            object = objectMapper.readValue(jsonString, Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static String entityConvertToJsonString(Object object) {
        String jsonString = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
