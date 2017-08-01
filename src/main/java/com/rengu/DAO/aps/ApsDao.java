package com.rengu.DAO.aps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rengu.entity.RG_ScheduleEntity;
import com.rengu.entity.RG_SnapshotNodeEntity;
import com.rengu.util.ApsTools;
import com.rengu.util.MySessionFactory;
import com.rengu.util.Tools;
import com.rengu.util.UserConfigTools;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * Created by wey580231 on 2017/6/14.
 */
public class ApsDao {

    public boolean queryCurrState(StringBuilder result) {
        boolean flag = false;
        int state = ApsTools.instance().queryExecuteState();

        ObjectMapper mapper = new ObjectMapper();  //定义转换类
        ObjectNode root = mapper.createObjectNode();//创建根节点
        root.put("result", "ok");

        ObjectNode data = mapper.createObjectNode();
        data.put("state", state);

        root.put("data", data);

        try {
            result.append(mapper.writeValueAsString(root));
            flag = true;
        } catch (JsonProcessingException e) {

        }
        return flag;
    }

    public boolean getScheduleInfo(StringBuilder result) {
        boolean flag = false;

        Session session = MySessionFactory.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        NativeQuery query = session.createNativeQuery("select * from rg_scheduleentity order by scheduleTime desc limit 0,1", RG_ScheduleEntity.class);

        List<RG_ScheduleEntity> list = query.list();

        String currApsSnapshotId = UserConfigTools.getUserConfig("1").getApsCurrSnapshotId();

        String bottomNodeName = "";
        String middleNodeName = "";

        if (currApsSnapshotId != null && currApsSnapshotId.length() > 0) {
            RG_SnapshotNodeEntity bottomSnapshot = session.get(RG_SnapshotNodeEntity.class, currApsSnapshotId);
            if (bottomSnapshot != null) {
                bottomNodeName = bottomSnapshot.getName();

                RG_SnapshotNodeEntity middleSnapshot = bottomSnapshot.getParent();
                if (middleSnapshot != null) {
                    middleNodeName = middleSnapshot.getName();
                }
            }
        }

        if (list.size() > 0) {

            RG_ScheduleEntity entity = list.get(0);

            ObjectMapper mapper = new ObjectMapper();  //定义转换类
            ObjectNode root = mapper.createObjectNode();//创建根节点
            root.put("result", "ok");

            ObjectNode data = mapper.createObjectNode();
            data.put("name", entity.getName());
            data.put("state", entity.getState());
            data.put("scheduleTime", Tools.formatToStandardDate(entity.getScheduleTime()));
            data.put("startCalcTime", Tools.formatToStandardDate(entity.getStartCalcTime()));
            data.put("endCalcTime", Tools.formatToStandardDate(entity.getEndCalcTime()));
            data.put("scheduleWindow", entity.getScheduleWindow());
            data.put("rollTime", entity.getRollTime());
            data.put("bottomNodeName", bottomNodeName);
            data.put("middleNodeName", middleNodeName);


            root.put("data", data);

            try {
                result.append(mapper.writeValueAsString(root));
                flag = true;
            } catch (JsonProcessingException e) {

            }
        }
        return flag;
    }
}
