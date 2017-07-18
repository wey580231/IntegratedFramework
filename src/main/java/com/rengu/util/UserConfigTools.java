package com.rengu.util;

import com.rengu.entity.RG_UserConfigEntity;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

/**
 * 用户配置信息工具，用于获取当前用户排程记录等信息
 * Created by wey580231 on 2017/6/22.
 */
public class UserConfigTools {

    //创建新排程，将当前用户配置信息更新
    public static int newScheduleRecord(String userId, String scheduleId, String rootId, String middleId, boolean isError) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();

        NativeQuery query = session.createNativeQuery("update rg_userconfig set latestScheduleId=?, rootSnapshotId=?,middleSnapshotId=?,errorSchedule=? where idUser = ?");
        query.setParameter(1, scheduleId);
        query.setParameter(2, rootId);
        query.setParameter(3, middleId);
        query.setParameter(4, isError);
        query.setParameter(5, userId);

        return query.executeUpdate();
    }

    //根据用户ID查找排程根节点
    public static String getRootSnapId(String userId) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        String result = "";

        NativeQuery query = session.createNativeQuery("select rootSnapshotId from rg_userconfig where idUser = ?");
        query.setParameter(1, userId);
        List list = query.getResultList();
        if (list.size() > 0) {
            result = list.get(0).toString();
        }

        session.getTransaction().commit();
        session.close();

        return result;
    }

    //根据用户ID查找排程中间节点
    public static String getMiddleSnapId(String userId) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        String result = "";

        NativeQuery query = session.createNativeQuery("select middleSnapshotId from rg_userconfig where idUser = ?");
        query.setParameter(1, userId);
        List list = query.getResultList();
        if (list.size() > 0) {
            result = list.get(0).toString();
        }

        session.getTransaction().commit();
        session.close();

        return result;
    }

    //根据用户更新bottom节点信息
    public static int updateBottomSnapshotId(String userId, String bottomId) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        String result = "";

        NativeQuery query = session.createNativeQuery("update rg_userconfig set bottomSnapshotId = ? where idUser = ?");
        query.setParameter(1, bottomId);
        query.setParameter(2, userId);

        return query.executeUpdate();
    }

    //在调用应急接口时，保存快照的节点。
    public static int updateMiddleSnapshotId(String userId, String middleId, boolean errorSchedule) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        String result = "";

        NativeQuery query = session.createNativeQuery("update rg_userconfig set middleSnapshotId = ?,errorSchedule = ? where idUser = ?");
        query.setParameter(1, middleId);
        query.setParameter(2, errorSchedule);
        query.setParameter(3, userId);

        return query.executeUpdate();
    }

    //根据用户ID获取配置信息
    public static RG_UserConfigEntity getUserConfig(String userId) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        String result = "";

        RG_UserConfigEntity entity = null;

        NativeQuery query = session.createNativeQuery("select * from rg_userconfig where idUser = ?");
        query.setParameter(1, userId);
        List list = query.getResultList();
        if (list.size() > 0) {
            Object[] rowdata = (Object[]) list.get(0);
            entity = new RG_UserConfigEntity();
            entity.setId(Integer.parseInt(rowdata[0].toString()));
            entity.setLatestScheduleId(rowdata[1].toString());
            entity.setCurrScheduleId(rowdata[2].toString());
            entity.setRootSnapshotId(rowdata[3].toString());
            entity.setMiddleSnapshotId(rowdata[4].toString());
            entity.setBottomSnapshotId(rowdata[5].toString());
            entity.setErrorSchedule(Boolean.parseBoolean(rowdata[6].toString()));
            entity.setApsReplyCount(Integer.parseInt(rowdata[7].toString()));
            entity.setResetApsTable(Boolean.parseBoolean(rowdata[8].toString()));
            entity.setErrorType(rowdata[9].toString());
            entity.setErrorId(rowdata[10].toString());
        }

        session.getTransaction().commit();
        session.close();

        return entity;
    }

    //根据ID，更新
    public static int updateApsReplyCount(String userId, int count) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();

        NativeQuery query = session.createNativeQuery("update rg_userconfig set apsReplyCount = ? where idUser = ?");
        query.setParameter(1, count);
        query.setParameter(2, userId);
        return query.executeUpdate();
    }

    //获取最新排程信息
    public static String getLatestSchedule(String userId) {
        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        String result = "";

        NativeQuery query = session.createNativeQuery("select latestScheduleId from rg_userconfig where idUser = ?");
        query.setParameter(1, userId);
        List list = query.getResultList();
        if (list.size() > 0) {
            result = list.get(0).toString();
        }

        session.getTransaction().commit();
        session.close();

        return result;
    }

    //故障应急时，保存当前应急的故障信息，待结果返回时，根据此信息更新对应故障的状态信息
    public static int updateErroInfo(String userId, String errorType, String errorId) {
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();

        NativeQuery query = session.createNativeQuery("update rg_userconfig set errorType = ?, errorId = ? where idUser = ?");
        query.setParameter(1, errorType);
        query.setParameter(2, errorId);
        query.setParameter(3, userId);
        return query.executeUpdate();
    }
}
