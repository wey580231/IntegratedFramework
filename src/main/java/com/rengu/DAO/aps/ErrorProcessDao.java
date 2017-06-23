package com.rengu.DAO.aps;

import com.rengu.entity.RG_AdjustDeviceEntity;
import com.rengu.entity.RG_AdjustOrderEntity;
import com.rengu.entity.RG_AdjustProcessEntity;
import com.rengu.entity.RG_SnapshotNodeEntity;
import com.rengu.util.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用于aps进行异常的处理
 * Created by wey580231 on 2017/6/15.
 */
public class ErrorProcessDao {

    //设备故障
    public Integer processDeviceError(String id) {

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Integer result = ApsTools.UNKNOWN;

        Query query = session.createQuery("from RG_AdjustDeviceEntity entity where entity.id=:id");
        query.setParameter("id", id);
        List list = query.list();
        if (list.size() == 1 && list.get(0) instanceof RG_AdjustDeviceEntity) {
            RG_AdjustDeviceEntity entity = (RG_AdjustDeviceEntity) list.get(0);

            //撤销资源
            if (entity.getCancelTime() != null && entity.getLatestCancelTime() != null) {
                result = ApsTools.instance().executeCommand(ApsTools.instance().getCancelDeviceURL(entity));
            }
            //设置时段不可用
            else if (entity.getUnavailableStartDate() != null && entity.getUnavailableEndDate() != null) {
                result = ApsTools.instance().executeCommand(ApsTools.instance().getUnavailableDeviceURL(entity));
            }

            //更新故障的状态、创建
            if (result == ApsTools.STARTED) {
                entity.setState(ErrorState.ERROR_APS_PROCESS);

                createSnapNode();

                session.update(entity);
            }
        }

        session.getTransaction().commit();

        return result;
    }

    //紧急插单
    public Integer processOrderError(String id) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Integer result = ApsTools.UNKNOWN;

        Query query = session.createQuery("from RG_AdjustOrderEntity entity where entity.id=:id");
        query.setParameter("id", id);
        List list = query.list();
        if (list.size() == 1 && list.get(0) instanceof RG_AdjustOrderEntity) {
            RG_AdjustOrderEntity entity = (RG_AdjustOrderEntity) list.get(0);

//            result = ApsTools.instance().executeCommand(ApsTools.instance().getAdjustOrderHandlingURL(entity));

            //更新故障的状态、创建
            if (result == ApsTools.STARTED) {
                entity.setState(ErrorState.ERROR_APS_PROCESS);
                createSnapNode();
                session.update(entity);
            }
        }

        session.getTransaction().commit();

        return result;
    }

    //工序异常
    public Integer processProcessError(String id) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Integer result = ApsTools.UNKNOWN;

        Query query = session.createQuery("from RG_AdjustProcessEntity entity where entity.id=:id");
        query.setParameter("id", id);
        List list = query.list();
        if (list.size() == 1 && list.get(0) instanceof RG_AdjustProcessEntity) {
            RG_AdjustProcessEntity entity = (RG_AdjustProcessEntity) list.get(0);

            result = ApsTools.instance().executeCommand(ApsTools.instance().getAdjustProcessHandlingURL(entity));

            //更新故障的状态、创建
            if (result == ApsTools.STARTED) {
                entity.setState(ErrorState.ERROR_APS_PROCESS);
                createSnapNode();
                session.update(entity);
            }
        }

        session.getTransaction().commit();

        return result;
    }

    private void createSnapNode() {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Query query = session.createQuery("from RG_SnapshotNodeEntity entity where entity.id=:id");
        query.setParameter("id", UserConfigTools.getRootSnapId("1"));
        List list = query.list();
        if (list.size() > 0 && list.get(0) instanceof RG_SnapshotNodeEntity) {
            RG_SnapshotNodeEntity rootSnapshot = (RG_SnapshotNodeEntity) list.get(0);

            RG_SnapshotNodeEntity middleSnapshot = new RG_SnapshotNodeEntity();
            middleSnapshot.setId(Tools.getUUID());
            middleSnapshot.setName("工序异常应急");
            middleSnapshot.setLevel(SnapshotLevel.MIDDLE);
            middleSnapshot.setParent(rootSnapshot);
            middleSnapshot.setRootParent(rootSnapshot);

            UserConfigTools.updateMiddleSnapshotId("1",middleSnapshot.getId(),true);

            rootSnapshot.getChilds().add(middleSnapshot);
            session.save(rootSnapshot);
        }
    }
}
