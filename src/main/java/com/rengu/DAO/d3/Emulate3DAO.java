package com.rengu.DAO.d3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.entity.RG_EmulateDataEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.entity.RG_SnapshotNodeEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by wey580231 on 2017/6/6.
 */
public class Emulate3DAO {

    //TODO 3D车间的模拟数据，是依照最近一次排程结果来转换，还是可以根据指定的排程快照对应的排程记录来查看？
    public boolean getEmulateData(String snapshotId, StringBuilder jsonString) {
        boolean flag = false;
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from RG_SnapshotNodeEntity entity where entity.id=:id");

        query.setParameter("id", snapshotId);

        RG_SnapshotNodeEntity snapshot = session.get(RG_SnapshotNodeEntity.class, snapshotId);

        if (snapshot != null) {
            Set<RG_PlanEntity> plans = snapshot.getPlans();
        }

        query = session.createQuery("from RG_EmulateDataEntity entity");
        List<RG_EmulateDataEntity> list = (List<RG_EmulateDataEntity>) query.list();
        if (list.size() > 0) {
            Iterator<RG_EmulateDataEntity> iter = list.iterator();

            ObjectMapper mapper = new ObjectMapper();               //定义转换类
            ObjectNode root = mapper.createObjectNode();            //创建根节点
            root.put("result", "0");

            ArrayNode array = mapper.createArrayNode();

            while (iter.hasNext()) {
                RG_EmulateDataEntity data = iter.next();

                ObjectNode dataNode = mapper.createObjectNode();

                dataNode.put("item", data.getItem());
                dataNode.put("state", data.getState());
                dataNode.put("good", data.getGood());
                dataNode.put("startLocation", data.getStartLocation());
                dataNode.put("ednLocation", data.getEndLocation());
                dataNode.put("startTime", data.getStartTime());
                dataNode.put("endTime", data.getEndTime());

                array.add(dataNode);
            }

            root.put("data", array);

            try {

                jsonString.append(mapper.writeValueAsString(root));
                flag = true;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        session.getTransaction().commit();

        return flag;
    }

    //将plan表转换成3d车间的模拟数据，中间存在的字段需要框架来补充
    private List<RG_EmulateDataEntity> convertPlanTo3DEmulateData(Set<RG_PlanEntity> plans) {

        List<RG_EmulateDataEntity> list = new ArrayList<RG_EmulateDataEntity>();

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();

        Query queryObject = session.createNativeQuery("truncate table rg_emulatedata");
        if (queryObject.executeUpdate() >= 0) {
            Iterator<RG_PlanEntity> iter = plans.iterator();
            while (iter.hasNext()) {
                RG_PlanEntity plan = iter.next();

                RG_EmulateDataEntity data = new RG_EmulateDataEntity();

                list.add(data);
            }
        }
        return list;
    }
}
