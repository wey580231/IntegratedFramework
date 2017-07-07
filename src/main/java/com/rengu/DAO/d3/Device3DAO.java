package com.rengu.DAO.d3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.entity.RG_OrderStateEntity;
import com.rengu.entity.RG_ResourceStateEntity;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

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
                returnRes = true;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return returnRes;
    }

    //获取订单最新信息
    public boolean getOrderReport(String requestOrder, String code, StringBuilder result) {

        boolean returnRes = false;

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

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
                //TODO 需要向MES发送activeMQ发送订单表请求
                mainData.put("completeNum", entity.getFinishQuantity());
                mainData.put("completeCent", (float) entity.getFinishQuantity() / entity.getQuantity());
                mainData.put("t0", Tools.formatDate(entity.getT0()));
                mainData.put("currTime", "");
                mainData.put("t2Plan", Tools.formatDate(entity.getT2Plan()));
                mainData.put("state", entity.getState());

                //【2】订单子表
                NativeQuery nativeQuery = session.createNativeQuery("select * from rg_orderstate where orderId = ? order by currTime desc limit 0,1").addEntity(RG_OrderStateEntity.class);
                nativeQuery.setParameter(1, code);
                list = nativeQuery.list();

                if (list.size() > 0) {
                    RG_OrderStateEntity orderStateEntity = (RG_OrderStateEntity) list.get(0);

                    mainData.put("idOrder", entity.getId());
                    //TODO 需要向MES发送activeMQ发送订单子表请求，将下列字段值更新
                    mainData.put("idTask", orderStateEntity.getIdTask());
                    mainData.put("nameTask", orderStateEntity.getNameTask());
                    mainData.put("planDevice", orderStateEntity.getPlanDevice());
                    mainData.put("planCount", orderStateEntity.getPlanCount());
                    mainData.put("planStartTime", Tools.formatToStandardDate(orderStateEntity.getPlanStartTime()));
                    mainData.put("planFinishTime", Tools.formatToStandardDate(orderStateEntity.getPlanFinishTime()));
                    mainData.put("actualDispatchDevice", orderStateEntity.getActualDispatchDevice());
                    mainData.put("actualFinshCount", orderStateEntity.getActualFinishCount());
                    mainData.put("hasFinished", orderStateEntity.isFinished());
                    mainData.put("finishPercent", orderStateEntity.getFinishPercent());

                    root.put("data", mainData);

                    try {
                        result.append(mapper.writeValueAsString(root));
                        returnRes = true;
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    //【3】更新order中的对应订单的完成数量
                    entity.setFinishQuantity((short) 0);
                    session.update(entity);
                }
            }
        }

        session.getTransaction().commit();

        return returnRes;
    }
}
