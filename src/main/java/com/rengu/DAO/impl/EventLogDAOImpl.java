package com.rengu.DAO.impl;

import com.rengu.DAO.EventLogDAO;
import com.rengu.entity.RG_EventLogEntity;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by hanch on 2017/7/20.
 */
public class EventLogDAOImpl extends SuperDAOImpl implements EventLogDAO<RG_EventLogEntity> {
    @Override
    public List<RG_EventLogEntity> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_EventLogEntity rg_eventLogEntity order by rg_eventLogEntity.creatTime desc";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }
}
