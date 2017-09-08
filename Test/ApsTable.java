import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.actions.AutoRollingSchedulingAction;
import com.rengu.actions.ScheduleAction;
import com.rengu.actions.mes.MesSender;
import com.rengu.util.APSDatabaseSync;
import com.rengu.util.DatabaseInfo;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;

public class ApsTable {
    @Test
    public void tongbu() throws ParseException, SQLException, ClassNotFoundException {
//        String[] tableName = {DatabaseInfo.APS_PRODUCT, DatabaseInfo.APS_PROCESS_TYPERESOURCE_SITE, DatabaseInfo.APS_TYPERESOURCE, DatabaseInfo.APS_GROUPRESOURCE, DatabaseInfo.APS_SITE, DatabaseInfo.APS_SHIFT, DatabaseInfo.APS_PROCESS, DatabaseInfo.APS_CLUB, DatabaseInfo.APS_USER, DatabaseInfo.APS_ORDER, DatabaseInfo.APS_RESOURCE};
        String[] tableName = {DatabaseInfo.APS_SITE, DatabaseInfo.APS_SITE_SITE};
        APSDatabaseSync.SyncAPSTable(tableName, DatabaseInfo.ORACLE, DatabaseInfo.APS);
    }

    @Test
    public void testDate() {
        if ((int) (Math.random() * 100) % 2 == 0) {
            System.out.println("1");
        }
    }

    @Test
    public void sendPlan() {

        ObjectMapper mapper = new ObjectMapper();               //定义转换类
        ObjectNode dataNode = mapper.createObjectNode();            //创建根节点

        dataNode.put("id", "a");
        dataNode.put("idOrder", "b");
        dataNode.put("nameTask", "c");
        dataNode.put("idProductOrder", "d");
        dataNode.put("quantityTask", 2);
        dataNode.put("t1Task", "2017-08-02 17:12:00");
        dataNode.put("t2Task", "2017-08-02 17:15:00");
        dataNode.put("nameUser", "yang");
        dataNode.put("scheduleTime", "2017-08-02 17:00:00");

        MesSender.instance().sendData("planInfo", dataNode);

    }

    @Test
    public void sendOrder() {
        ObjectMapper mapper = new ObjectMapper();                   //定义转换类
        ObjectNode dataNode = mapper.createObjectNode();            //创建根节点

        dataNode.put("id", "a");
        dataNode.put("name", "b");
        dataNode.put("idProduct", "8000X8000");
        dataNode.put("quantity", 1);
        dataNode.put("priority", 2);
        dataNode.put("t0", "2017-08-02 17:12");
        dataNode.put("t1", "2017-08-02 17:12");
        dataNode.put("t2", "2017-08-02 17:12");
        dataNode.put("ord", 2);
        dataNode.put("t1Interaction", "2017-08-03 17:12");
        dataNode.put("t2Interaction", "2017-08-04 17:12");
        dataNode.put("t1Plan", "2017-08-05 17:12");
        dataNode.put("t2Plan", "2017-08-06 17:12");
        dataNode.put("advance", 0);
        dataNode.put("delay", 0);
        dataNode.put("nbTask", 20);

        MesSender.instance().sendData("orderInfo", dataNode);
    }

    @Test
    public void sendTransport() {
        ObjectMapper mapper = new ObjectMapper();               //定义转换类
        ObjectNode dataNode = mapper.createObjectNode();            //创建根节点

        dataNode.put("type", "a");
        dataNode.put("idOrder", "b");
        dataNode.put("idTask", "c");
        dataNode.put("nameTask", "d");
        dataNode.put("idProductOrder", "e");
        dataNode.put("t1", "2017-08-03 09:40");
        dataNode.put("t2", "2017-08-03 09:41");
        dataNode.put("idSite1", "e");
        dataNode.put("goods", "f");
        dataNode.put("idResource", "g");
        dataNode.put("idProcess", "h");

        MesSender.instance().sendData("orderInfo", dataNode);
    }

    @Test
    public void createPostBodyTest() {
        try {
            String jsonString = AutoRollingSchedulingAction.createScheduleInfo();
            System.out.println(jsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseAndSnaphostTest() {
        String jsonString = "{\"name\":\"排程-2017-09-07 00:18:37\",\"scheduleWindow\":7,\"rollTime\":1,\"APSConfig\":{\"t0\":1504564200000,\"t2\":1505730600000,\"modeScheduling\":\"正向\"},\"layout\":{\"id\":\"1\"},\"orders\":[{\"id\":\"Kqd-1\"},{\"id\":\"Kqd-2\"},{\"id\":\"Yqc-1\"},{\"id\":\"Yqc-2\"}],\"resources\":[{\"id\":2}],\"groupResource\":[{\"id\":2}],\"site\":[{\"id\":2}],\"scheduleOption\":5}";
        new ScheduleAction().beginScheduleHandler(jsonString);
    }
}
