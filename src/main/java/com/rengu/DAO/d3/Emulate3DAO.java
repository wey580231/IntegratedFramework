package com.rengu.DAO.d3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.entity.*;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.*;

/**
 * Created by wey580231 on 2017/6/6.
 */
public class Emulate3DAO {

    @Deprecated
    public boolean getEmulateData(String snapshotId, StringBuilder jsonString) {
        boolean flag = false;
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        RG_SnapshotNodeEntity bottomSnapshot = session.get(RG_SnapshotNodeEntity.class, snapshotId);

        Set<RG_OrderEntity> orders = null;

        if (bottomSnapshot != null) {

            RG_SnapshotNodeEntity rootSnapshot = bottomSnapshot.getRootParent();

            if (rootSnapshot != null) {
                //查找此次排程对应的订单信息
                RG_ScheduleEntity scheduleEntity = rootSnapshot.getSchedule();
                if (scheduleEntity != null) {
                    orders = scheduleEntity.getOrders();
                }
            }
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
                        node.put("startTime", Integer.parseInt(emulateData.getStartTime()));
                        node.put("endTime", Integer.parseInt(emulateData.getEndTime()));

                        arrayNode.add(node);
                    }
                }

                dataNode.put("id", entity.getId());
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

    //获取新格式排程结果
    public boolean getEmulateResult(String snapshotId, StringBuilder jsonString) {
        boolean flag = false;
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        RG_SnapshotNodeEntity bottomSnapshot = session.get(RG_SnapshotNodeEntity.class, snapshotId);

        Set<RG_OrderEntity> orders = null;

        if (bottomSnapshot != null) {

            RG_SnapshotNodeEntity rootSnapshot = bottomSnapshot.getRootParent();

            if (rootSnapshot != null) {
                //查找此次排程对应的订单信息
                RG_ScheduleEntity scheduleEntity = rootSnapshot.getSchedule();
                if (scheduleEntity != null) {
                    orders = scheduleEntity.getOrders();
                }
            }
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

                NativeQuery query = session.createNativeQuery("select * from rg_emulateresult where idOrder = ? and idSnapshort is null order by id asc",RG_EmulateResultEntity.class);
                query.setParameter(1,entity.getId());

                List<RG_EmulateResultEntity> emulateDatas = query.list();
                Iterator<RG_EmulateResultEntity> emulateIter = emulateDatas.iterator();

                ArrayNode arrayNode = mapper.createArrayNode();

                while (emulateIter.hasNext()) {
                    RG_EmulateResultEntity emulateData = emulateIter.next();

                    if (emulateData != null) {
                        ObjectNode node = mapper.createObjectNode();

                        node.put("task", emulateData.getTask());
                        node.put("good", emulateData.getGoods());
                        node.put("startTime", Integer.parseInt(emulateData.getStartTime()));
                        node.put("endTime", Integer.parseInt(emulateData.getEndTime()));
                        if (emulateData.getSite() != null && emulateData.getSite().length() > 0) {
                            node.put("site", emulateData.getSite());
                        } else {
                            node.put("site", "");
                        }
                        arrayNode.add(node);
                    }
                }

                dataNode.put("id", entity.getId());
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

    //获取所有订单信息，利用在快招树节点转换后的结果，直接查询
    public boolean getAllOrderEmulateResult(String snapshotId, StringBuilder jsonString) {
        boolean flag = false;
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        NativeQuery query = session.createNativeQuery("select * from rg_emulateresult where idSnapshort=:id ", RG_EmulateResultEntity.class);
        query.setParameter("id", snapshotId);

        List<RG_EmulateResultEntity> results = query.list();

        if (results.size() > 0) {

            ObjectMapper mapper = new ObjectMapper();               //定义转换类
            ObjectNode root = mapper.createObjectNode();            //创建根节点
            root.put("result", "0");

            ArrayNode array = mapper.createArrayNode();

            ObjectNode dataNode = mapper.createObjectNode();
            dataNode.put("id", "All Orders");
            dataNode.put("name", "orders");

            ArrayNode arrayNode = mapper.createArrayNode();

            for (int i = 0; i < results.size(); i++) {
                RG_EmulateResultEntity entity = results.get(i);
                ObjectNode node = mapper.createObjectNode();

                node.put("task", entity.getTask());
                node.put("good", entity.getGoods());
                node.put("startTime", Integer.parseInt(entity.getStartTime()));
                node.put("endTime", Integer.parseInt(entity.getEndTime()));
                if (entity.getSite() != null) {
                    node.put("site", entity.getSite());
                } else {
                    node.put("site", "");
                }
                arrayNode.add(node);
            }
            dataNode.put("info", arrayNode);

            array.add(dataNode);

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
}
