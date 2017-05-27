package com.rengu.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by hanchangming on 2017/5/24.
 */
public class Tools {

    public static <T> T jsonConvertToEntity(String jsonString, Class<T> classType) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper.readValue(jsonString, classType);
    }

    public static String entityConvertToJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper.writeValueAsString(object);
    }

    public static String getHttpRequestBody(HttpServletRequest httpServletRequest) {
        String httpRequestBodyString = "";
        try {
            BufferedReader bufferedReader = httpServletRequest.getReader();
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null) {
                httpRequestBodyString = httpRequestBodyString + tempString;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpRequestBodyString;
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = Tools.class.getResourceAsStream("/Database.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static boolean database(String databaseType, String companyName, String SQLString) throws ClassNotFoundException, SQLException {
        String databaseUrl = getProperties().getProperty(companyName + "DatabaseUrl");
        String databaseUsername = getProperties().getProperty(companyName + "DatabaseUsername");
        String databasePassword = getProperties().getProperty(companyName + "DatabasePassword");
        String databaseDriver = getProperties().getProperty(databaseType + "Driver");

        Class.forName(databaseDriver);
        Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
        Statement statement = connection.createStatement();
        boolean resultFlag = statement.execute(SQLString);
        return resultFlag;
    }
}
