import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.actions.mes.MesSender;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public void createPostBody() throws ParseException, JsonProcessingException {
        Session session = MySessionFactory.getSessionFactory().openSession();
        String latestScheduleId = UserConfigTools.getLatestSchedule("1");
        if (latestScheduleId != null && latestScheduleId.length() > 0) {
            RG_ScheduleEntity scheduleEntity = session.get(RG_ScheduleEntity.class, latestScheduleId);
            if (scheduleEntity != null) {
                ObjectMapper mapper = new ObjectMapper();
                ObjectNode mainNode = mapper.createObjectNode();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                //Todo 待按照时间筛选出订单
                ArrayNode orderNode = mapper.createArrayNode();
                calendar.setTime(simpleDateFormat.parse(simpleDateFormat.format(scheduleEntity.getScheduleTime())));
                calendar.add(Calendar.DAY_OF_YEAR, scheduleEntity.getRollTime());
                Date startRollingTime = calendar.getTime();
                calendar.add(Calendar.DAY_OF_YEAR, scheduleEntity.getRollTime());
                Date endRollingTime = calendar.getTime();

                calendar.setTime(startRollingTime);
                calendar.add(Calendar.DAY_OF_YEAR, scheduleEntity.getScheduleWindow());
                Date endSelectOrderTime = calendar.getTime();
                calendar.setTime(endSelectOrderTime);
                calendar.add(Calendar.DAY_OF_MONTH, -scheduleEntity.getRollTime());
                Date startSelectOrderTime = calendar.getTime();
                mainNode.put("name", "排程-" + Tools.formatToStandardDate(startRollingTime));
                mainNode.put("scheduleWindow", scheduleEntity.getScheduleWindow());
                mainNode.put("rollTime", scheduleEntity.getRollTime());
                mainNode.put("scheduleOption", scheduleEntity.getScheduleOption());

                String hql = "from RG_OrderEntity rg_orderEntity where rg_orderEntity.t2 between ? and ? and rg_orderEntity.state =:state";
                Query query = session.createQuery(hql);
                query.setParameter(0, startSelectOrderTime);
                query.setParameter(1, endSelectOrderTime);
                query.setParameter("state", Byte.parseByte("0"));
                List<RG_OrderEntity> orderEntityList = query.list();
                //本次滚动新增的订单
                for (RG_OrderEntity rg_OrderEntity : orderEntityList) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("id", rg_OrderEntity.getId());
                    orderNode.add(objectNode);
                }
                //添加上次排程使用的订单信息
                for (RG_OrderEntity rg_orderEntity : scheduleEntity.getOrders()) {
                    ObjectNode objectNode = mapper.createObjectNode();
                    objectNode.put("id", rg_orderEntity.getId());
                    orderNode.add(objectNode);
                }
                mainNode.put("orders", orderNode);

                ObjectNode apsNode = mapper.createObjectNode();
                calendar.setTime(startRollingTime);
                calendar.add(Calendar.DAY_OF_YEAR, -2);
                apsNode.put("t0", calendar.getTime().getTime());
                calendar.setTime(endSelectOrderTime);
                calendar.add(Calendar.DAY_OF_YEAR, scheduleEntity.getScheduleOption());
                apsNode.put("t2", calendar.getTime().getTime());
                apsNode.put("modeScheduling", scheduleEntity.getApsModel());

                mainNode.put("APSConfig", apsNode);

                ObjectNode layoutNode = mapper.createObjectNode();
                layoutNode.put("id", scheduleEntity.getLayout().getId());
                mainNode.put("layout", layoutNode);

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

                System.out.println(mapper.writeValueAsString(mainNode));
//                return mapper.writeValueAsString(mainNode);
            }
        }
//        return null;
    }
}
