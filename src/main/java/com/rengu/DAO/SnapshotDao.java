package com.rengu.DAO;

import com.rengu.entity.*;
import com.rengu.util.MySessionFactory;
import com.rengu.util.SnapshotLevel;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.Iterator;
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
            Set<RG_PlanEntity> plans = bottomSnapshot.getPlans();

            //【2】TODO 等真实需要转换时，再调用。
//            convertPlanTo3DEmulateData(plans);

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
    private void convertPlanTo3DEmulateData(Set<RG_PlanEntity> plans) {

        Session session = MySessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        Query queryObject = session.createNativeQuery("truncate table rg_emulatedata");
        if (queryObject.executeUpdate() >= 0) {

            Iterator<RG_PlanEntity> iter = plans.iterator();
            while (iter.hasNext()) {
                RG_PlanEntity plan = iter.next();

                //TODO 将plan表转换成3d车间对应的格式
                RG_EmulateDataEntity data = new RG_EmulateDataEntity();

                data.setOrderEntity(plan.getOrderByIdOrder());

                session.save(data);
            }
        }
        session.getTransaction().commit();
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
