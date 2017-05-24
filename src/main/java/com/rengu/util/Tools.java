package com.rengu.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by hanchangming on 2017/5/24.
 */
public class Tools {
    public static Object jsonConvertToEntity(String jsonString) {
        Object object = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//        object = objectMapper.readValue(jsonString,)
        return object;
    }
}
