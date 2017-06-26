package com.rengu.DAO.d3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.entity.*;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;

/**
 * Created by wey580231 on 2017/6/6.
 */
public class Emulate3DAO {

    //TODO 3D车间的模拟数据，根据一次排程所涉及的订单数量，按照订单排程结果，分别发送给会3D车间
    public boolean getEmulateData(String snapshotId, StringBuilder jsonString) {
        boolean flag = false;
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        RG_SnapshotNodeEntity snapshot = session.get(RG_SnapshotNodeEntity.class, snapshotId);

        Set<RG_OrderEntity> orders = null;

        if (snapshot != null) {
            //查找此次排程对应的订单信息
            RG_ScheduleEntity scheduleEntity = snapshot.getSchedule();
            if (scheduleEntity != null) {
                orders = scheduleEntity.getOrders();
            }

            //查找此次排程对应的所有订单结果信息
            Set<RG_PlanEntity> plans = snapshot.getPlans();

            //TODO 等真实需要转换时，再调用。
//            convertPlanTo3DEmulateData(plans);
        }

        if (orders != null) {
            Iterator<RG_OrderEntity> iter = orders.iterator();

            ObjectMapper mapper = new ObjectMapper();               //定义转换类
            ObjectNode root = mapper.createObjectNode();            //创建根节点
            root.put("result", "0");

            ArrayNode array = mapper.createArrayNode();

            while (iter.hasNext()) {

                ObjectNode dataNode = mapper.createObjectNode();

                RG_OrderEntity entity = iter.next();

                RG_ProductEntity product = entity.getProductByIdProduct();
                List<RG_EmulateDataEntity> emulateDatas = entity.getEmulateDatas();
                Iterator<RG_EmulateDataEntity> emulateIter = emulateDatas.iterator();

                ArrayNode arrayNode = mapper.createArrayNode();

                while (emulateIter.hasNext()) {
                    RG_EmulateDataEntity emulateData = emulateIter.next();

                    if (emulateData != null) {
                        ObjectNode node = mapper.createObjectNode();

                        node.put("item", emulateData.getItem());
                        node.put("state", emulateData.getState());
                        node.put("good", emulateData.getGood());
                        node.put("startLocation", emulateData.getStartLocation());
                        node.put("ednLocation", emulateData.getEndLocation());
                        node.put("startTime", emulateData.getStartTime());
                        node.put("endTime", emulateData.getEndTime());

                        arrayNode.add(node);
                    }
                }

                dataNode.put("name", product.getName());
                dataNode.put("info", arrayNode);

                array.add(dataNode);
            }

            root.put("data", array);

            try {
                jsonString.append(mapper.writeValueAsString(root));
                flag = true;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        session.getTransaction().commit();

        return flag;
    }

    //将plan表转换成3d车间的模拟数据，中间存在的字段需要框架来补充
    private void convertPlanTo3DEmulateData(Set<RG_PlanEntity> plans) {

        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query queryObject = session.createNativeQuery("truncate table rg_emulatedata");
        if (queryObject.executeUpdate() >= 0) {

            Iterator<RG_PlanEntity> iter = plans.iterator();
            while (iter.hasNext()) {
                RG_PlanEntity plan = iter.next();

                //TODO 将plan表转换成3d车间对应的格式

                RG_EmulateDataEntity data = new RG_EmulateDataEntity();

                data.setOrderEntity(plan.getOrderByIdOrder());

                session.save(data);
            }
        }
        session.getTransaction().commit();
    }
}
