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
        deviceData.put("idResource", "UR5");
        deviceData.put("resourceName", "UR5");
        deviceData.put("manufacturer", "UR5");
        deviceData.put("idTask", "abc");
        deviceData.put("ordToParentTask", 12);
        deviceData.put("idClub", "sfs");
        deviceData.put("idProduct", "sd");
        deviceData.put("productName", "232a");
        deviceData.put("t1Task", "asdf");
        deviceData.put("t2Task", "asdf");
        deviceData.put("currTime", "asdf");
        deviceData.put("t1RealTask", "23ad");
        deviceData.put("t2RealTask", "asdfbaf");
        deviceData.put("state", 1);
        deviceData.put("bootstrapTime", 0.2);
        deviceData.put("awaitTime", 0.4);
        deviceData.put("processTime", 0.3);

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
        if(!session.getTransaction().isActive()){
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
//                mainData.put("completeNum",entity.get);
//                mainData.put("completeCent",entity.getId());
                mainData.put("t0", Tools.formatDate(entity.getT0()));
                mainData.put("currTime", "");
                mainData.put("t2Plan", Tools.formatDate(entity.getT2Plan()));
                mainData.put("state", entity.getState());

                //【2】订单子表
                mainData.put("idOrder", entity.getId());
                //TODO 需要向MES发送activeMQ发送订单子表请求，将下列字段值更新
                mainData.put("idTask", entity.getId());
                mainData.put("nameTask", entity.getId());
                mainData.put("planDevice", entity.getId());
                mainData.put("planCount", entity.getId());
                mainData.put("planStartTime", entity.getId());
                mainData.put("planFinishTime", entity.getId());
                mainData.put("actualDispatchTime", entity.getId());
                mainData.put("actualFinshTime", entity.getId());
                mainData.put("hasFinished", entity.getId());
                mainData.put("finishPercent", entity.getId());

                root.put("data", mainData);

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
