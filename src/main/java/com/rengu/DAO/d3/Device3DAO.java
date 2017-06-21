package com.rengu.DAO.d3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.entity.RG_OrderEntity;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * 3D车间请求MES的设备和订单信息
 * Created by wey580231 on 2017/6/6.
 */
public class Device3DAO {

    public boolean getDeviceReport(String requestDevice, String code, StringBuilder result) {
        boolean returnRes = false;

        //TODO 需要向MES发送activeMQ发送设备表请求
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("result", "0");
        root.put("type", requestDevice);

        //【1】订单主表
        ObjectNode deviceData = mapper.createObjectNode();
        deviceData.put("idResource", "");
        deviceData.put("resourceName", "");
        deviceData.put("manufacturer", "");
        deviceData.put("idTask", "");
        deviceData.put("ordToParentTask", "");
        deviceData.put("idClub", "");
        deviceData.put("idProduct", "");
        deviceData.put("productName", "");
        deviceData.put("t1Task", "");
        deviceData.put("t2Task", "");
        deviceData.put("currTime", "");
        deviceData.put("t1RealTask", "");
        deviceData.put("t2RealTask", "");
        deviceData.put("state", "");
        deviceData.put("bootstrapTime", "");
        deviceData.put("awaitTime", "");
        deviceData.put("processTime", "");

        root.put("data", deviceData);

        try {
            result.append(mapper.writeValueAsString(root));
            returnRes = true;
        } catch (JsonProcessingException e) {

        }
        return returnRes;
    }

    public boolean getOrderReport(String requestOrder, String code, StringBuilder result) {

        boolean returnRes = false;

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
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
                //TODO 需要向MES发送activeMQ发送订单表请求
//                mainData.put("completeNum",entity.get);
//                mainData.put("completeCent",entity.getId());
                mainData.put("t0", Tools.formatDate(entity.getT0()));
                mainData.put("currTime", "");
                mainData.put("t2Plan", Tools.formatDate(entity.getT2Plan()));
                mainData.put("state", entity.getState());

                //【2】订单子表
                ObjectNode subData = mapper.createObjectNode();
                subData.put("idOrder", entity.getId());
                //TODO 需要向MES发送activeMQ发送订单子表请求，将下列字段值更新
                subData.put("idTask", entity.getId());
                subData.put("nameTask", entity.getId());
                subData.put("planDevice", entity.getId());
                subData.put("planCount", entity.getId());
                subData.put("planStartTime", entity.getId());
                subData.put("planFinishTime", entity.getId());
                subData.put("actualDispatchTime", entity.getId());
                subData.put("actualFinshTime", entity.getId());
                subData.put("hasFinished", entity.getId());
                subData.put("finishPercent", entity.getId());

                ObjectNode dataOrder = mapper.createObjectNode();
                dataOrder.put("mainOrder", mainData);
                dataOrder.put("subOrder", subData);

                root.put("data", dataOrder);

                try {
                    result.append(mapper.writeValueAsString(root));
                    returnRes = true;
                } catch (JsonProcessingException e) {

                }
                //【3】更新order中的对应订单的完成数量
                entity.setFinishQuantity((short) 0);
                session.update(entity);
            }
        }

        session.getTransaction().commit();

        return returnRes;
    }
}
