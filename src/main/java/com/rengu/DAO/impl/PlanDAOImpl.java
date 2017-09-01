package com.rengu.DAO.impl;

import com.rengu.DAO.PlanDAO;
import com.rengu.entity.RG_AdjustOrderEntity;
import com.rengu.entity.RG_PlanEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanch on 2017/7/1.
 */
public class PlanDAOImpl extends SuperDAOImpl implements PlanDAO<RG_PlanEntity> {
    @Override
    public List<RG_PlanEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_PlanEntity rg_planEntity";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public RG_PlanEntity findAllById(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_PlanEntity rg_planEntity where rg_planEntity.id =:id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            if (!query.list().isEmpty()) {
                RG_PlanEntity rg_planEntity = (RG_PlanEntity) query.list().get(0);
                return rg_planEntity;
            } else {
                return null;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RG_PlanEntity> findAllBySnapshotId(String id) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_PlanEntity rg_planEntity where rg_planEntity.snapShort.id =:id order by rg_planEntity.t1Task asc";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }

    @Override
    public RG_PlanEntity findAllByOrderId(String id) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }

            //SQL删除
            NativeQuery query = session.createNativeQuery("select * from rg_plan where idOrder = ? ", RG_PlanEntity.class);
            query.setParameter(1, id);
            List<RG_PlanEntity> list = query.list();
            if (list.size() > 0) {
                return list.get(0);
            }
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
