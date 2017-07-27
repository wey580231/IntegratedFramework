package com.rengu.DAO.impl;

import com.rengu.DAO.AdjustLayoutDAO;
import com.rengu.entity.RG_AdjustLayoutEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AdjustLayoutDAOImpl extends SuperDAOImpl implements AdjustLayoutDAO<RG_AdjustLayoutEntity> {
    @Override
    public List<RG_AdjustLayoutEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_AdjustLayoutEntity rg_adjustLayoutEntity ";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    @Override
    public List<RG_AdjustLayoutEntity> findAllByErrorState(Integer errorState) {
        try {
            MySessionFactory.getSessionFactory().getCurrentSession().close();
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                session.beginTransaction();
            }
            String hql = "from RG_AdjustLayoutEntity rg_adjustLayoutEntity where rg_adjustLayoutEntity.state =:errorState";
            Query query = session.createQuery(hql);
            query.setParameter("errorState", errorState);
            List list = query.list();
            return list;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
