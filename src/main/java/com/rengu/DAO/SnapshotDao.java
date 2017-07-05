package com.rengu.DAO;

import com.rengu.entity.*;
import com.rengu.util.MySessionFactory;
import com.rengu.util.SnapshotLevel;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by wey580231 on 2017/6/27.
 */
public class SnapshotDao {

    public boolean switchToEmulateData(String userId, String id) {
        boolean result = false;

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        RG_SnapshotNodeEntity bottomSnapshot = session.get(RG_SnapshotNodeEntity.class, id);

        if (bottomSnapshot != null && bottomSnapshot.getLevel().equals(SnapshotLevel.BOTTOM)) {

            //【1】查找此次排程对应的所有订单结果信息
            List<RG_PlanEntity> plans = bottomSnapshot.getPlans();

            //【2】将plan表转换至模拟数据
            result = convertPlanTo3DEmulateData(plans);

            RG_SnapshotNodeEntity rootParent = bottomSnapshot.getRootParent();

            try {
                String layoutName = rootParent.getSchedule().getLayout().getName();

                //【3】更新3D车间的对应状态，将布局、仿真、快照节点更新
                NativeQuery nativeQuery = session.createNativeQuery("update rg_state3d set layoutState = ?, layoutId = ? ,model = ? , snapshotId = ? where id = ?");
                nativeQuery.setParameter(1, 1);
                nativeQuery.setParameter(2, layoutName);
                nativeQuery.setParameter(3, 1);
                nativeQuery.setParameter(4, id);
                nativeQuery.setParameter(5, 1);
                if (nativeQuery.executeUpdate() > 0) {
                    result = true;
                }

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        session.getTransaction().commit();

        return result;
    }

    //将plan表转换成3d车间的模拟数据，中间存在的字段需要框架来补充
    private boolean convertPlanTo3DEmulateData(List<RG_PlanEntity> plans) {
        boolean flag = false;

        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query queryObject = session.createNativeQuery("truncate table rg_emulatedata");

        if (queryObject.executeUpdate() >= 0 && plans.size() > 0) {

            try {

                RG_PlanEntity startPlan = plans.get(0);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                Date initialDate = sdf.parse(startPlan.getT1Task());

                for (int i = 0; i < plans.size(); i++) {

                    RG_PlanEntity plan = plans.get(i);

                    RG_EmulateDataEntity data = new RG_EmulateDataEntity();

                    //资源名
                    data.setItem(plan.getResourceByIdResource().getId());
                    //动作
                    data.setState(plan.getProcessByIdProcess().getOperation());

                    //货物

                    //开始位置
                    data.setStartLocation("CURRENT");

                    //结束位置
                    data.setEndLocation(plan.getSiteByIdSite().getId());

                    Date startDate = sdf.parse(plan.getT1Task());
                    Date endDate = sdf.parse(plan.getT2Task());

                    //开始时间
                    data.setStartTime(Long.toString((startDate.getTime() - initialDate.getTime()) / 1000));

                    //结束时间
                    data.setEndTime(Long.toString((endDate.getTime() - initialDate.getTime()) / 1000));

                    data.setOrderEntity(plan.getOrderByIdOrder());

                    session.save(data);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        session.getTransaction().commit();

        return flag;
    }

    //TODO 将结果下发给MES，下发之前取得MES的同意
    public boolean switchResultToMess(String s, String id) {
        boolean result = false;

        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }

        RG_SnapshotNodeEntity snapshot = session.get(RG_SnapshotNodeEntity.class, id);

        if (snapshot != null && snapshot.getLevel().equals(SnapshotLevel.BOTTOM)) {

            RG_SnapshotNodeEntity parent = snapshot.getParent();

            if (!parent.getApply() && !snapshot.getApply()) {

                parent.setApply(true);
                snapshot.setApply(true);
                session.update(snapshot);
                session.update(parent);
            }
        }

        session.getTransaction().commit();

        return result;
    }
}
