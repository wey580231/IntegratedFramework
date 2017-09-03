import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.actions.mes.MesConsumer;
import com.rengu.actions.mes.MesSender;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class ApsTable {
    @Test
    public void tongbu() throws ParseException, SQLException, ClassNotFoundException {
//        String[] tableName = {DatabaseInfo.APS_PRODUCT, DatabaseInfo.APS_PROCESS_TYPERESOURCE_SITE, DatabaseInfo.APS_TYPERESOURCE, DatabaseInfo.APS_GROUPRESOURCE, DatabaseInfo.APS_SITE, DatabaseInfo.APS_SHIFT, DatabaseInfo.APS_PROCESS, DatabaseInfo.APS_CLUB, DatabaseInfo.APS_USER, DatabaseInfo.APS_ORDER, DatabaseInfo.APS_RESOURCE};
        String[] tableName = {DatabaseInfo.APS_SITE, DatabaseInfo.APS_SITE_SITE};
        APSDatabaseSync.SyncAPSTable(tableName, DatabaseInfo.ORACLE, DatabaseInfo.APS);
    }

    @Test
    public void testDate() {
//        String dd = "2017-07-22 17:10:45";
//        Date date = Tools.parseStandTextDate(dd);
//        System.out.println(date);
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
    public void createPostBody() {
        Session session = MySessionFactory.getSessionFactory().openSession();
        String latestScheduleId = UserConfigTools.getLatestSchedule("1");

        if (latestScheduleId != null && latestScheduleId.length() > 0) {
            RG_ScheduleEntity scheduleEntity = session.get(RG_ScheduleEntity.class, latestScheduleId);
            if (scheduleEntity != null) {
                ObjectMapper mapper = new ObjectMapper();
                ObjectNode mainNode = mapper.createObjectNode();

                mainNode.put("name", "排程-" + Tools.formatToStandardDate(new Date()));
                mainNode.put("scheduleWindow", scheduleEntity.getScheduleWindow());
                mainNode.put("rollTime", scheduleEntity.getRollTime());

                ObjectNode apsNode = mapper.createObjectNode();

                Date currDate = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currDate);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);

                System.out.println(Tools.formatToStandardDate(calendar.getTime()));

                apsNode.put("t0", calendar.getTime().getTime());

                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(currDate);
                endCalendar.set(Calendar.HOUR_OF_DAY, 0);
                endCalendar.set(Calendar.MINUTE, 0);
                endCalendar.set(Calendar.SECOND, 0);
                endCalendar.set(Calendar.DAY_OF_MONTH, endCalendar.get(Calendar.DAY_OF_MONTH) + scheduleEntity.getScheduleWindow());

                System.out.println(Tools.formatToStandardDate(endCalendar.getTime()));
                apsNode.put("t2", endCalendar.getTime().getTime());

                apsNode.put("modeScheduling", scheduleEntity.getApsModel());
                mainNode.put("APSConfig", apsNode);

                ObjectNode layoutNode = mapper.createObjectNode();
                layoutNode.put("id", scheduleEntity.getLayout().getId());
                mainNode.put("layout", layoutNode);

                //Todo 待按照时间筛选出订单
                ArrayNode orderNode = mapper.createArrayNode();


                mainNode.put("orders", orderNode);

                ArrayNode resourceNode = mapper.createArrayNode();
                ObjectNode resNode = mapper.createObjectNode();
                resNode.put("id", 2);
                resourceNode.add(resNode);
                mainNode.put("resources", resourceNode);

                ArrayNode groupResourceNode = mapper.createArrayNode();
                ObjectNode groupNode = mapper.createObjectNode();
                groupNode.put("id", 2);
                groupResourceNode.add(groupNode);
                mainNode.put("groupResource", groupResourceNode);

                ArrayNode sitesNode = mapper.createArrayNode();
                ObjectNode siteNode = mapper.createObjectNode();
                siteNode.put("id", 2);
                sitesNode.add(siteNode);
                mainNode.put("site", sitesNode);

                try {
                    System.out.println(mapper.writeValueAsString(mainNode));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
