package com.rengu.DAO.impl;

import com.rengu.DAO.CarryDAO;
import com.rengu.entity.RG_Mes_CarryInfo;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by XY on 2017/8/17.
 */
public class CarryDAOImpl extends SuperDAOImpl implements CarryDAO<RG_Mes_CarryInfo> {
    @Override
    public List<RG_Mes_CarryInfo> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_Mes_CarryInfo rg_carryInfo";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }

    public List<RG_Mes_CarryInfo> findAllByFirstResultAndMaxResults(int firstResult, int maxResults) {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_Mes_CarryInfo rg_carryInfo";
        Query query = session.createQuery(hql);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        List list = query.list();
        return list;
    }

    public int getTotalNum() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_Mes_CarryInfo rg_carryInfo";
        Query query = session.createQuery(hql);
        int totalNum = query.list().size();
        return totalNum;
    }
}
