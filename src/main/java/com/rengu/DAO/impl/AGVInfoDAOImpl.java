package com.rengu.DAO.impl;

import com.rengu.DAO.AGVInfoDAO;
import com.rengu.entity.RG_Mes_AgvInfo;
import com.rengu.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by XY on 2017/8/23.
 */
public class AGVInfoDAOImpl extends SuperDAOImpl implements AGVInfoDAO<RG_Mes_AgvInfo> {
    @Override
    public List<RG_Mes_AgvInfo> findAll() {
        MySessionFactory.getSessionFactory().getCurrentSession().close();
        Session session = MySessionFactory.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();

        if (!transaction.isActive()) {
            session.beginTransaction();
        }
        String hql = "from RG_Mes_AgvInfo rg_agvInfo";
        Query query = session.createQuery(hql);
        List list = query.list();
        return list;
    }
}
