package com.rengu.DAO.d3;

import com.rengu.entity.RG_PlanEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by wey580231 on 2017/6/6.
 */
public class Emulate3DAO {

    //模拟数据->3D数据
    public boolean getEmulateData(String scheduleId, StringBuilder jsonString) {
        boolean flag = false;
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query query = session.createQuery("from RG_PlanEntity entity where entity.scheduleByIdSchedule=:id ");
        query.setParameter("id", scheduleId);
        List<RG_PlanEntity> list = (List<RG_PlanEntity>)query.list();
        if (list.size() > 0) {

        }

        session.getTransaction().commit();
        session.close();

        return flag;
    }
}
