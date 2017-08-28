package com.rengu.DAO.d3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.entity.*;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

/**
 * 3D车间请求MES的设备和订单信息
 * Created by wey580231 on 2017/6/6.
 */
public class Device3DAO {

    //获取设备最新信息
    public boolean getDeviceReport(String requestDevice, String code, StringBuilder result) {
        boolean returnRes = false;

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        NativeQuery query = session.createNativeQuery("select * from rg_resourcestate where resourceId = ? order by currTime desc limit 0,1").addEntity(RG_ResourceStateEntity.class);
        query.setParameter(1, code);
        List list = query.list();

        if (list.size() > 0) {
            //TODO 直接从resourceState中读取最新值
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode root = mapper.createObjectNode();
            root.put("result", "0");
            root.put("type", requestDevice);

            //【1】设备信息
            ObjectNode deviceData = mapper.createObjectNode();

            RG_ResourceStateEntity entity = (RG_ResourceStateEntity) list.get(0);
            deviceData.put("idResource", entity.getIdResource());
            deviceData.put("resourceName", entity.getResourceName());
            deviceData.put("manufacturer", entity.getManufacturer());
            deviceData.put("idTask", entity.getIdTask());
            deviceData.put("idProcess", entity.getIdProcess());
            deviceData.put("idClub", entity.getIdClub());
            deviceData.put("idProduct", entity.getIdProduct());
            deviceData.put("productName", entity.getProductName());
            deviceData.put("t1Task", Tools.formatToStandardDate(entity.getT1Task()));
            deviceData.put("t2Task", Tools.formatToStandardDate(entity.getT2Task()));
            deviceData.put("currTime", Tools.formatToStandardDate(entity.getCurrTime()));
            deviceData.put("t1RealTask", Tools.formatToStandardDate(entity.getT1RealTask()));
            deviceData.put("t2RealTask", Tools.formatToStandardDate(entity.getT2RealTask()));
            deviceData.put("state", entity.getState());
            deviceData.put("bootstrapTime", entity.getBootstrapTime());
            deviceData.put("awaitTime", entity.getAwaitTime());
            deviceData.put("processTime", entity.getProcessTime());

            root.put("data", deviceData);

            try {
                result.append(mapper.writeValueAsString(root));
                MyLog.getLogger().info("3D车间设备状态查询结果:" + mapper.writeValueAsString(root));
                returnRes = true;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                MyLog.getLogger().error("XXXXXXXXXX设备状态查询失败XXXXXXXXXXXXX" + e.getMessage());
            }
        }

        return returnRes;
    }

    //获取订单最新信息，
    public boolean getOrderReport(String requestOrder, String code, StringBuilder result) {

        boolean returnRes = false;

        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from RG_OrderEntity entity where entity.id = :id");
        query.setParameter("id", code);
        List list = query.list();

        if (list.size() == 1) {
            if (list.get(0) instanceof RG_OrderEntity) {
                RG_OrderEntity entity = (RG_OrderEntity) list.get(0);

                ObjectMapper mapper = new ObjectMapper();                //定义转换类
                ObjectNode root = mapper.createObjectNode();             //创建根节点
                root.put("result", "0");
                root.put("type", requestOrder);

                //【1】订单主表
                ObjectNode mainData = mapper.createObjectNode();
                mainData.put("idOrder", entity.getId());
                mainData.put("name", entity.getName());
                mainData.put("idProduct", entity.getProductByIdProduct().getId());
                mainData.put("productName", entity.getProductByIdProduct().getName());
                mainData.put("quantity", entity.getQuantity());
                mainData.put("completeNum", entity.getFinishQuantity());
                mainData.put("completeCent", (float) entity.getFinishQuantity() / entity.getQuantity());
                mainData.put("t0", Tools.formatDate(entity.getT0()));
                mainData.put("currTime", "");
                mainData.put("t2Plan", Tools.formatDate(entity.getT2Plan()));
                mainData.put("state", entity.getState());

                String snapshotId = D3Tools.getD3SnapshotId(1);

                ArrayNode subData = mapper.createArrayNode();

                //Yang 20170821 获取对应订单的所有的生产信息
                if (snapshotId != null) {
                    query = session.createNativeQuery("select * from rg_plan rp LEFT JOIN rg_process procc on rp.idProcess = procc.id where procc.transport = 0 and rp.idOrder=:orderId and rp.idSnapshort=:snapshotId order by t1Task asc", RG_PlanEntity.class);
                    query.setParameter("orderId", code);
                    query.setParameter("snapshotId", snapshotId);
                    List<RG_PlanEntity> plans = query.list();

                    int count = 0;

                    Iterator<RG_PlanEntity> iter = plans.iterator();
                    while (iter.hasNext()) {
                        RG_PlanEntity plan = iter.next();

                        ObjectNode dataNode = mapper.createObjectNode();

                        dataNode.put("idTask", plan.getIdTask());
                        dataNode.put("nameTask", plan.getNameTask());
                        dataNode.put("planDevice", plan.getNameResource());
                        dataNode.put("planCount", plan.getQuantityTask());
                        dataNode.put("planStartTime", plan.getT1Task());
                        dataNode.put("planFinishTime", plan.getT2Task());
                        dataNode.put("actualDispatchDevice", plan.getNameResource());
                        dataNode.put("actualFinshCount", plan.getQuantityTask());
                        if (count < plans.size() - 1) {
                            dataNode.put("hasFinished", false);
                            dataNode.put("finishPercent", (float) count / plans.size());
                        } else {
                            dataNode.put("hasFinished", true);
                            dataNode.put("finishPercent", 1);
                        }

                        subData.add(dataNode);

                        count++;
                    }
                }

                ObjectNode dataNode = mapper.createObjectNode();
                dataNode.put("mainOrder", mainData);
                dataNode.put("subOrder", subData);

                root.put("data", dataNode);

                try {
                    result.append(mapper.writeValueAsString(root));
                    returnRes = true;
                    MyLog.getLogger().info("3D车间订单状态查询结果:"+mapper.writeValueAsString(root));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    MyLog.getLogger().error("XXXXXXXXXX订单状态查询失败XXXXXXXXXXXXX" + e.getMessage());
                }
            }
        }

        session.getTransaction().commit();
        session.close();

        return returnRes;
    }
}
