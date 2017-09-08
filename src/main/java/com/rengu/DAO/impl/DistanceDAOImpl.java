package com.rengu.DAO.impl;

import com.rengu.DAO.DistanceDAO;
import com.rengu.entity.RG_DistanceEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DistanceDAOImpl extends SuperDAOImpl implements DistanceDAO<RG_DistanceEntity> {

    @Override
    public List<RG_DistanceEntity> findAllBySiteId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_DistanceEntity rg_distanceEntity where rg_distanceEntity.startSite.id =:id or rg_distanceEntity.endSite.id =:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }

    public boolean deleteBySiteId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
            session.createQuery("delete from RG_DistanceEntity rg_distanceEntity where rg_distanceEntity.startSite.id =:id or rg_distanceEntity.endSite.id =:id").setParameter("id",id).executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
