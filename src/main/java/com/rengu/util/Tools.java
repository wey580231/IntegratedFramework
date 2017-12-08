package com.rengu.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.entity.RG_EventLogEntity;
import com.rengu.entity.RG_OrderEntity;
import org.hibernate.Session;

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
    static Properties properties = null;

    private static String flag = "hha";

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
            printWriter.println(string);
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHttpRequestBody(HttpServletRequest httpServletRequest) {
        String httpRequestBodyString = "";
        try {
            httpServletRequest.setCharacterEncoding("UTF-8");
            BufferedReader bufferedReader = httpServletRequest.getReader();
            String tempString;
            StringBuffer buf = new StringBuffer();
            while ((tempString = bufferedReader.readLine()) != null) {
                buf.append(tempString);
            }
            httpRequestBodyString = buf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpRequestBodyString;
    }

    public static void deleteAPSOrder(String databaseType, String companyName, List<RG_OrderEntity> orderList) {
        for (RG_OrderEntity rg_orderEntity : orderList) {
            System.out.println("删除订单ID：" + rg_orderEntity.getId() + "，名称：" + rg_orderEntity.getName());
            try {
                String deleteOrder = "DELETE FROM APS_ORDER WHERE id = '" + rg_orderEntity.getId() + "' AND idClub='001'";
                String updateOrder = "UPDATE APS_RESOURCE r SET RATE=101 WHERE EXISTS (SELECT 0 FROM APS_PLAN p WHERE r.id=p.idResource AND idOrder = '" + rg_orderEntity.getId() + "') AND idClub='001'";
                String deletePlan = "DELETE FROM APS_PLAN WHERE idOrder = '" + rg_orderEntity.getId() + "' AND idClub='001'";
                String deleteTask = "DELETE FROM APS_TASK WHERE idOrder = '" + rg_orderEntity.getId() + "' AND idClub='001'";
                String deleteJob = "DELETE FROM APS_JOB WHERE idOrder = '" + rg_orderEntity.getId() + "' AND idClub='001'";

                executeSQLForUpdate(databaseType, companyName, deleteOrder);
                executeSQLForUpdate(databaseType, companyName, updateOrder);
                executeSQLForUpdate(databaseType, companyName, deletePlan);
                executeSQLForUpdate(databaseType, companyName, deleteTask);
                executeSQLForUpdate(databaseType, companyName, deleteJob);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteAPSOrder(String databaseType, String companyName, RG_OrderEntity rg_orderEntity) {
        System.out.println("删除订单ID：" + rg_orderEntity.getId() + "，名称：" + rg_orderEntity.getName());
        String deleteOrder = "DELETE FROM APS_ORDER WHERE id = '" + rg_orderEntity.getId() + "' AND idClub='001'";
        String updateOrder = "UPDATE APS_RESOURCE r SET RATE=101 WHERE EXISTS (SELECT 0 FROM APS_PLAN p WHERE r.id=p.idResource AND idOrder = '" + rg_orderEntity.getId() + "') AND idClub='001'";
        String deletePlan = "DELETE FROM APS_PLAN WHERE idOrder = '" + rg_orderEntity.getId() + "' AND idClub='001'";
        String deleteTask = "DELETE FROM APS_TASK WHERE idOrder = '" + rg_orderEntity.getId() + "' AND idClub='001'";
        String deleteJob = "DELETE FROM APS_JOB WHERE idOrder = '" + rg_orderEntity.getId() + "' AND idClub='001'";
        try {
            executeSQLForUpdate(databaseType, companyName, deleteOrder);
            executeSQLForUpdate(databaseType, companyName, updateOrder);
            executeSQLForUpdate(databaseType, companyName, deletePlan);
            executeSQLForUpdate(databaseType, companyName, deleteTask);
            executeSQLForUpdate(databaseType, companyName, deleteJob);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Properties getDatabaseProperties() {
        synchronized (flag) {
            if (properties == null) {
                InputStream inputStream = null;
                properties = new Properties();
                try {
                    inputStream = Tools.class.getResourceAsStream("/Database.properties");
                    properties.load(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return properties;
    }

    //创建普通通知消息
    public static String creatNotificationMessage(String message, String notificationType) {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        objectNode.put("MessageType", "notification");
        objectNode.put("NotificationType", notificationType);
        objectNode.put("Message", message);
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(objectNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    //向前端通知3D实时信息
    public static String creat3DMessage(String message) {
        JsonNodeFactory jsonNodeFactory = JsonNodeFactory.instance;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        objectNode.put("MessageType", "3DMessage");
        objectNode.put("Message", message);
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(objectNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static boolean createEventLog(Short eventLogType, Short logItemType, String title, String content, String objectId) {
        RG_EventLogEntity rg_eventLogEntity = new RG_EventLogEntity();
        rg_eventLogEntity.setId(Tools.getUUID());
        rg_eventLogEntity.setCreatTime(new Date());
        rg_eventLogEntity.setEventType(eventLogType);
        rg_eventLogEntity.setLogItemtype(logItemType);
        rg_eventLogEntity.setTitle(title);
        rg_eventLogEntity.setContent(content);
        rg_eventLogEntity.setObjectId(objectId);
        return DAOFactory.getEventLogDAOImplInstance().save(rg_eventLogEntity);
    }

    public static void createEventLog(Session session, Short eventLogType, Short logItemType, String title, String content, String objectId) {
        RG_EventLogEntity rg_eventLogEntity = new RG_EventLogEntity();
        rg_eventLogEntity.setId(Tools.getUUID());
        rg_eventLogEntity.setCreatTime(new Date());
        rg_eventLogEntity.setEventType(eventLogType);
        rg_eventLogEntity.setLogItemtype(logItemType);
        rg_eventLogEntity.setTitle(title);
        rg_eventLogEntity.setContent(content);
        rg_eventLogEntity.setObjectId(objectId);
        session.save(rg_eventLogEntity);
    }

    public static boolean executeSQLForUpdate(String databaseType, String companyName, String SQLString) throws ClassNotFoundException, SQLException {
//        System.out.println("执行SQL语句:" + SQLString);
        Properties databaseProperties = getDatabaseProperties();
        String databaseUrl = databaseProperties.getProperty(companyName + databaseType + "DatabaseUrl");
        String databaseUsername = databaseProperties.getProperty(companyName + "DatabaseUsername");
        String databasePassword = databaseProperties.getProperty(companyName + "DatabasePassword");
        String databaseDriver = databaseProperties.getProperty(databaseType + "Driver");
//        System.out.println("驱动：" + databaseDriver + "-----" + "链接地址：" + databaseUrl + "-----" + "执行命令：" + SQLString);
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
//        System.out.println("驱动：" + databaseDriver + "-----" + "链接地址：" + databaseUrl + "-----" + "执行命令：" + SQLString);
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
//        System.out.println("驱动：" + databaseDriver + "-----" + "链接地址：" + databaseUrl + "-----" + "执行命令：" + SQLString);
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
//            System.out.println("驱动：" + databaseDriver + "-----" + "链接地址：" + databaseUrl + "-----" + "执行命令：" + SQLCommed);
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
//        String tmp = "";
//
//        tmp += "{" +
//                "\"result\":" + "\"" + result + "\"" + "," +
//                "\"code\":" + "\"" + code + "\"" + "," +
//                "\"description\":" + "\"" + description + "\"" +
//                "}";
        return "ok";
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

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(date);
    }

    public static String formatToStandardDate(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    public static Date parseStandTextDate(String text) {
        if (text.equals("")) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String text) {
        if (text.equals("")) {
            return null;
        }
        long timeStmp = Long.parseLong(text);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(timeStmp);
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void showLog() {
        String ss = "\n" +
                "  _____                _____            _____                                              \n" +
                " |  __ \\              / ____|          / ____|                                             \n" +
                " | |__) | ___  _ __  | |  __  _   _   | |      ___   _ __ ___   _ __    __ _  _ __   _   _ \n" +
                " |  _  / / _ \\| '_ \\ | | |_ || | | |  | |     / _ \\ | '_ ` _ \\ | '_ \\  / _` || '_ \\ | | | |\n" +
                " | | \\ \\|  __/| | | || |__| || |_| | _| |____| (_) || | | | | || |_) || (_| || | | || |_| |\n" +
                " |_|  \\_\\\\___||_| |_| \\_____| \\__,_|(_)\\_____|\\___/ |_| |_| |_|| .__/  \\__,_||_| |_| \\__, |\n" +
                "                                                               | |                    __/ |\n" +
                "                                                               |_|                   |___/ \n";
        MyLog.getLogger().info(ss);
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

    //日期计算
    public static Date dateCalculator(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        return calendar.getTime();
    }

    //日期计算
    public static Date dateCalculator(Date date, int type, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, offset);
        return calendar.getTime();
    }

    //将Date类型格式化成需要的格式
    public static Date dateFormater(Date date, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //将Date类型格式化成需要的格式
    public static Date dateFormater(String string, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    //字符串是否传空值问题
    public static String insertIfNull(String s){

        //+ "'," + (apsResource1Entity.getName() == null ? null : "'"+apsResource1Entity.getName()+"'")
        return (s == null ? null : "'"+ s +"'");
    }
}
