package com.rengu.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by hanchangming on 2017/5/24.
 */
public class Tools {

    private static Properties properties = null;

    public static <T> T jsonConvertToEntity(String jsonString, Class<T> classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(jsonString, classType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String entityConvertToJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return objectMapper.writeValueAsString(object);
    }

    public static JsonNode jsonTreeModelParse(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        return jsonNode;
    }

    public static void jsonPrint(String string, HttpServletResponse httpServletResponse) {
        httpServletResponse.setContentType("text/html");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter printWriter = null;
        try {
            printWriter = httpServletResponse.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.println(string);
        printWriter.flush();
        printWriter.close();
    }

    public static String getHttpRequestBody(HttpServletRequest httpServletRequest) {
        String httpRequestBodyString = "";
        try {
            httpServletRequest.setCharacterEncoding("UTF-8");
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

    public static Properties getDatabaseProperties() {
        if (properties == null) {
            properties = new Properties();

            try {
                InputStream inputStream = Tools.class.getResourceAsStream("/Database.properties");
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

    public static boolean executeSQLForUpdate(String databaseType, String companyName, String SQLString) throws ClassNotFoundException, SQLException {
        System.out.println("执行SQL语句:" + SQLString);
        Properties databaseProperties = getDatabaseProperties();
        String databaseUrl = databaseProperties.getProperty(companyName + databaseType + "DatabaseUrl");
        String databaseUsername = databaseProperties.getProperty(companyName + "DatabaseUsername");
        String databasePassword = databaseProperties.getProperty(companyName + "DatabasePassword");
        String databaseDriver = databaseProperties.getProperty(databaseType + "Driver");
        System.out.println("驱动：" + databaseDriver + "-----" + "链接地址：" + databaseUrl + "-----" + "执行命令：" + SQLString);
        Class.forName(databaseDriver);
        Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
        Statement statement = connection.createStatement();
        boolean resultFlag = statement.execute(SQLString);
        statement.close();
        connection.close();
        return resultFlag;
    }

    public static ResultSet executeSQLForResultSet(String databaseType, String companyName, String SQLString) throws ClassNotFoundException, SQLException {
        Properties databaseProperties = getDatabaseProperties();
        String databaseUrl = databaseProperties.getProperty(companyName + databaseType + "DatabaseUrl");
        String databaseUsername = databaseProperties.getProperty(companyName + "DatabaseUsername");
        String databasePassword = databaseProperties.getProperty(companyName + "DatabasePassword");
        String databaseDriver = databaseProperties.getProperty(databaseType + "Driver");
        System.out.println("驱动：" + databaseDriver + "-----" + "链接地址：" + databaseUrl + "-----" + "执行命令：" + SQLString);
        Class.forName(databaseDriver);
        Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLString);
        statement.close();
        connection.close();
        return resultSet;
    }

    public static List executeSQLForList(String databaseType, String companyName, String SQLString) throws ClassNotFoundException, SQLException {
        Properties databaseProperties = getDatabaseProperties();
        String databaseUrl = databaseProperties.getProperty(companyName + databaseType + "DatabaseUrl");
        String databaseUsername = databaseProperties.getProperty(companyName + "DatabaseUsername");
        String databasePassword = databaseProperties.getProperty(companyName + "DatabasePassword");
        String databaseDriver = databaseProperties.getProperty(databaseType + "Driver");
        System.out.println("驱动：" + databaseDriver + "-----" + "链接地址：" + databaseUrl + "-----" + "执行命令：" + SQLString);
        Class.forName(databaseDriver);
        Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLString);
        return resultSetConvertToList(resultSet);
    }

    public static List resultSetConvertToList(ResultSet resultSet) throws SQLException {
        List list = new ArrayList();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();//获取键名
        int columnCount = resultSetMetaData.getColumnCount();//获取行的数量
        while (resultSet.next()) {
            Map rowData = new HashMap();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
    }

    public static void executeSQLForInitTable(String databaseType, String companyName, String[] tableList) throws ClassNotFoundException, SQLException {
        Properties databaseProperties = getDatabaseProperties();
        String databaseUrl = databaseProperties.getProperty(companyName + databaseType + "DatabaseUrl");
        String databaseUsername = databaseProperties.getProperty(companyName + "DatabaseUsername");
        String databasePassword = databaseProperties.getProperty(companyName + "DatabasePassword");
        String databaseDriver = databaseProperties.getProperty(databaseType + "Driver");
        Class.forName(databaseDriver);
        Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
        Statement statement = connection.createStatement();
        for (String tableName : tableList) {
//            String SQLCommed = "TRUNCATE table " + tableName + ";";
            String SQLCommed = "delete from " + tableName + "";
            System.out.println("驱动：" + databaseDriver + "-----" + "链接地址：" + databaseUrl + "-----" + "执行命令：" + SQLCommed);
            statement.execute(SQLCommed);
        }
        statement.close();
        connection.close();
    }

    /*
 * 将时间转换为时间戳
 */
    public static Date stringConvertToDate(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(dateString);
        return date;
    }

    public static Date stringConvertToDate(Long dateLong) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(simpleDateFormat.format(dateLong));
        return date;
    }

    public static String dateConvertToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateString = simpleDateFormat.format(date);
        return dateString;
    }

    public static String resultCode(String result, String description) {
        String tmp = "";

        tmp += "{" +
                "\"result\":" + "\"" + result + "\"" + "," +
                "\"description\":" + "\"" + description + "\"" +
                "}";

        return tmp;
    }

    //aps状态返回
    public static String apsCode(String result, String code, String description) {
        String tmp = "";

        tmp += "{" +
                "\"result\":" + "\"" + result + "\"" + "," +
                "\"code\":" + "\"" + code + "\"" + "," +
                "\"description\":" + "\"" + description + "\"" +
                "}";

        return tmp;
    }

    //获取时间戳
    public static String getTimestamp() {
        return String.valueOf(new Date().getTime());
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return df.format(date);
    }

    public static String formatToStandardDate(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return df.format(date);
    }

    public static Date parseDate(String text) {
        if (text.equals("")) {
            return null;
        }
        long timeStmp = Long.parseLong(text);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = simpleDateFormat.format(timeStmp);
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void showLog() {
        System.out.println("○○○○○○○○○╭╭╮╮╮    ╭╭╭╮╮○○○○\n" +
                "○○○○○○○○○╰╰ ╮╮    ╭╭ ╯╯○○○○○\n" +
                "○○○○○○○○○○○○○╰╮╭╯○○○○○○○○\n" +
                "○oo◥█◣◢█◤○○○○○oo╮╭○○○○○○○○○\n" +
                "○○○◥██◤○○○○◢█████◣○○○○○○○\n" +
                "○○○○◥█◣○○○◢███████◣○○○○○○\n" +
                "○○○○○██◣○◢███████〥█◣○○○○○\n" +
                "○○○○○███████████████○○○○○\n" +
                "○○○○○◥██████████████○○○○○\n" +
                "○﹏﹏﹏﹏﹏◥████████████◤﹏﹏﹏﹏﹏**==");
    }

    public static String getSha(String str) {
        if (null == str || 0 == str.length()) {
            return null;
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }

            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
